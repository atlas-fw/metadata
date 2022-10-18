package me.xtrm.atlas.metadata.jackson

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.module.SimpleDeserializers
import com.fasterxml.jackson.databind.module.SimpleSerializers
import me.xtrm.atlas.metadata.api.Version
import me.xtrm.atlas.metadata.api.VersionRanges
import me.xtrm.atlas.metadata.api.mod.dep.DependencyType
import com.fasterxml.jackson.core.Version as JacksonVersion

/**
 * The Jackson module in charge of registering custom serializers, ...
 *
 * @author xtrm
 * @author lambdagg
 * @since 0.0.1
 */
object AtlasModule : Module() {
    override fun version(): JacksonVersion =
        JacksonVersion.unknownVersion()

    override fun getModuleName(): String =
        "Atlas Metadata Jackson Module"

    override fun setupModule(context: SetupContext): Unit =
        context.run {
            addDeserializers(
                SimpleDeserializers().apply {
                    addDeserializer(
                        DependencyType::class.java,
                        DependencyTypeDeserializer,
                    )

                    addDeserializer(
                        VersionRanges::class.java,
                        VersionRangesDeserializer,
                    )

                    addDeserializer(
                        Version::class.java,
                        VersionDeserializer,
                    )
                }
            )

            addSerializers(
                SimpleSerializers().apply {
                    addSerializer(
                        DependencyType::class.java,
                        DependencyTypeSerializer,
                    )

                    addSerializer(
                        VersionRanges::class.java,
                        VersionRangesSerializer,
                    )

                    addSerializer(
                        Version::class.java,
                        VersionSerializer,
                    )
                }
            )
        }
}
