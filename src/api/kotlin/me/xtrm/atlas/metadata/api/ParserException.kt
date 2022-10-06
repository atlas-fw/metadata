package me.xtrm.atlas.metadata.api

/**
 * @author xtrm
 * @since 0.0.1
 */
class ParserException(
    val type: Type = Type.UNKNOWN,
    vararg format: Any,
    cause: Throwable? = null,
) : Exception(
    type.message.format(*format),
    cause,
) {
    /**
     * @author lambdagg
     * @since 0.0.1
     */
    enum class Type(val message: String) {
        MISSING_PARSER("No parser available for class '%s'."),
        UNKNOWN_SCHEMA("Unknown schema version '%s'."),
        @Suppress("UNUSED")
        READER_EXCEPTION("An exception occurred while reading input."),
        MAPPER_EXCEPTION("An exception occurred while parsing input."),
        UNKNOWN("An unknown exception occurred."),
    }
}
