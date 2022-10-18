@file:Suppress("NOTHING_TO_INLINE")

package me.xtrm.atlas.metadata.api.dsl

import me.xtrm.atlas.metadata.api.*
import org.semver4j.*
import kotlin.reflect.KClass

/**
 * A Kotlin-infied version of [ParserService.findFor]
 */
inline fun <T : Any> ParserService.findFor(klass: KClass<T>): Parser<T>? =
    this.findFor(klass.java)

/**
 * Kotlin syntactic sugar for [ParserService.findFor].
 */
inline fun <reified T : Any> ParserService.findFor(): Parser<T>? =
    this.findFor(T::class)

/**
 * A Kotlin-infied version of [ParserService.getFor].
 */
@Throws(ParserException::class)
inline fun <T : Any> ParserService.getFor(klass: KClass<T>): Parser<T> =
    this.getFor(klass.java)

/**
 * Kotlin syntactic sugar for [ParserService.getFor].
 */
@Throws(ParserException::class)
inline fun <reified T : Any> ParserService.getFor(): Parser<T> =
    this.getFor(T::class)
