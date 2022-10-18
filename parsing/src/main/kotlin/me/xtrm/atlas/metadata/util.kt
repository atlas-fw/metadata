package me.xtrm.atlas.metadata

import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.VersionRanges
import java.lang.reflect.Field

val UNSPECIFIED_VERSION: Version =
    Version.parse("0.0.0-SNAPSHOT+unspecified")

val VERSION_RANGES_ACTUAL_LIST_FIELD: Field by lazy {
    VersionRanges::class.java.getDeclaredField("rangesList").also {
        it.isAccessible = true
    }
}
