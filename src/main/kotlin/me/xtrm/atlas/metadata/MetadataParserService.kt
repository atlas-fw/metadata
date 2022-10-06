package me.xtrm.atlas.metadata

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import me.xtrm.atlas.metadata.api.Parser
import me.xtrm.atlas.metadata.api.ParserException
import me.xtrm.atlas.metadata.api.ParserService
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import me.xtrm.atlas.metadata.jackson.OBJECT_MAPPER
import me.xtrm.atlas.metadata.mod.ModMetadataV0

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
            put(ModMetadata::class.java, mapOf(0 to ModMetadataV0.Parser))
        }

    override fun <T> findFor(`class`: Class<T>): Parser<T>? {
        val parsers = parserRegistry[`class`]
            ?: return null

        return Parser { string ->
            val map = OBJECT_MAPPER.readValue(string, Map::class.java)
            val schemaVersion = (map["schemaVersion"] as? Int) ?: 0
            val parser = parsers[schemaVersion] as? Parser<T>
                ?: throw ParserException(
                    ParserException.Type.UNKNOWN_SCHEMA,
                    schemaVersion,
                )

            try {
                parser.from(string)
            } catch (exception: MissingKotlinParameterException) {
                throw ParserException(
                    ParserException.Type.MAPPER_EXCEPTION,
                    cause = exception,
                )
            }
        }
    }
}
