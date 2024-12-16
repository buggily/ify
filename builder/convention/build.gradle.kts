plugins {
    `kotlin-dsl`
}

group = "com.buggily.ify.builder"

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
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
        register("androidLibrary") {
            id = "ify.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "ify.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("kotlinHilt") {
            id = "ify.kotlin.hilt"
            implementationClass = "KotlinHiltConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "ify.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kotlinLibraryLocal") {
            id = "ify.kotlin.library.local"
            implementationClass = "KotlinLibraryLocalConventionPlugin"
        }
        register("kotlinLibraryRemote") {
            id = "ify.kotlin.library.remote"
            implementationClass = "KotlinLibraryRemoteConventionPlugin"
        }
        register("kotlinTest") {
            id = "ify.kotlin.test"
            implementationClass = "KotlinTestConventionPlugin"
        }
    }
}
