@file:JvmName("TestUtilKt") // Causes NoSuchMethodErrors with the main util.kt otherwise

package me.xtrm.atlas.metadata

/**
 * Reads the wanted JSON resource from `/($type)/$(version).$(rev).json`.
 */
internal fun metadataJson(type: String, version: String, rev: String) =
    ParserServiceTests::class.java
        .getResourceAsStream("/$type/$version.$rev.json")!!
        .bufferedReader()
        .readText()
