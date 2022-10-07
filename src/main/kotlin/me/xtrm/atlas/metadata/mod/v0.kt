package me.xtrm.atlas.metadata.mod

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.mod.*
import me.xtrm.atlas.metadata.api.mod.dependency.DependencyType
import me.xtrm.atlas.metadata.jackson.OBJECT_MAPPER
import java.util.*
import me.xtrm.atlas.metadata.api.Parser as BaseParser

/**
 * Iteration 0 of the [ModMetadata] schema specification.
 *
 * Note: This version is highly experimental and subject to changes,
 * expect random unnotified changes until V1.
 *
 * @author xtrm
 * @author lambdagg
 * @since 0.0.1
 */
data class ModMetadataV0(
    override val id: String,
    @JsonProperty("version") val versionString: String = "0.0.0-SNAPSHOT+unspecified",
    @JsonProperty("semVersion") override val version: Version =
        Version.parse(versionString),
    override val displayName: String = id,
    override val description: String = "No description provided.",
    override val authors: List<ModAuthorV0> = emptyList(),
    override val contact: ModContactV0 = ModContactV0(),
    override val entrypoints: Map<String, ModEntrypointV0> = emptyMap(),
    override val licences: List<String> = listOf("repo"),
    override val dependencies: Map<DependencyType, DependencyDeclarations> = emptyMap()
) : ModMetadata {
    companion object Parser : BaseParser<ModMetadataV0> {
        override fun from(string: String): ModMetadataV0 =
            OBJECT_MAPPER.readValue(string)
    }
}

/**
 * @author lambdagg
 * @since 0.0.1
 */
data class ModAuthorV0(
    override val name: String,
    override val website: String?,
    override val mail: String?,
    override val uuid: UUID?,
) : ModAuthor

/**
 * @author lambdagg
 * @since 0.0.1
 */
data class ModContactV0(
    override val website: String? = null,
    override val repository: String? = null,
    override val issues: String? = null,
    override val discord: String? = null,
    override val extras: Map<String, String> = emptyMap(),
) : ModContact

/**
 * @author lambdagg
 * @since 0.0.1
 */
data class ModEntrypointV0(
    override val priority: Int = 0,
    override val className: String,
    override val adapter: String = "default",
) : ModEntrypoint
