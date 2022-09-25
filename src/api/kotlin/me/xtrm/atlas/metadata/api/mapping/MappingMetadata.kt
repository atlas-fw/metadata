package me.xtrm.atlas.metadata.api.mapping

import com.vdurmont.semver4j.Semver

/**
 * @author xtrm
 * @since 0.0.1
 */
interface MappingMetadata {
    /**
     * The minimum required Atlas Framework version for this mapping
     * metadata to be used correctly.
     *
     * This value defaults to `null`, meaning no version check
     * will be made by the Atlas Engine.
     */
    val minFrameworkVersion: Semver?
        get() = null

    /**
     * The root package containing your mapping classes, for easier
     * naming later on.
     *
     * You can leave this empty, meaning you **will** have to reference
     * your mapping classes by their full name (package + name).
     */
    val rootPackage: String?
        get() = null


    /**
     * The list of class names (which would be prefixed by [rootPackage])
     * representing mapping classes.
     */
    val mappings: List<String>
        get() = emptyList()
}
