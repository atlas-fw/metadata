package me.xtrm.atlas.metadata

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder

internal val jacksonMapper = jacksonMapperBuilder()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, false)
    .build()