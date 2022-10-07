package me.xtrm.atlas.metadata.mapping

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.api.VersionRanges
import me.xtrm.atlas.metadata.api.mapping.MappingMetadata
import me.xtrm.atlas.metadata.jackson.OBJECT_MAPPER
import org.semver4j.Semver
import org.semver4j.internal.range.RangesListFactory
import me.xtrm.atlas.metadata.api.Parser as BaseParser

object AllAcceptingVersionRanges : VersionRanges() {
    override fun isSatisfiedBy(version: Semver?): Boolean =
        true
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
    @JsonProperty("frameworkVersion") val frameworkVersionString: String?,
    @JsonProperty("frameworkSemVersion") override val frameworkVersion: VersionRanges =
        frameworkVersionString?.let { RangesListFactory.create(it) }
            ?: AllAcceptingVersionRanges,
    override val rootPackage: String?,
    override val mappings: List<String>,
) : MappingMetadata {
    companion object Parser : BaseParser<MappingMetadataV0> {
        override fun from(string: String): MappingMetadataV0 =
            OBJECT_MAPPER.readValue(string)
    }
}
