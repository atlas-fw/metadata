package me.xtrm.atlas.metadata.api.mod

import com.vdurmont.semver4j.Semver

/**
 * Base interface for any revision of the mod metadata scheme.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModMetadata {
    /**
     * The mod's identifier.
     * Example: TODO
     */
    val id: String

    /**
     * The mod's display name.
     * Example: TODO
     */
    val displayName: String

    /**
     * The mod's description.
     * Example: TODO
     */
    val description: String

    /**
     * The mod's version string.
     * Example: TODO
     */
    val version: String

    /**
     * The mod's authors.
     * Example: TODO
     */
    val authors: List<ModAuthor>

    /**
     * The mod's contact information.
     * Example: TODO
     */
    val contact: ModContact

    /**
     * The mod's licenses.
     * Example: TODO
     *
     * May be looked-up in the [ModContact.repository] by leaving this value to
     * default.
     */
    val licences: List<String>

    /**
     * Mod entry point declarations.
     *
     * Maps the Entrypoint Type (as a String) to the [ModEntrypoint] target
     * information.
     */
    val entrypoints: Map<String, ModEntrypoint>

    /**
     * @return a [Semver] object parsed from [version].
     */
    fun getSemanticVersion(): Semver =
        Semver(this.version)
}
