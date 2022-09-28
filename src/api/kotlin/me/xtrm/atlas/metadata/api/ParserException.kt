package me.xtrm.atlas.metadata.api

/**
 * @author xtrm
 * @since 0.0.1
 */
class ParserException(
    val error: ParseError,
    cause: Throwable,
) : Exception(error.message, cause)

/**
 * @author xtrm
 * @since 0.0.1
 */
enum class ParseError(
    val message: String
) {
    MISSING_PARSER("No parser available for class: %s"),
    UNKNOWN_SCHEMA("Unknown schema version: %s"),
    READER_EXCEPTION("An exception occured while reading input: %s"),
    MAPPER_EXCEPTION("An exception occured while parsing input: %s")
}
