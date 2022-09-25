package me.xtrm.atlas.metadata.api.mod

/**
 * An entrypoint for a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModEntrypoint {
    /**
     * The entrypoint's priority.
     */
    val priority: Int
        get() = 0

    /**
     * The entrypoint's class name.
     */
    val className: String

    /**
     * The required adapter for this entrypoint.
     */
    val adapter: String
        get() = "default"
}
