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
     * @default 0
     * @example 100
     */
    val priority: Int

    /**
     * The entrypoint's class name.
     *
     * @required
     * @example "me.xtrm.atlas.mod.AtlasMod"
     */
    val className: String

    /**
     * The entrypoint's required adapter.
     *
     * @default "default"
     * @example TODO
     */
    val adapter: String
}
