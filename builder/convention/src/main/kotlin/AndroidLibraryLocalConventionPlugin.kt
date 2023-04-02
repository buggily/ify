import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryLocalConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.android.library")
            apply("ify.android.hilt")
            apply("org.jetbrains.kotlin.kapt")
        }

        dependencies {
            with(getLibs()) {
                add("implementation", getLib("androidx.room"))
                add("implementation", getLib("androidx.room.ktx"))
                add("kapt", getLib("androidx.room.compiler"))
            }
        }
    }
}
