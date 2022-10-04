package me.xtrm.atlas.metadata.api.mod

/**
 * Contact Information for a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModContact {
    /**
     * The mod's website URL, as a [String].
     *
     * If undefined, will default to the first set:
     * - [repository]
     * - [discord]
     * - [issues]
     */
    val website: String?

    /**
     * The mod's source repository URL, as a [String].
     */
    val repository: String?

    /**
     * The mod's issue tracker URL, as a [String].
     *
     * If not defined, will default to `[repository]/issues`.
     */
    val issues: String?

    /**
     * The mod's discord server **invite identifier**, as a [String].
     */
    val discord: String?

    /**
     * Extra links/information destined for user-consumption.
     */
    val extras: Map<String, String>
}
