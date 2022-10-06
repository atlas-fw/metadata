package me.xtrm.atlas.metadata.jackson

import me.xtrm.atlas.metadata.api.ParserException
import me.xtrm.atlas.metadata.api.mod.dependency.DependencyType
import me.xtrm.atlas.metadata.jackson.ser.RangesListDeserializer
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
import org.semver4j.RangesList
import org.semver4j.internal.range.RangesListFactory
import kotlin.test.Test
import kotlin.test.assertEquals

@Testable
internal class AtlasModuleTests {
    @Test
    fun `dependency type deserialization`() {
        DependencyType.values().forEach {
            assertEquals(
                it,
                OBJECT_MAPPER.readValue("\"${it.key}\"", DependencyType::class.java)
            )
        }

        assertThrows<ParserException> {
            OBJECT_MAPPER.readValue("\"dummy invalid value\"", DependencyType::class.java)
        }
    }

    @Test
    fun `dependency type serialization`() {
        DependencyType.values().forEach {
            assertEquals(
                "\"${it.key}\"",
                OBJECT_MAPPER.writeValueAsString(it)
            )
        }
    }

    @Test
    fun `version range deserialization`() {
        assertEquals(
            RangesListDeserializer.actualListField.get(
                RangesListFactory.create(">=0.0.1")
            ),

            RangesListDeserializer.actualListField.get(
                OBJECT_MAPPER.readValue("\">=0.0.1\"", RangesList::class.java)
            )
        )

        assertThrows<ParserException> {
            OBJECT_MAPPER.readValue("\"dummy invalid value\"", RangesList::class.java)
        }

        assertThrows<ParserException> {
            OBJECT_MAPPER.readValue("\"\"", RangesList::class.java)
        }
    }
}
