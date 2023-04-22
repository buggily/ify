plugins {
    `kotlin-dsl`
}

group = "com.buggily.ify.builder"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
        register("androidLibraryCompose") {
            id = "ify.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryData") {
            id = "ify.android.library.data"
            implementationClass = "AndroidLibraryDataConventionPlugin"
        }
        register("androidLibraryDomain") {
            id = "ify.android.library.domain"
            implementationClass = "AndroidLibraryDomainConventionPlugin"
        }
        register("androidLibraryFeature") {
            id = "ify.android.library.feature"
            implementationClass = "AndroidLibraryFeatureConventionPlugin"
        }
        register("androidLibraryLocal") {
            id = "ify.android.library.local"
            implementationClass = "AndroidLibraryLocalConventionPlugin"
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
        register("kotlinLibraryRemote") {
            id = "ify.kotlin.library.remote"
            implementationClass = "KotlinLibraryRemoteConventionPlugin"
        }
    }
}
