package me.xtrm.atlas.metadata.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import me.xtrm.atlas.metadata.api.Version

object VersionDeserializer : StdDeserializer<Version>(
    Version::class.java,
) {
    override fun deserialize(
        parser: JsonParser,
        ctx: DeserializationContext,
    ): Version = Version.parse(parser.text)
}

object VersionSerializer : StdSerializer<Version>(
    Version::class.java,
) {
    override fun serialize(
        value: Version,
        gen: JsonGenerator,
        provider: SerializerProvider,
    ) = gen.writeString(value.version)
}
