package com.buggily.ify

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

internal fun Project.configureKotlin(
    javaExtension: JavaPluginExtension,
) = with(javaExtension) {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
