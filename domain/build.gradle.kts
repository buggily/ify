plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")

    kotlin("kapt")
}

java {
    sourceCompatibility = Version.JAVA
    targetCompatibility = Version.JAVA
}

dependencies {
    implementation(project(":data"))

    implementation(Dependency.Hilt.Core.IDENTITY)
    kapt(Dependency.Hilt.Core.COMPILER)
}
