package me.xtrm.atlas.metadata.api

/**
 * Thrown whenever a [Parser] encounters a problem.
 *
 * @author xtrm
 * @since 0.0.1
 */
class ParserException(
    /**
     * The type of exception.
     */
    val type: Type = Type.UNKNOWN,

    /**
     * A formatting array for the [type]'s [Type.message].
     */
    vararg format: Any,

    /**
     * Another exception that caused this one, if there is one.
     */
    cause: Throwable? = null,
) : Exception(
    type.message.format(*format),
    cause,
) {
    /**
     * A [ParserException]'s type.
     *
     * @author lambdagg
     * @since 0.0.1
     */
    enum class Type(
        /**
         * The unformatted message.
         */
        internal val message: String,
    ) {
        MISSING_PARSER("No parser available for class '%s'."),
        UNKNOWN_SCHEMA("Unknown schema version '%s'."),
        READER_EXCEPTION("An exception occurred while reading input: '%s'."),
        MAPPER_EXCEPTION("An exception occurred while parsing input."),
        UNKNOWN("An unknown exception occurred."),
    }
}
