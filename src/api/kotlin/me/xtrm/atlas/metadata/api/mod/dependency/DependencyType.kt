package me.xtrm.atlas.metadata.api.mod.dependency

/**
 * Dependency type.
 *
 * @author xtrm
 * @since 0.0.1
 */
enum class DependencyType(
    val key: String,
    val positive: Boolean,
    val warns: Boolean,
) {
    DEPENDS("depends", true, true),
    SOFT_DEPENDS("soft-depends", true, false),
    CONFLICTS("conflicts", false, false),
    BREAKS("breaks", false, true)
}
