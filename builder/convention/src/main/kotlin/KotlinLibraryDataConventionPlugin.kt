import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryDataConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("ify.kotlin.library")
            apply("ify.kotlin.hilt")
        }

        dependencies {
            add("implementation", project(":core:model"))
        }
    }
}
