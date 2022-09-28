package me.xtrm.atlas.metadata.tests.parser

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
            "couldn't find parser for ModMetadata"
        )
    }

    @Test
    fun `throw on unknown target`() {
        assertThrows<MissingParserException> {
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
        val exception = assertThrows<ParserException> {
            MetadataParserService.getFor<ModMetadata>()
                .from(schemaDecl.byteInputStream())
        }
        assertNotEquals(exception.error, ParseError.UNKNOWN_SCHEMA)
    }

    @Test
    fun `handle unknown schema version`() {
        val schemaDecl = """
            {
                "schemaVersion": ${Int.MAX_VALUE - 1},
                "dummyData": true
            }
        """.trimIndent()
        val exception = assertThrows<ParserException> {
            MetadataParserService.getFor<ModMetadata>()
                .from(schemaDecl.byteInputStream())
        }
        assertEquals(exception.error, ParseError.UNKNOWN_SCHEMA)
    }
}
