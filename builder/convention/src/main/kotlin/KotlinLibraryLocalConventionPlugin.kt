import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryLocalConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.kotlin.library")
            apply("ify.kotlin.hilt")
        }

        dependencies {
            with(getLibs()) {
                add("implementation", getLib("androidx.room.core"))
                add("implementation", getLib("kotlinx.coroutines.core"))
            }
        }
    }
}
