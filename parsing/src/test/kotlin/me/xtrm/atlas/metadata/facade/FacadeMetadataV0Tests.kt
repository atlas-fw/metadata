package me.xtrm.atlas.metadata.facade

import me.xtrm.atlas.metadata.MetadataParserService
import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.dsl.getFor
import me.xtrm.atlas.metadata.api.facade.FacadeMetadata
import me.xtrm.atlas.metadata.metadataJson
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.platform.commons.annotation.Testable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Testable
internal class FacadeMetadataV0Tests {
    @Test
    fun `parse full metadata`() {
        val metadataJson = metadataJson("facades", "v0", "full")

        assertDoesNotThrow {
            val metadata = MetadataParserService.getFor<FacadeMetadata>()
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

            assertEquals(2, metadata.facadeClasses.size)

            assertEquals(
                "me.xtrm.atlas.api.facades.TestFacade",
                metadata.facadeClasses[0]
            )

            assertEquals(
                "me.xtrm.atlas.api.facades.subpkg.OtherFacade\$SubClass",
                metadata.facadeClasses[1]
            )
        }
    }
}
