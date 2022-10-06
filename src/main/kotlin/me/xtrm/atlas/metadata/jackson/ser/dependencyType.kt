package me.xtrm.atlas.metadata.jackson.ser

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import me.xtrm.atlas.metadata.api.ParserException
import me.xtrm.atlas.metadata.api.mod.dependency.DependencyType

object DependencyTypeDeserializer : StdDeserializer<DependencyType>(
    DependencyType::class.java,
) {
    override fun deserialize(
        parser: JsonParser,
        obj: DeserializationContext,
    ): DependencyType = parser.text.let {
        DependencyType.of(it)
            ?: throw ParserException(
                ParserException.Type.READER_EXCEPTION,
                "Unknown dependency type `$it`",
            )
    }
}

object DependencyTypeSerializer : StdSerializer<DependencyType>(
    DependencyType::class.java,
) {
    override fun serialize(
        value: DependencyType,
        gen: JsonGenerator,
        provider: SerializerProvider,
    ) = gen.writeString(value.key)
}
