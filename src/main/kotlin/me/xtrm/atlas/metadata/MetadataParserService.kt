package me.xtrm.atlas.metadata

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import me.xtrm.atlas.metadata.api.*
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import me.xtrm.atlas.metadata.mod.ModMetadataV0
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * !TODO
 *
 * @author xtrm
 * @since 0.0.1
 */
@Suppress("UNCHECKED_CAST")
object MetadataParserService: ParserService {
    override val parserRegistry: Map<Class<*>, Map<Int, Parser<*>>> =
        buildMap {
            put(ModMetadata::class.java, mapOf(0 to ModMetadataV0.Parser))
        }

    override fun <T> findFor(clazz: Class<T>): Parser<T>? {
        val parsers = parserRegistry[clazz]
            ?: return null

        return Parser { inputStream ->
            val baos = ByteArrayOutputStream()
            inputStream.copyTo(baos)
            val byteArray = baos.toByteArray()

            val map = JACKSON_MAPPER.readValue(byteArray, Map::class.java)
            val schemaVersion = (map["schemaVersion"] as? Int) ?: 0
            val parser = parsers[schemaVersion] as? Parser<T>
                ?: throw ParserException(
                    ParserException.Type.UNKNOWN_SCHEMA,
                    schemaVersion,
                )


            try {
                parser.from(ByteArrayInputStream(byteArray))
            } catch (exception: MissingKotlinParameterException) {
                throw ParserException(
                    ParserException.Type.MAPPER_EXCEPTION,
                    cause = exception,
                )
            }
        }
    }
}
