package me.xtrm.atlas.metadata.api

import kotlin.reflect.KClass

/**
 * @author xtrm
 * @since 0.0.1
 */
interface ParserService {
    val parserRegistry: Map<Class<*>, Map<Int, Parser<*>>>

    fun <T> findFor(clazz: Class<T>): Parser<T>?

    @Throws(MissingParserException::class)
    fun <T> getFor(clazz: Class<T>): Parser<T> =
        findFor(clazz) ?: throw MissingParserException(clazz)
}

/**
 * Syntactic sugar for [ParserService.findFor].
 */
inline fun <reified T> ParserService.findFor(): Parser<T>? =
    this.findFor(T::class.java)

/**
 * Syntactic sugar for [ParserService.getFor].
 *
 * @throws MissingParserException
 */
@Throws(MissingParserException::class)
inline fun <reified T> ParserService.getFor(): Parser<T> =
    this.getFor(T::class.java)

fun <T : Any> ParserService.findFor(clazz: KClass<T>): Parser<T>? =
    this.findFor(clazz.java)

@Throws(MissingParserException::class)
fun <T : Any> ParserService.getFor(clazz: KClass<T>): Parser<T> =
    this.getFor(clazz.java)
