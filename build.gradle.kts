import java.net.URL
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

import org.jetbrains.dokka.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    with(Plugins) {
        // Language Plugins
        id("org.jetbrains.kotlin.jvm") version KOTLIN apply false

        // Git Repo Information
        id("org.ajoberstar.grgit") version GRGIT

        // Code Quality
        id("org.jlleitschuh.gradle.ktlint") version KTLINT apply false

        // Documentation Generation
        id("org.jetbrains.dokka") version DOKKA

        // Maven Publication
        id("io.github.gradle-nexus.publish-plugin") version NEXUS_PUBLISH
        `maven-publish`
        signing
    }
}

// The latest commit ID
val buildRevision: String = grgit.log()[0].id ?: "dev"

allprojects {
    with(Coordinates) {
        project.group = group
        project.version = version
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

// Normalizing project version
val prettyProjectVersion = rootProject.version.toString().run {
    Regex("(\\d+\\.\\d+\\.\\d+).*").matchEntire(this)
        ?.run { groupValues[1] }
        ?: throw Error(
            "Version '$this' does not match version pattern, e.g. 1.0.0-QUALIFIER"
        )
}

subprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")

        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("org.jetbrains.dokka")

        plugin("signing")
        plugin("maven-publish")
    }

    val sourceSets = project.extensions.getByType<SourceSetContainer>()

    dependencies {
        val implementation by configurations
        val testImplementation by configurations

        with(Dependencies) {
            kotlinModules.forEach { module ->
                implementation("org.jetbrains.kotlin", "kotlin-$module", KOTLIN)
            }

            implementation("org.semver4j", "semver4j", "2.1.1")
            dokkaHtmlPlugin("org.jetbrains.dokka", "kotlin-as-java-plugin", Plugins.DOKKA)
            testImplementation("org.jetbrains.kotlin", "kotlin-test", KOTLIN)
        }
    }

    val actualJavaVersion = if (JAVA_VERSION <= 10) "1.$JAVA_VERSION" else "$JAVA_VERSION"

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.valueOf(
            "VERSION_${actualJavaVersion.replace(".", "_")}"
        )
        targetCompatibility = sourceCompatibility
    }

    configure<KtlintExtension> {
        this.disabledRules.addAll(
            "no-wildcard-imports",
            "filename"
        )
    }

    tasks {
        withType<JavaCompile> {
            sourceCompatibility = actualJavaVersion
            targetCompatibility = actualJavaVersion
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = actualJavaVersion
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<Jar> {
            archiveBaseName.set("${rootProject.name}.${project.name}")

            val buildTimeAndDate = OffsetDateTime.now()
            val buildDate = DateTimeFormatter.ISO_LOCAL_DATE.format(buildTimeAndDate)
            val buildTime = DateTimeFormatter.ofPattern("HH:mm:ss.SSSZ").format(buildTimeAndDate)

            val javaVersion = System.getProperty("java.version")
            val javaVendor = System.getProperty("java.vendor")
            val javaVmVersion = System.getProperty("java.vm.version")

            with(Coordinates) {
                manifest.attributes(
                    "Name" to group.replace(".", "/") + "/" + project.name + "/",

                    "Created-By" to "$javaVersion ($javaVendor $javaVmVersion)",
                    "Build-Date" to buildDate,
                    "Build-Time" to buildTime,
                    "Build-Revision" to buildRevision,

                    "Specification-Title" to "bus-${project.name}",
                    "Specification-Version" to prettyProjectVersion,
                    "Specification-Vendor" to vendor,

                    "Implementation-Title" to "$name-${project.name}",
                    "Implementation-Version" to buildRevision,
                    "Implementation-Vendor" to vendor,

                    "Bundle-Name" to "$name-${project.name}",
                    // the README.md file always contains the module description on its 3rd line.
                    "Bundle-Description" to projectDir.resolve("README.md").readLines()[2],
                    "Bundle-DocURL" to gitUrl,
                    "Bundle-Vendor" to vendor,
                    "Bundle-SymbolicName" to "$group.${project.name}",
                )
            }

            from("LICENSE")
        }

        // Source artifact, including everything the 'main' does but not compiled.
        create("sourcesJar", Jar::class) {
            group = "build"
            archiveClassifier.set("sources")

            from(sourceSets["main"].allSource)

            from("LICENSE")
        }

        withType<DokkaTask>().configureEach {
            moduleName.set("${Coordinates.name}-${project.name}")
        }

        withType<DokkaTaskPartial>().configureEach {
            moduleName.set(project.name)

            dokkaSourceSets.configureEach {
                includes.from(projectDir.resolve("README.md"))

                displayName.set("${Coordinates.name}/${moduleName.get()} on ${Coordinates.gitHost}")

                skipDeprecated.set(false)
                includeNonPublic.set(false)
                skipEmptyPackages.set(true)
                reportUndocumented.set(true)
                suppressObviousFunctions.set(true)

                // Link the source to the documentation
                sourceLink {
                    localDirectory.set(file("src"))
                    remoteUrl.set(
                        URL(
                            "${Coordinates.gitUrl}/tree/${Coordinates.mainGitBranch}/${project.name}/src"
                        )
                    )
                }

                // TODO
                ///**
                // * @see config.Dokka.externalDocumentations
                // */
                //config.Dokka.externalDocumentations.forEach {
                //    externalDocumentationLink { url.set(URL(it)) }
                //}
            }
        }

        // The Javadoc artifact, containing the Dokka output and the LICENSE file.
        create("javadocJar", Jar::class) {
            group = "build"
            archiveClassifier.set("javadoc")

            val dokkaHtml by getting
            dependsOn(dokkaHtml)

            from(dokkaHtml)
            from("LICENSE")
        }
    }

    publishing.publications {
        create("mavenJava", MavenPublication::class.java) {
            from(components["java"])

            groupId = project.group.toString()
            version = project.version.toString()

            with(Coordinates) {
                pom {
                    name.set(this@with.name)
                    description.set(this@with.description)
                    url.set(gitUrl)

                    with(Pom) {
                        licenses {
                            licenses.forEach {
                                license {
                                    name.set(it.name)
                                    url.set(it.url)
                                    distribution.set(it.distribution)
                                }
                            }
                        }

                        developers {
                            developers.forEach {
                                developer {
                                    id.set(it.id)
                                    name.set(it.name)
                                    email.set(it.email)
                                }
                            }
                        }
                    }

                    scm {
                        connection.set("scm:git:git://$gitHost/$repoId.git")
                        developerConnection.set(
                            "scm:git:ssh://$gitHost/$repoId.git"
                        )
                        url.set(gitUrl)
                    }
                }
            }

            signing {
                isRequired = project.properties["signing.keyId"] != null
                sign(this@create)
            }
        }
    }
}

tasks.withType<DokkaMultiModuleTask>().configureEach {
    val moduleFile = File(
        temporaryDir,
        "MODULE.${UUID.randomUUID()}.md"
    ).apply {
        writeText("# ${Coordinates.name} by ${Coordinates.vendor}\n${Coordinates.description}\n<a href=\"${Coordinates.gitUrl}\">Source</a>")
    }

    moduleName.set(Coordinates.name)
    includes.from(moduleFile)
}

// Configure publishing to Maven Central
nexusPublishing.repositories.sonatype {
    nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
    snapshotRepositoryUrl.set(
        uri(
            "https://s01.oss.sonatype.org/content/repositories/snapshots/"
        )
    )

    // Skip this step if environment variables NEXUS_USERNAME or NEXUS_PASSWORD aren't set.
    username.set(properties["NEXUS_USERNAME"] as? String ?: return@sonatype)
    password.set(properties["NEXUS_PASSWORD"] as? String ?: return@sonatype)
}
