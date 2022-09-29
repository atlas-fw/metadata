package me.xtrm.atlas.metadata.api

import kotlin.reflect.KClass

/**
 * Syntactic sugar for [ParserService.findFor].
 */
inline fun <reified T> ParserService.findFor(): Parser<T>? =
    this.findFor(T::class.java)

/**
 * Syntactic sugar for [ParserService.getFor].
 *
 * @throws ParserException
 */
@Throws(ParserException::class)
inline fun <reified T> ParserService.getFor(): Parser<T> =
    this.getFor(T::class.java)

fun <T : Any> ParserService.findFor(clazz: KClass<T>): Parser<T>? =
    this.findFor(clazz.java)

@Throws(ParserException::class)
fun <T : Any> ParserService.getFor(clazz: KClass<T>): Parser<T> =
    this.getFor(clazz.java)
