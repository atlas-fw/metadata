package me.xtrm.atlas.metadata.mod

import me.xtrm.atlas.metadata.api.mod.ModContact
import me.xtrm.atlas.metadata.api.mod.ModMetadata

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
    override val version: String,
    override val contact: ModContact = TODO()
) : ModMetadata
