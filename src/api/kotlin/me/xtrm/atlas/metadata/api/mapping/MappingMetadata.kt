package me.xtrm.atlas.metadata.api.mapping

import org.semver4j.Semver

/**
 * Base interface for any revision of the mapping metadata scheme.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface MappingMetadata {
    /**
     * The minimum required Atlas Framework version for this mapping metadata to
     * be used correctly.
     *
     * Defaults to `null`, meaning no version check will be done by
     * the Atlas Engine.
     */
    val minFrameworkVersion: Semver?

    /**
     * The root package containing all mapping classes, for easier naming later
     * on.
     *
     * You can leave this empty, meaning you *will* have to reference mapping
     * classes using their full name (package + name).
     */
    val rootPackage: String?

    /**
     * The list of class names (which would be prefixed by [rootPackage])
     * representing mapping classes.
     */
    val mappings: List<String>
}
