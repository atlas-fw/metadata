package me.xtrm.atlas.metadata.jackson

import me.xtrm.atlas.metadata.VERSION_RANGES_ACTUAL_LIST_FIELD
import me.xtrm.atlas.metadata.api.ParserException
import me.xtrm.atlas.metadata.api.VersionRanges
import me.xtrm.atlas.metadata.api.mod.dep.DependencyType
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
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
        println(VERSION_RANGES_ACTUAL_LIST_FIELD)

        assertEquals(
            VERSION_RANGES_ACTUAL_LIST_FIELD.get(
                RangesListFactory.create(">=0.0.1")
            ),

            VERSION_RANGES_ACTUAL_LIST_FIELD.get(
                OBJECT_MAPPER.readValue(
                    "\">=0.0.1\"",
                    VersionRanges::class.java,
                )
            )
        )

        assertThrows<ParserException> {
            OBJECT_MAPPER.readValue(
                "\"dummy invalid value\"",
                VersionRanges::class.java,
            )
        }

        assertThrows<ParserException> {
            OBJECT_MAPPER.readValue(
                "\"\"",
                VersionRanges::class.java,
            )
        }
    }
}
