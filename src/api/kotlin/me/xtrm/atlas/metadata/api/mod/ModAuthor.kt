package me.xtrm.atlas.metadata.api.mod

/**
 * Represents the author of a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModAuthor {
    /**
     * The name of the author.
     */
    val name: String

    /**
     * The link to the author's Git profile.
     */
    val gitProfile: String?

    /**
     * The email address of the author.
     */
    val mail: String?

    /**
     * The Minecraft profile UUID of the author, as a [String].
     */
    val uuid: String?
}
