package me.xtrm.atlas.metadata.api

/**
 * @author xtrm
 * @since 0.0.1
 */
interface ParserService {
    val parserRegistry: Map<Class<*>, Map<Int, Parser<*>>>

    fun <T> findFor(clazz: Class<T>): Parser<T>?

    @Throws(ParserException::class)
    fun <T> getFor(clazz: Class<T>): Parser<T> =
        findFor(clazz)
            ?: throw ParserException(
                ParserException.Type.MISSING_PARSER,
                clazz.name,
            )
}
