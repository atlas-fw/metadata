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
            javaClass.getResourceAsStream("/modMetadataV0.full.json")!!
                .bufferedReader()
                .readText()

        assertDoesNotThrow {
            val metadata = MetadataParserService.getFor<ModMetadata>()
                .from(metadataJson)
            with(metadata) {
                mapOf(
                    id to "mod-identifier",
                    version to "1.3.2-SNAPSHOT.beta+legacy",
                    displayName to "Test Mod",
                    description to "Mod description.",
                ).forEach(::assertEquals)

                assertEquals(authors.size, 2)
                with(authors[0]) {
                    mapOf(
                        name to "xtrm",
                        website to "https://xtrm.me/",
                        mail to "oss@xtrm.me",
                        uuid.toString() to "7740d6e3-9f20-4381-a56a-060991a1c41c"
                    ).forEach(::assertEquals)
                }
                with(authors[1]) {
                    mapOf(
                        name to "lambdagg",
                        website to "https://lambdagg.xyz",
                        mail to "lambda@stardustenterprises.fr",
                        uuid.toString() to "6e63e818-2268-4db4-92ec-448991ab12f1"
                    ).forEach(::assertEquals)
                }

                with(contact) {
                    mapOf(
                        website to "https://github.com/atlas-fw/metadata",
                        repository to "https://github.com/atlas-fw/metadata",
                        issues to "https://github.com/atlas-fw/metadata/issues",
                        discord to "8ZZ3TXFCZb"
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
                            priority to 100,
                            className to "me.xtrm.atlas.mod.AtlasMod",
                            adapter to "default"
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `parse minimal ModMetadataV0`() {
        val metadataJson =
            javaClass.getResourceAsStream("/modMetadataV0.mini.json")!!
                .bufferedReader()
                .readText()

        assertDoesNotThrow {
            /*val metadata = */MetadataParserService.getFor<ModMetadata>()
                .from(metadataJson)
        }
    }
}
