package com.buggily.ify

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion

internal fun Project.configureKotlin(
    javaExtension: JavaPluginExtension,
) = with(javaExtension) {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
}
