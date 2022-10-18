package me.xtrm.atlas.metadata.mod

import me.xtrm.atlas.metadata.MetadataParserService
import me.xtrm.atlas.metadata.api.dsl.getFor
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import me.xtrm.atlas.metadata.metadataJson
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.platform.commons.annotation.Testable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@Testable
internal class ModMetadataV0Tests {
    @Test
    fun `parse full ModMetadataV0`() {
        val metadataJson = metadataJson("mod", "v0", "full")

        assertDoesNotThrow {
            val metadata = MetadataParserService.getFor<ModMetadata>()
                .from(metadataJson)
            with(metadata) {
                assertNotNull(contributors)
                assertNotNull(contact)

                mapOf(
                    "example-mod" to id,
                    "1.3.2-SNAPSHOT.beta+legacy" to version.toString(),
                    "Example Mod" to displayName,
                    "Mod description." to description,
                ).forEach(::assertEquals)

                assertEquals(contributors.size, 2)
                with(contributors[0]) {
                    mapOf(
                        "xtrm" to name,
                        "https://xtrm.me/" to website,
                        "oss@xtrm.me" to mail,
                        "7740d6e3-9f20-4381-a56a-060991a1c41c" to uuid.toString(),
                    ).forEach(::assertEquals)
                }
                with(contributors[1]) {
                    mapOf(
                        "lambdagg" to name,
                        "https://lambdagg.xyz" to website,
                        "lambda@stardustenterprises.fr" to mail,
                        "6e63e818-2268-4db4-92ec-448991ab12f1" to uuid.toString(),
                    ).forEach(::assertEquals)
                }

                with(contact!!) {
                    mapOf(
                        "https://github.com/atlas-fw/metadata" to website,
                        "https://github.com/atlas-fw/metadata" to repository,
                        "https://github.com/atlas-fw/metadata/issues" to issues,
                        "8ZZ3TXFCZb" to discord,
                    ).forEach(::assertEquals)
                    assertEquals(1, extras?.size)
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

    // TODO: test parse infer ModMetadataV0

    @Test
    fun `parse minimal ModMetadataV0`() {
        val metadataJson = metadataJson("mod", "v0", "mini")

        assertDoesNotThrow {
            /*val metadata = */MetadataParserService.getFor<ModMetadata>()
                .from(metadataJson)
        }
    }
}
