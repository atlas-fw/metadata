package me.xtrm.atlas.metadata.tests.parser

import me.xtrm.atlas.metadata.api.ParseMetadataException
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
import kotlin.test.Test

@Testable
internal class ModMetadataV0Tests {
    @Test
    fun `handle valid schema version`() {

    }

    @Test
    fun `handle invalid schema version`() {
        assertThrows<MissingParserException> {

        }
    }
}
