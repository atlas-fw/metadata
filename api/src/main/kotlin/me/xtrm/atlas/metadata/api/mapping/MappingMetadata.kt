package me.xtrm.atlas.metadata.api.mapping

import me.xtrm.atlas.metadata.api.VersionRanges

/**
 * Base interface for any revision of the mapping metadata scheme.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface MappingMetadata {
    /**
     * The version requirement (version range list) of Atlas Framework for
     * this mapping metadata to be loaded and used correctly.
     *
     * Defaults to a range where all versions are accepted.
     */
    val frameworkVersion: VersionRanges

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

    /**
     * Utility field to easily get full mapping classes' path.
     * TODO: Think of an alternative way of doing this?
     */
    val mappingClasses: List<String>
        get() = run {
            val prefix = rootPackage?.let { "$it." } ?: ""
            mappings.map { prefix + it }
        }
}
