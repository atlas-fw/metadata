package me.xtrm.atlas.metadata.api

import java.io.*

/**
 * @author xtrm
 * @since 0.0.1
 */
fun interface Parser<T> {
    /**
     * Parses a new [T] from the given [string].
     *
     * @param string the [String] to parse as a [T].
     *
     * @return a [T] object if the parse was successful.
     * @throws ParserException if the parse was unsuccessful.
     */
    @Throws(ParserException::class)
    fun from(string: String): T

    /**
     * Parses a new [T] from the given [stream].
     *
     * @param stream the [InputStream] to parse as a [T].
     *
     * @return a [T] object if the parse was successful.
     * @throws ParserException if the parse was unsuccessful.
     */
    @Throws(ParserException::class)
    fun from(stream: InputStream): T = from(stream.bufferedReader().readText())

    /**
     * Parses a new [T] from the given [file].
     *
     * @param file the [File] to parse as a [T].
     *
     * @return a [T] object if the parse was successful.
     * @throws ParserException if the parse was unsuccessful.
     */
    @Throws(ParserException::class)
    fun from(file: File): T = from(FileInputStream(file))
}
