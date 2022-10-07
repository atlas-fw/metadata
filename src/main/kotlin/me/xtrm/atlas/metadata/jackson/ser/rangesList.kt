package me.xtrm.atlas.metadata.jackson.ser

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import me.xtrm.atlas.metadata.api.ParserException
import org.semver4j.RangesList
import org.semver4j.SemverException
import org.semver4j.internal.range.RangesListFactory

object RangesListDeserializer : StdDeserializer<RangesList>(
    RangesList::class.java,
) {
    internal val actualListField by lazy {
        RangesList::class.java.getDeclaredField("rangesList").also {
            it.isAccessible = true
        }
    }

    override fun deserialize(
        parser: JsonParser,
        obj: DeserializationContext,
    ): RangesList = parser.text.let {
        if (it.trim().isEmpty()) {
            throw ParserException(
                ParserException.Type.READER_EXCEPTION,
                "Version range cannot be empty",
            )
        }

        val range = try {
            RangesListFactory.create(it)
        } catch (ex: SemverException) {
            throw ParserException(
                ParserException.Type.READER_EXCEPTION,
                "Error while parsing version",
                cause = ex,
            )
        }

        // Check if range actually has available versions
        if ((actualListField.get(range) as List<*>).isEmpty()) {
            throw ParserException(
                ParserException.Type.READER_EXCEPTION,
                "No actual available version in deserialized range",
            )
        }

        range
    }
}

object RangesListSerializer : StdSerializer<RangesList>(
    RangesList::class.java,
) {
    override fun serialize(
        value: RangesList,
        gen: JsonGenerator,
        provider: SerializerProvider,
    ) = TODO()
}
