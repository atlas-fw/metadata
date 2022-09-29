package me.xtrm.atlas.metadata.api.mod

import java.util.UUID

/**
 * A mod's author object.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModAuthor {
    /**
     * The author's name.
     */
    val name: String

    /**
     * The link to the author's website.
     */
    val website: String?

    /**
     * The author's e-mail address.
     */
    val mail: String?

    /**
     * The Minecraft profile UUID of the author.
     */
    val uuid: UUID?
}
