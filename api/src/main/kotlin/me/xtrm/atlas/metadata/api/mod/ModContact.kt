package me.xtrm.atlas.metadata.api.mod

/**
 * A mod's contact information object.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModContact {
    /**
     * The mod's website URL, as a [String].
     *
     * @default [repository] or [discord] or [issues] or null TODO
     * @example "https://atlas-fw.github.io/metadata"
     */
    val website: String?

    /**
     * The mod's source repository URL, as a [String].
     *
     * @default null
     * @example "https://github.com/atlas-fw/metadata"
     */
    val repository: String?

    /**
     * The mod's issue tracker URL, as a [String].
     *
     * @default "[repository]/issues" or null
     * @example "https://github.com/atlas-fw/metadata/issues"
     */
    val issues: String?

    /**
     * The mod's discord server **invite identifier**, as a [String].
     *
     * @default null
     * @example "8ZZ3TXFCZb"
     */
    val discord: String?

    /**
     * Extra links/information destined for user-consumption.
     *
     * @default null
     * @example { "twitter": "https://twitter.com/xtrmdev" }
     */
    val extras: Map<String, String>?
}
