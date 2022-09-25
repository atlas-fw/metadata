package me.xtrm.atlas.metadata.api

/**
 * @author xtrm
 * @since 0.0.1
 */
class ParseMetadataException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
