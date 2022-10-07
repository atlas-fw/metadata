@file:Suppress("NOTHING_TO_INLINE")

package me.xtrm.atlas.metadata.api

import kotlin.reflect.KClass

/**
 * A Kotlin-infied version of [ParserService.findFor]
 */
inline fun <T : Any> ParserService.findFor(clazz: KClass<T>): Parser<T>? =
    this.findFor(clazz.java)

/**
 * Kotlin syntactic sugar for [ParserService.findFor].
 */
inline fun <reified T : Any> ParserService.findFor(): Parser<T>? =
    this.findFor(T::class)

/**
 * A Kotlin-infied version of [ParserService.getFor].
 */
@Throws(ParserException::class)
inline fun <T : Any> ParserService.getFor(clazz: KClass<T>): Parser<T> =
    this.getFor(clazz.java)

/**
 * Kotlin syntactic sugar for [ParserService.getFor].
 */
@Throws(ParserException::class)
inline fun <reified T : Any> ParserService.getFor(): Parser<T> =
    this.getFor(T::class.java)
