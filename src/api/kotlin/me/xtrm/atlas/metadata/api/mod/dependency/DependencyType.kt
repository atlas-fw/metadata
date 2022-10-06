package me.xtrm.atlas.metadata.api.mod.dependency

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A dependency's type.
 *
 * @author xtrm
 * @since 0.0.1
 */
enum class DependencyType(
    val key: String,
    val positive: Boolean,
    val warns: Boolean,
) {
    @JsonProperty("depends")
    DEPENDS("depends", true, true),

    @JsonProperty("softDepends")
    SOFT_DEPENDS("soft-depends", true, false),

    @JsonProperty("conflicts")
    CONFLICTS("conflicts", false, false),

    @JsonProperty("breaks")
    BREAKS("breaks", false, true),
}
