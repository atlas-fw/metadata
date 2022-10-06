package me.xtrm.atlas.metadata.api

/**
 * @author xtrm
 * @since 0.0.1
 */
interface ParserService {
    /**
     * Maps a class to its version and [Parser] object.
     */
    val parserRegistry: Map<Class<*>, Map<Int, Parser<*>>>

    /**
     * Tries finding a [Parser] object from the given [class].
     *
     * @param T the type of object to parse from.
     * @param class A [Class] object
     *
     * @return The [Parser] object if found, `null` otherwise.
     */
    fun <T> findFor(`class`: Class<T>): Parser<T>?

    /**
     * Gets a [Parser] object from the given [class].
     *
     * @param T the type of object to parse from.
     * @param class A [Class] object
     *
     * @return The [Parser] object.
     * @throws ParserException if the [Parser] object for [T] wasn't found.
     */
    @Throws(ParserException::class)
    fun <T> getFor(`class`: Class<T>): Parser<T> =
        findFor(`class`)
            ?: throw ParserException(
                ParserException.Type.MISSING_PARSER,
                `class`.name,
            )
}
