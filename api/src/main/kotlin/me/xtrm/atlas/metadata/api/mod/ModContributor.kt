package me.xtrm.atlas.metadata.api.mod

import java.util.*

/**
 * A mod's contributor object.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface ModContributor {
    /**
     * The contributor's name.
     *
     * @required
     * @example "xtrm"
     */
    val name: String

    /**
     * The link to the contributor's website.
     *
     * @default null
     * @example "https://xtrm.me"
     */
    val website: String?

    /**
     * The contributor's e-mail address.
     *
     * @default null
     * @example "oss@xtrm.me"
     */
    val mail: String?

    /**
     * The contributor's Minecraft profile UUID.
     *
     * @default null
     * @example "7740d6e3-9f20-4381-a56a-060991a1c41c"
     */
    val uuid: UUID?
}
