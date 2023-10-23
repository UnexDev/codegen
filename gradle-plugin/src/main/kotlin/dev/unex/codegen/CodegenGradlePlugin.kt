package dev.unex.codegen

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.plugin.management.internal.autoapply.AutoAppliedGradleEnterprisePlugin.GROUP
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

open class CodegenGradleExtension {
    var enabled: Boolean = true
}

class CodegenGradlePlugin : KotlinCompilerPluginSupportPlugin {

    override fun getCompilerPluginId() = BuildConfig.KOTLIN_PLUGIN_ID
    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>) = true

    override fun getPluginArtifact() = SubpluginArtifact(
        groupId = GROUP,
        artifactId = BuildConfig.KOTLIN_PLUGIN_GROUP,
        version = BuildConfig.KOTLIN_PLUGIN_VERSION
    )

    override fun apply(target: Project) {
        target.extensions.create("codegen", CodegenGradleExtension::class.java)
        super.apply(target)
    }

    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        with(kotlinCompilation.target.project) {
            return provider {
                listOf(
                    SubpluginOption(key = "enabled", value = extensions.getByName("enabled") as String)
                )
            }
        }
    }
}