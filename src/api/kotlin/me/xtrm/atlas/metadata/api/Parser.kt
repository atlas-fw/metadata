package me.xtrm.atlas.metadata.api

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

/**
 * @author xtrm
 * @since 0.0.1
 */
fun interface Parser<T> {
    @Throws(ParserException::class)
    fun from(stream: InputStream): T

    @Throws(ParserException::class)
    fun from(file: File): T = from(FileInputStream(file))
}
