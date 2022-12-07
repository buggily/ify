import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryRemoteConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.kotlin.library")
            apply("ify.kotlin.hilt")
            apply("org.jetbrains.kotlin.kapt")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        val libs: VersionCatalog = getLibs()

        dependencies {
            add("implementation", project(":core:model"))

            add("implementation", libs.getLib("hilt.core"))
            add("kapt", libs.getLib("hilt.core.compiler"))

            add("implementation", libs.getLib("retrofit"))
            add("implementation", libs.getLib("retrofit.kotlin.serialization"))
        }
    }
}
