package me.xtrm.atlas.metadata.api.mod

import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.VersionRanges
import me.xtrm.atlas.metadata.api.mod.dep.DependencyType

/**
 * Aliases the dependency declaration block into a concise type.
 *
 * @see ModMetadata.dependencies
 *
 * @author xtrm
 * @since 0.0.1
 */
typealias DependencyDeclarations = Map<String, VersionRanges>

/**
 * Base interface for any revision of the mod metadata scheme.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModMetadata {
    /**
     * The mod's identifier.
     *
     * @required
     * @example "example-mod"
     */
    val id: String

    /**
     * The mod's [sementic version][Version] object.
     *
     * @required
     * @example "1.3.2-SNAPSHOT.beta+legacy"
     */
    val version: Version

    /**
     * The mod's display name.
     *
     * @default [id]
     * @example "Example mod"
     */
    val displayName: String

    /**
     * The mod's description.
     *
     * @default null
     * @example TODO
     */
    val description: String?

    /**
     * The mod's contributors.
     *
     * @default an empty list
     * @example TODO
     */
    val contributors: List<ModContributor>

    /**
     * The mod's contact information.
     *
     * @default null
     * @example TODO
     */
    val contact: ModContact?

    /**
     * The mod's declared dependencies.
     *
     * @default null
     * @example TODO
     *
     * @see DependencyType
     */
    val dependencies: Map<DependencyType, DependencyDeclarations>?

    /**
     * The mod's licenses.
     *
     * If this list is undefined when parsing, the parser will automatically
     * use the "license" field as the only item. TODO
     *
     * If this list is empty when parsing, it should default. TODO
     *
     * @default ["repo"]
     * @example ["MIT"]
     */
    val licences: List<String>

    /**
     * The mod's entrypoints declaration.
     *
     * Maps the Entrypoint Type (as a String) to the [ModEntrypoint] target
     * information.
     *
     * If this map is undefined when parsing, the parser will
     * automatically create a [Map] of "atlas:primary" to a [ModEntrypoint] with
     * its [ModEntrypoint.className] value set as the given `entrypoint` value,
     * such as `{ "entrypoint": "me.xtrm.atlas.mod.AtlasMod" }` should equal to
     * `{ "entrypoints": { "atlas:primary": { "className": "me.xtrm.atlas.mod.AtlasMod" } } }`.
     * TODO
     *
     * If a value of this map is only a [String] when parsing, the parser will
     * automatically create a [ModEntrypoint] with its [ModEntrypoint.className]
     * value set as the [String]. TODO
     *
     * @required
     * @example { "atlas:primary": "me.xtrm.atlas.mod.AtlasMod" }
     */
    val entrypoints: Map<String, ModEntrypoint>
}
