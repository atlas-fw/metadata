package me.xtrm.atlas.metadata.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import me.xtrm.atlas.metadata.VERSION_RANGES_ACTUAL_LIST_FIELD
import me.xtrm.atlas.metadata.api.ParserException
import me.xtrm.atlas.metadata.api.VersionRanges
import org.semver4j.SemverException
import org.semver4j.internal.range.RangesListFactory

object VersionRangesDeserializer : StdDeserializer<VersionRanges>(
    VersionRanges::class.java,
) {
    override fun deserialize(
        parser: JsonParser,
        obj: DeserializationContext,
    ): VersionRanges = parser.text.let {
        if (it.trim().isEmpty()) {
            // TODO maybe we could default this to the AllAcceptingVersionsRange?
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
        if ((VERSION_RANGES_ACTUAL_LIST_FIELD.get(range) as List<*>).isEmpty()) {
            // TODO maybe we could default this to the AllAcceptingVersionsRange?
            throw ParserException(
                ParserException.Type.READER_EXCEPTION,
                "No actual available version in deserialized range",
            )
        }

        range
    }
}

object VersionRangesSerializer : StdSerializer<VersionRanges>(
    VersionRanges::class.java,
) {
    override fun serialize(
        value: VersionRanges,
        gen: JsonGenerator,
        provider: SerializerProvider,
    ) = TODO()
}
