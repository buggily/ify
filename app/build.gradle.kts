plugins {
    id("ify.android.application")
    id("ify.android.application.compose")
    id("ify.android.hilt")
}

android {

    namespace = "com.buggily.ify"

    defaultConfig {
        applicationId = namespace

        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
            )
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:domain"))

    implementation(project(":feature:age"))
    implementation(project(":feature:gender"))
    implementation(project(":feature:nationality"))

    implementation(project(":domain:age"))
    implementation(project(":domain:gender"))
    implementation(project(":domain:nationality"))

    implementation(libs.kotlin)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
}
