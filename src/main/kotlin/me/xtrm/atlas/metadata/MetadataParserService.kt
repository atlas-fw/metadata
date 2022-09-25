package me.xtrm.atlas.metadata

import me.xtrm.atlas.metadata.api.MissingParserException
import me.xtrm.atlas.metadata.api.Parser
import me.xtrm.atlas.metadata.api.ParserService
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import me.xtrm.atlas.metadata.mod.ModMetadataV0Parser
import java.io.InputStreamReader

/**
 * !TODO
 *
 * @author xtrm
 * @since 0.0.1
 */
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
            val reader = it.reader()
            val map: Map<String, Any> = emptyMap()
            val schemaVersion = (map["schemaVersion"] as? Int) ?: 0
            val parser = parsers[schemaVersion]
                ?: throw MissingParserException(clazz, schemaVersion)
            it.close()


        }
    }
}
