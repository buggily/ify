object Dependency {

    object Kotlin {
        object Core {
            const val KTX = "androidx.core:core-ktx:${Version.Kotlin.CORE}"
        }
    }

    object Hilt {
        const val IDENTITY = "com.google.dagger:hilt-android:${Version.Hilt.IDENTITY}"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:${Version.Hilt.IDENTITY}"

        object Core {
            const val IDENTITY = "com.google.dagger:hilt-core:${Version.Hilt.IDENTITY}"
            const val COMPILER = "com.google.dagger:hilt-compiler:${Version.Hilt.IDENTITY}"
        }

        object Android {
            const val NAVIGATION = "androidx.hilt:hilt-navigation-compose:${Version.Hilt.ANDROID}"
            const val COMPILER = "androidx.hilt:hilt-compiler:${Version.Hilt.ANDROID}"
        }
    }

    object Retrofit {
        const val IDENTITY = "com.squareup.retrofit2:retrofit:${Version.Retrofit.IDENTITY}"

        const val CONVERTER = "com.jakewharton.retrofit:" +
                "retrofit2-kotlinx-serialization-converter:" +
                Version.Retrofit.SERIALIZATION
    }

    object Serialization {
        const val IDENTITY = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.Serialization.IDENTITY}"
    }

    object Compose {
        const val ACTIVITY = "androidx.activity:activity-compose:${Version.Compose.ACTIVITY}"
        const val MATERIAL = "androidx.compose.material3:material3:${Version.Compose.MATERIAL}"
        const val NAVIGATION = "androidx.navigation:navigation-compose:${Version.Compose.NAVIGATION}"

        object Ui {

            const val IDENTITY = "androidx.compose.ui:ui:${Version.Compose.IDENTITY}"

            object Tooling {
                const val IDENTITY = "androidx.compose.ui:ui-tooling:${Version.Compose.Tooling.IDENTITY}"
                const val PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Version.Compose.Tooling.IDENTITY}"
            }
        }

        object Lifecycle {

            const val IDENTITY = "androidx.lifecycle:lifecycle-runtime-compose:${Version.Compose.LIFECYCLE}"
            const val KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Compose.LIFECYCLE}"

            object ViewModel {
                const val IDENTITY = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.Compose.LIFECYCLE}"
                const val KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Compose.LIFECYCLE}"
            }
        }
    }

    object Coil {
        const val IDENTITY = "io.coil-kt:coil-compose:${Version.Coil.IDENTITY}"
    }
}