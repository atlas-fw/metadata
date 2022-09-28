package me.xtrm.atlas.metadata

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import me.xtrm.atlas.metadata.api.ParseError
import me.xtrm.atlas.metadata.api.Parser
import me.xtrm.atlas.metadata.api.ParserException
import me.xtrm.atlas.metadata.api.ParserService
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import me.xtrm.atlas.metadata.mod.ModMetadataV0Parser
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * !TODO
 *
 * @author xtrm
 * @since 0.0.1
 */
@Suppress("UNCHECKED_CAST")
object MetadataParserService : ParserService {
    override val parserRegistry: Map<Class<*>, Map<Int, Parser<*>>> =
        buildMap {
            put(ModMetadata::class.java, mapOf(
                0 to ModMetadataV0Parser
            ))
        }

    override fun <T> findFor(clazz: Class<T>): Parser<T>? {
        val parsers = parserRegistry[clazz]
            ?: return null

        return Parser {
            val baos = ByteArrayOutputStream()
            it.copyTo(baos)
            val bytearr = baos.toByteArray()

            val map = jacksonMapper.readValue(
                bytearr,
                Map::class.java
            ) as Map<*, *>
            val schemaVersion = (map["schemaVersion"] as? Int) ?: 0
            val parser = parsers[schemaVersion] as? Parser<T>
                ?: throw ParserException(ParseError.UNKNOWN_SCHEMA, Exception("$schemaVersion"))


            try {
                parser.from(ByteArrayInputStream(bytearr))
            } catch (exception: MissingKotlinParameterException) {
                throw ParserException(ParseError.MAPPER_EXCEPTION, exception)
            }
        }
    }
}
