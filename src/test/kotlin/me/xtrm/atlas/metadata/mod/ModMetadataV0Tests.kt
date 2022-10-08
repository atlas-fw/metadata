package me.xtrm.atlas.metadata.mod

import me.xtrm.atlas.metadata.MetadataParserService
import me.xtrm.atlas.metadata.api.getFor
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.platform.commons.annotation.Testable
import kotlin.test.Test
import kotlin.test.assertEquals

@Testable
internal class ModMetadataV0Tests {
    @Test
    fun `parse full ModMetadataV0`() {
        val metadataJson =
            javaClass.getResourceAsStream("/mod/v0.full.json")!!
                .bufferedReader()
                .readText()

        assertDoesNotThrow {
            val metadata = MetadataParserService.getFor<ModMetadata>()
                .from(metadataJson)
            with(metadata) {
                mapOf(
                    "mod-identifier" to id,
                    "1.3.2-SNAPSHOT.beta+legacy" to version.toString(),
                    "Test Mod" to displayName,
                    "Mod description." to description,
                ).forEach(::assertEquals)

                assertEquals(authors.size, 2)
                with(authors[0]) {
                    mapOf(
                        "xtrm" to name,
                        "https://xtrm.me/" to website,
                        "oss@xtrm.me" to mail,
                        "7740d6e3-9f20-4381-a56a-060991a1c41c" to uuid.toString(),
                    ).forEach(::assertEquals)
                }
                with(authors[1]) {
                    mapOf(
                        "lambdagg" to name,
                        "https://lambdagg.xyz" to website,
                        "lambda@stardustenterprises.fr" to mail,
                        "6e63e818-2268-4db4-92ec-448991ab12f1" to uuid.toString(),
                    ).forEach(::assertEquals)
                }

                with(contact) {
                    mapOf(
                        "https://github.com/atlas-fw/metadata" to website,
                        "https://github.com/atlas-fw/metadata" to repository,
                        "https://github.com/atlas-fw/metadata/issues" to issues,
                        "8ZZ3TXFCZb" to discord,
                    ).forEach(::assertEquals)
                    assertEquals(1, extras.size)
                    assertEquals(
                        mapOf("twitter" to "https://twitter.com/xtrmdev"),
                        extras
                    )
                }

                // TODO: dependencies

                assertEquals(entrypoints.size, 1)
                with(entrypoints.entries.first()) {
                    assertEquals(key, "atlas:primary")
                    with(value) {
                        mapOf(
                            100 to priority,
                            "me.xtrm.atlas.mod.AtlasMod" to className,
                            "default" to adapter,
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `parse minimal ModMetadataV0`() {
        val metadataJson =
            javaClass.getResourceAsStream("/mod/v0.mini.json")!!
                .bufferedReader()
                .readText()

        assertDoesNotThrow {
            /*val metadata = */MetadataParserService.getFor<ModMetadata>()
                .from(metadataJson)
        }
    }
}
