package me.xtrm.atlas.metadata.api.mod

import com.vdurmont.semver4j.Semver

/**
 * Representation of a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModMetadata {
    /**
     * The mod's identifier.
     */
    val id: String

    /**
     * The mod's display name.
     */
    val displayName: String

    /**
     * The mod's description.
     */
    val description: String

    /**
     * The mod's current version string.
     */
    val version: String

    /**
     * The mod's authors.
     */
    val authors: List<ModAuthor>
        get() = emptyList()

    /**
     * The mod's contact information.
     */
    val contact: ModContact

    /**
     * The mod's licenses.
     *
     * May be looked-up in the [ModContact.repository]
     * by leaving this value to default.
     */
    val licence: List<String>
        get() = listOf("repo")

    /**
     * Mod entrypoint declarations.
     *
     * This field maps the Entrypoint Type (as a String)
     * to the [ModEntrypoint] target information.
     */
    val entrypoints: Map<String, ModEntrypoint>
        get() = emptyMap()

    /**
     * @return a [Semver] object.
     */
    fun getSemanticVersion(): Semver =
        Semver(this.version)
}
