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

// TEMPROARY SHIT // I SURE HOPE SO!?
@Deprecated("what the fuck?")
fun Any.prettyPrint(): String {
    var indentLevel = 0
    val indentWidth = 4

    fun padding() = "".padStart(indentLevel * indentWidth)

    val toString = toString()

    val stringBuilder = StringBuilder(toString.length)

    var i = 0
    while (i < toString.length) {
        when (val char = toString[i]) {
            '(', '[', '{' -> {
                indentLevel++
                stringBuilder.appendLine(char).append(padding())
            }
            ')', ']', '}' -> {
                indentLevel--
                stringBuilder.appendLine().append(padding()).append(char)
            }
            ',' -> {
                stringBuilder.appendLine(char).append(padding())
                // ignore space after comma as we have added a newline
                val nextChar = toString.getOrElse(i + 1) { char }
                if (nextChar == ' ') i++
            }
            else -> {
                stringBuilder.append(char)
            }
        }
        i++
    }

    return stringBuilder.toString()
}
