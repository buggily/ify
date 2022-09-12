import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")

    kotlin("kapt")
    kotlin("plugin.serialization") version "1.7.0"
}

java {
    sourceCompatibility = Version.JAVA
    targetCompatibility = Version.JAVA
}

dependencies {
    implementation(Dependency.Hilt.Core.IDENTITY)
    kapt(Dependency.Hilt.Core.COMPILER)

    implementation(Dependency.Retrofit.IDENTITY)
    implementation(Dependency.Retrofit.CONVERTER)
    implementation(Dependency.Serialization.IDENTITY)
}

tasks.withType(KotlinCompile::class).configureEach {
    val optIns: List<String> = listOf(
        Build.OptIns.SERIALIZATION,
    ).map {
        "-opt-in=$it"
    }

    kotlinOptions.freeCompilerArgs += optIns
}
