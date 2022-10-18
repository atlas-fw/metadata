const val JAVA_VERSION = 8

object Coordinates {
    const val name = "metadata"
    const val description = "Shared metadata files and parsers required by our different projects."
    const val vendor = "xtrm"

    const val gitHost = "github.com"
    const val repoId = "atlas-fw/$name"
    const val gitUrl = "https://$gitHost/$repoId"
    const val mainGitBranch = "main"

    const val group = "me.xtrm.atlas"
    const val version = "0.0.1"
}

object Pom {
    val licenses = arrayOf(
        License("ISC License", "https://opensource.org/licenses/ISC")
    )

    val developers = arrayOf(
        Developer("xtrm", "oss@xtrm.me"),
        Developer("lambdagg", "lambda@stardustenterprises.fr")
    )
}

/**
 * A POM-compliant License object.
 */
data class License(
    val name: String,
    val url: String,
    val distribution: String = "repo",
)

/**
 * A POM-compliant Developer object.
 */
data class Developer(
    val name: String,
    val id: String,
    val email: String?,
) {
    constructor(
        nameAndId: String,
        email: String
    ) : this(nameAndId, nameAndId, email)
}
