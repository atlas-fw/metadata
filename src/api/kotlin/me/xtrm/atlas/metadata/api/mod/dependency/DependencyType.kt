@file:Suppress("UNUSED")

package me.xtrm.atlas.metadata.api.mod.dependency

import com.fasterxml.jackson.annotation.JsonProperty

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
    // TODO(@lambdagg) make use of the 'key' value as the @JsonProperty by
    //  default

    @JsonProperty("depends")
    DEPENDS("depends", true, true),

    @JsonProperty("softDepends")
    SOFT_DEPENDS("softDepends", true, false),

    @JsonProperty("conflicts")
    CONFLICTS("conflicts", false, false),

    @JsonProperty("breaks")
    BREAKS("breaks", false, true),
}
