package me.xtrm.atlas.metadata.mod

import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.UNSPECIFIED_VERSION
import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.mod.*
import me.xtrm.atlas.metadata.api.mod.dep.DependencyType
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

    override val version: Version = UNSPECIFIED_VERSION,

    override val displayName: String = id,

    override val description: String? = null,

    override val contributors: List<ModContributorV0> = emptyList(),

    override val contact: ModContactV0? = null,

    override val entrypoints: Map<String, ModEntrypointV0> = emptyMap(),

    override val licences: List<String> = listOf("repo"),

    override val dependencies: Map<DependencyType, DependencyDeclarations>? = mapOf()
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
data class ModContributorV0(
    override val name: String,

    override val website: String?,

    override val mail: String?,

    override val uuid: UUID?,
) : ModContributor

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
