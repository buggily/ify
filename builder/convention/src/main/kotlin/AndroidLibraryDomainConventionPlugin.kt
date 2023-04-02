
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryDomainConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.android.library")
            apply("ify.android.hilt")
        }

        dependencies {
            add("implementation", project(":core:data"))
        }
    }
}
