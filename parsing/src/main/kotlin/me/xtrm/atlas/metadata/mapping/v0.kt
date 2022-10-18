package me.xtrm.atlas.metadata.mapping

import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.VERSION_RANGES_ACTUAL_LIST_FIELD
import me.xtrm.atlas.metadata.api.VersionRanges
import me.xtrm.atlas.metadata.api.mapping.MappingMetadata
import me.xtrm.atlas.metadata.jackson.OBJECT_MAPPER
import org.semver4j.Semver
import me.xtrm.atlas.metadata.api.Parser as BaseParser

object AllAcceptingVersionRanges : VersionRanges() {
    init {
        VERSION_RANGES_ACTUAL_LIST_FIELD.set(this, listOf(">=0.0.0"))
    }

    override fun isSatisfiedBy(version: Semver?): Boolean =
        version != null
}

/**
 * Iteration 0 of the [MappingMetadata] schema specification.
 *
 * Note: This version is highly experimental and subject to changes,
 * expect random unnotified changes until V1.
 *
 * @author xtrm
 * @since 0.0.1
 */
data class MappingMetadataV0(
    override val frameworkVersion: VersionRanges = AllAcceptingVersionRanges,

    override val rootPackage: String? = null,

    override val mappings: List<String> = emptyList(),
) : MappingMetadata {
    companion object Parser : BaseParser<MappingMetadataV0> {
        override fun from(string: String): MappingMetadataV0 =
            OBJECT_MAPPER.readValue(string)
    }
}
