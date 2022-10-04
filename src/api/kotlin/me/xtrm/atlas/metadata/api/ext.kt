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

// TEMPROARY SHIT
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
