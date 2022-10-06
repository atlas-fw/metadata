package me.xtrm.atlas.metadata.api.mod

import me.xtrm.atlas.metadata.api.mod.dependency.DependencyType
import org.semver4j.RangesList
import org.semver4j.Semver

/**
 * Aliases the dependency declaration block into a concise type.
 *
 * @see ModMetadata.dependencies
 *
 * @author xtrm
 * @since 0.0.1
 */
typealias DependencyDeclarations = Map<String, RangesList>

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
     * The mod's [sementic version][Semver] object.
     * Example: TODO
     */
    val version: Semver

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
     * The mod's declared dependencies.
     * Example: TODO
     *
     * @see DependencyType
     */
    val dependencies: Map<DependencyType, DependencyDeclarations>

    /**
     * The mod's licenses.
     * Example: TODO
     */
    val licences: List<String>

    /**
     * Mod entry point declarations.
     *
     * Maps the Entrypoint Type (as a String) to the [ModEntrypoint] target
     * information.
     */
    val entrypoints: Map<String, ModEntrypoint>
}
