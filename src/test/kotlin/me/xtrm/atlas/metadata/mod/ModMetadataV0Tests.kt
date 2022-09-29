package me.xtrm.atlas.metadata.mod

import me.xtrm.atlas.metadata.MetadataParserService
import me.xtrm.atlas.metadata.api.*
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
import java.util.function.Supplier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

@Testable
internal class ModMetadataV0Tests {
    @Test
    fun `find registered target's parser`() {
        assertNotNull(
            MetadataParserService.findFor(ModMetadata::class),
            "couldn't find parser for ModMetadata",
        )
    }

    @Test
    fun `throw on unknown target`() {
        assertThrows<ParserException> {
            MetadataParserService.getFor(Supplier::class)
        }
    }

    @Test
    fun `handle known schema version`() {
        val schemaDecl = """
            {
                "schemaVersion": 0,
                "dummyData": true
            }
        """.trimIndent()

        assertNotEquals(
            assertThrows<ParserException> {
                MetadataParserService.getFor<ModMetadata>()
                    .from(schemaDecl.byteInputStream())
            }.type,
            ParserException.Type.UNKNOWN_SCHEMA
        )
    }

    @Test
    fun `handle unknown schema version`() {
        val schemaDecl = """
            {
                "schemaVersion": ${Int.MAX_VALUE - 1},
                "dummyData": true
            }
        """.trimIndent()

        assertEquals(
            assertThrows<ParserException> {
                MetadataParserService.getFor<ModMetadata>()
                    .from(schemaDecl.byteInputStream())
            }.type,
            ParserException.Type.UNKNOWN_SCHEMA,
        )
    }
}
