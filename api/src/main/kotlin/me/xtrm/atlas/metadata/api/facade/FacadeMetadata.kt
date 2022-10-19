package me.xtrm.atlas.metadata.api.facade

import me.xtrm.atlas.metadata.api.VersionRanges

/**
 * Base interface for any revision of the facade metadata scheme.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface FacadeMetadata {
    /**
     * The version requirement (version range list) of Atlas Framework for
     * this metadata to be loaded and used correctly.
     *
     * Defaults to a range where all versions are accepted.
     */
    val frameworkVersion: VersionRanges

    /**
     * The root package containing all facade classes, for easier naming later
     * on.
     *
     * You can leave this empty, meaning you *will* have to reference facade
     * classes using their full name (package + name).
     */
    val rootPackage: String?

    /**
     * The list of class names (which would be prefixed by [rootPackage])
     * representing facade classes.
     */
    val facades: List<String>

    /**
     * Utility field to easily get full facade classes' path.
     * TODO: Think of an alternative way of doing this?
     */
    val facadeClasses: List<String>
        get() = run {
            val prefix = rootPackage?.let { "$it." } ?: ""
            facades.map { prefix + it }
        }
}
