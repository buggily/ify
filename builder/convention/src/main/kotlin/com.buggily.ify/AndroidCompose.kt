package com.buggily.ify

import ext.getLib
import ext.getLibs
import ext.getVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.dependencies
import com.android.build.api.dsl.CommonExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) = with(commonExtension) {

    val libs: VersionCatalog = getLibs()
    val bom: Provider<MinimalExternalModuleDependency> = libs.getLib("androidx-compose-bom")

    compileSdk = 33

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.getVersion("androidxComposeCompiler").toString()
    }

    dependencies {
        add("implementation", platform(bom))
        add("implementation", libs.getLib("androidx.compose.material3"))
    }
}
