package me.xtrm.atlas.metadata

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import me.xtrm.atlas.metadata.jackson.AtlasModule

internal val JACKSON_MAPPER = jacksonMapperBuilder()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, false)
    .addModule(AtlasModule)
    .build()
