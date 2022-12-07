import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.android.library")
            apply("ify.android.hilt")
            apply("org.jetbrains.kotlin.kapt")
        }

        val libs: VersionCatalog = getLibs()

        dependencies {
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:model"))
            add("implementation", project(":core:domain"))

            add("implementation", libs.getLib("androidx.hilt.navigation.compose"))
            add("kapt", libs.getLib("androidx.hilt.compiler"))

            add("implementation", libs.getLib("androidx.lifecycle.runtime.compose"))
            add("implementation", libs.getLib("androidx.lifecycle.viewModel.compose"))

            add("implementation", libs.getLib("androidx.compose.material3"))
        }
    }
}
