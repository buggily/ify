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

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(project(":feature:age"))
    implementation(project(":feature:gender"))
    implementation(project(":feature:nationality"))

    implementation(project(":domain:age"))
    implementation(project(":domain:gender"))
    implementation(project(":domain:nationality"))

    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.lifecycle.viewModel)
}
