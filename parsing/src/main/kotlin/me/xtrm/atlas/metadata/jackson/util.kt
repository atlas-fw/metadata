package me.xtrm.atlas.metadata.jackson

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule

internal val OBJECT_MAPPER = ObjectMapper()
    .registerModule(AtlasModule)
    .registerModule(
        KotlinModule.Builder()
            .configure(KotlinFeature.NullIsSameAsDefault, true)
            .build()
    )
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, false)
