plugins {
    `kotlin-dsl`
}

group = "com.buggily.ify.builder"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "ify.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "ify.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidHilt") {
            id = "ify.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibraryFeature") {
            id = "ify.android.library.feature"
            implementationClass = "AndroidLibraryFeatureConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "ify.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "ify.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinHilt") {
            id = "ify.kotlin.hilt"
            implementationClass = "KotlinHiltConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "ify.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kotlinLibraryData") {
            id = "ify.kotlin.library.data"
            implementationClass = "KotlinLibraryDataConventionPlugin"
        }
        register("kotlinLibraryDomain") {
            id = "ify.kotlin.library.domain"
            implementationClass = "KotlinLibraryDomainConventionPlugin"
        }
        register("kotlinLibraryRemote") {
            id = "ify.kotlin.library.remote"
            implementationClass = "KotlinLibraryRemoteConventionPlugin"
        }
    }
}
