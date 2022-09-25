package me.xtrm.atlas.metadata.mod

import me.xtrm.atlas.metadata.api.Parser
import java.io.InputStream

object ModMetadataV0Parser: Parser<ModMetadataV0> {
    override fun from(stream: InputStream): ModMetadataV0 {
        TODO()
    }
}
