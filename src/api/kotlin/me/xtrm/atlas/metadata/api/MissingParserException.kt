package me.xtrm.atlas.metadata.api

/**
 * @author xtrm
 * @since 0.0.1
 */
class MissingParserException : Exception {
    private constructor(message: String) : super(message)

    constructor(clazz: Class<*>) :
        this("Missing registered parser for `${clazz.simpleName}`")

    constructor(clazz: Class<*>, schemaVersion: Int) :
        this(
            "Missing registered parser for `${clazz.simpleName}` "
                + "with schema version '$schemaVersion'"
        )
}
