import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryRemoteConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.kotlin.library")
            apply("ify.kotlin.hilt")
            apply("org.jetbrains.kotlin.kapt")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        dependencies {
            add("implementation", project(":core:remote"))

            with(getLibs()) {
                add("implementation", getLib("retrofit"))
                add("implementation", getLib("retrofit.kotlin.serialization"))
            }
        }
    }
}
