package me.xtrm.atlas.metadata.api.mod

/**
 * A mod's entry point object.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModEntrypoint {
    /**
     * The entrypoint's priority.
     *
     * Default value `0`
     */
    val priority: Int

    /**
     * The entrypoint's class name.
     */
    val className: String

    /**
     * The required adapter for this entrypoint.
     *
     * Default value: `"default"`
     */
    val adapter: String
}
