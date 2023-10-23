import com.google.auto.service.AutoService
import dev.unex.codegen.BuildConfig
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

@OptIn(ExperimentalCompilerApi::class)
@AutoService(CommandLineProcessor::class)
class CodegenCLIProcessor : CommandLineProcessor {

    companion object {
        val ARG_ENABLED = CompilerConfigurationKey<String>("enabled")
    }
    override val pluginId = BuildConfig.KOTLIN_PLUGIN_ID
    override val pluginOptions: Collection<AbstractCliOption> = listOf(
        CliOption(
            optionName = "enabled",
            valueDescription = "boolean",
            description = "Whether or not to generate code.",
            required = false
        )
    )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        when(option.optionName) {
            "enabled" -> configuration.put(ARG_ENABLED, value)
            else -> throw IllegalArgumentException("Unexpected config option ${option.optionName}")
        }

        super.processOption(option, value, configuration)
    }
}