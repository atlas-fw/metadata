package me.xtrm.atlas.metadata.mapping

import me.xtrm.atlas.metadata.MetadataParserService
import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.dsl.getFor
import me.xtrm.atlas.metadata.api.mapping.MappingMetadata
import me.xtrm.atlas.metadata.metadataJson
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.platform.commons.annotation.Testable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Testable
internal class MappingMetadataV0Tests {
    @Test
    fun `parse full MappingMetadataV0`() {
        val metadataJson = metadataJson("mapping", "v0", "full")

        assertDoesNotThrow {
            val metadata = MetadataParserService.getFor<MappingMetadata>()
                .from(metadataJson)

            println(metadata)

            assertTrue(
                metadata.frameworkVersion.isSatisfiedBy(
                    Version.parse("0.0.2")
                )
            )

            assertTrue(
                !metadata.frameworkVersion.isSatisfiedBy(
                    Version.parse("0.0.1")
                )
            )

            assertEquals(2, metadata.mappingClasses.size)

            assertEquals(
                "me.xtrm.atlas.api.mappings.TestMapping",
                metadata.mappingClasses[0]
            )

            assertEquals(
                "me.xtrm.atlas.api.mappings.subpkg.OtherMappingClass\$SubClass",
                metadata.mappingClasses[1]
            )
        }
    }
}
