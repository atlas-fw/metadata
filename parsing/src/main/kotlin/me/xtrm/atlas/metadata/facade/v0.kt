package me.xtrm.atlas.metadata.facade

import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.VERSION_RANGES_ACTUAL_LIST_FIELD
import me.xtrm.atlas.metadata.api.VersionRanges
import me.xtrm.atlas.metadata.api.facade.FacadeMetadata
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
 * Iteration 0 of the [FacadeMetadata] schema specification.
 *
 * Note: This version is highly experimental and subject to changes,
 * expect random unnotified changes until V1.
 *
 * @author xtrm
 * @since 0.0.1
 */
data class FacadeMetadataV0(
    override val frameworkVersion: VersionRanges = AllAcceptingVersionRanges,

    override val rootPackage: String? = null,

    override val facades: List<String> = emptyList(),
) : FacadeMetadata {
    companion object Parser : BaseParser<FacadeMetadataV0> {
        override fun from(string: String): FacadeMetadataV0 =
            OBJECT_MAPPER.readValue(string)
    }
}
