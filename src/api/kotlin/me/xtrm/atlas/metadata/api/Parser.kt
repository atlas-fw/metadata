package me.xtrm.atlas.metadata.api

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

/**
 * @author xtrm
 * @since 0.0.1
 */
fun interface Parser<T> {
    @Throws
    fun from(string: String): T

    @Throws(ParserException::class)
    fun from(stream: InputStream): T = from(stream.bufferedReader().readText())

    @Throws(ParserException::class)
    fun from(file: File): T = from(FileInputStream(file))
}
