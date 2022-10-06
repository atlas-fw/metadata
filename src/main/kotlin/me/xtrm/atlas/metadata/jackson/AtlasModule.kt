package me.xtrm.atlas.metadata.jackson

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.Module

/**
 * The Jackson module in charge of registering custom serializers, ...
 *
 * @author xtrm
 * @author lambdagg
 * @since 0.0.1
 */
object AtlasModule : Module() {
    override fun version(): Version =
        Version.unknownVersion()

    override fun getModuleName(): String =
        "Atlas Metadata Jackson Module"

    override fun setupModule(context: SetupContext?) {
        context?.run {
        }
    }
}
