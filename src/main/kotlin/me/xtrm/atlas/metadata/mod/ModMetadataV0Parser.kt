package me.xtrm.atlas.metadata.mod

import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.api.Parser
import me.xtrm.atlas.metadata.jacksonMapper
import java.io.InputStream

/**
 * @author xtrm
 * @since 0.0.1
 */
object ModMetadataV0Parser: Parser<ModMetadataV0> {
    override fun from(stream: InputStream): ModMetadataV0 =
        jacksonMapper.readValue(stream)
}
