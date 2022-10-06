package me.xtrm.atlas.metadata.jackson

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.module.*
import me.xtrm.atlas.metadata.api.mod.dependency.DependencyType
import me.xtrm.atlas.metadata.jackson.ser.*
import org.semver4j.RangesList

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

    override fun setupModule(context: SetupContext): Unit =
        context.run {
            addDeserializers(
                SimpleDeserializers().apply {
                    addDeserializer(
                        DependencyType::class.java,
                        DependencyTypeDeserializer,
                    )

                    addDeserializer(
                        RangesList::class.java,
                        RangesListDeserializer,
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
                        RangesList::class.java,
                        RangesListSerializer,
                    )
                }
            )
        }
}
