
import com.android.build.api.dsl.LibraryExtension
import com.buggily.ify.configureKotlinAndroid
import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
        }

        dependencies {
            add("testImplementation", project(":core:test"))

            with(getLibs()) {
                add("testImplementation", getLib("junit"))
                add("testImplementation", getLib("mockk"))

                add("implementation", getLib("kotlinx.coroutines.android"))
                add("testImplementation", getLib("kotlinx.coroutines.test"))
            }
        }
    }
}
