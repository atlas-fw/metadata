package me.xtrm.atlas.metadata.mod

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.readValue
import me.xtrm.atlas.metadata.JACKSON_MAPPER
import me.xtrm.atlas.metadata.api.Parser as BaseParser
import me.xtrm.atlas.metadata.api.mod.ModAuthor
import me.xtrm.atlas.metadata.api.mod.ModContact
import me.xtrm.atlas.metadata.api.mod.ModEntrypoint
import me.xtrm.atlas.metadata.api.mod.ModMetadata
import java.io.InputStream
import java.util.*

/**
 * Iteration 0 of the [ModMetadata] schema specification.
 *
 * Note: This version is highly experimental and subject to changes,
 * expect random unnotified changes until V1.
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModMetadataV0(
    override val id: String,
    override val displayName: String = id,
    override val description: String = "No description provided.",
    override val version: String = "unspecified",
    override val authors: List<ModAuthorV0> = emptyList(),
    override val contact: ModContactV0 = TODO(),
    override val entrypoints: Map<String, ModEntrypointV0> = emptyMap(),
    override val licences: List<String> = listOf("repo")
): ModMetadata {
    companion object Parser: BaseParser<ModMetadataV0> {
        override fun from(stream: InputStream): ModMetadataV0 =
            JACKSON_MAPPER.readValue(stream)
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
) : ModAuthor {
    companion object Parser: BaseParser<ModAuthorV0> {
        override fun from(stream: InputStream) = TODO()
    }
}

/**
 * @author lambdagg
 * @since 0.0.1
 */
data class ModContactV0(
    override val website: String?,
    override val repository: String?,
    override val issues: String?,
    override val discord: String?,
    override val extras: Map<String, String> = emptyMap(),
): ModContact {
    companion object Parser: BaseParser<ModContactV0> {
        override fun from(stream: InputStream) = TODO()
    }
}

/**
 * @author lambdagg
 * @since 0.0.1
 */
data class ModEntrypointV0(
    override val priority: Int = 0,
    override val className: String,
    override val adapter: String = "default",
): ModEntrypoint {
    companion object Parser: BaseParser<ModEntrypointV0> {
        override fun from(stream: InputStream) = TODO()
    }
}
