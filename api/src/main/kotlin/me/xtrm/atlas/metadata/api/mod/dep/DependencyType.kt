@file:Suppress("UNUSED")

package me.xtrm.atlas.metadata.api.mod.dep

/**
 * A dependency's type.
 *
 * @author xtrm
 * @since 0.0.1
 */
enum class DependencyType(
    /**
     * This dependency's key.
     */
    val key: String,

    /**
     * Whether this dependency is positive.
     */
    val positive: Boolean,

    /**
     * Whether this dependency should warn out.
     */
    val warns: Boolean,
) {
    DEPENDS("depends", true, true),

    SOFT_DEPENDS("softDepends", true, false),

    CONFLICTS("conflicts", false, false),

    BREAKS("breaks", false, true);

    companion object {
        @JvmStatic
        fun of(key: String?): DependencyType? =
            values().firstOrNull { it.key == key }
    }
}
