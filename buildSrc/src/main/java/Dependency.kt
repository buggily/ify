object Dependency {

    object Kotlin {
        object Core {
            const val KTX = "androidx.core:core-ktx:${Version.Kotlin.CORE}"
        }
    }

    object Hilt {
        const val IDENTITY = "com.google.dagger:hilt-android:${Version.Hilt.IDENTITY}"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:${Version.Hilt.IDENTITY}"

        object Android {
            const val NAVIGATION = "androidx.hilt:hilt-navigation:${Version.Hilt.ANDROID}"
            const val COMPILER = "androidx.hilt:hilt-compiler:${Version.Hilt.ANDROID}"
        }
    }

    object Room {
        const val IDENTITY = "androidx.room:room-runtime:${Version.Room.IDENTITY}"
        const val COMPILER = "androidx.room:room-compiler:${Version.Room.IDENTITY}"
        const val COROUTINES = "androidx.room:room-ktx:${Version.Room.IDENTITY}"
        const val PAGING = "androidx.room:room-paging:${Version.Room.PAGING}"
    }

    object Paging {
        const val IDENTITY = "androidx.paging:paging-runtime:${Version.Paging.IDENTITY}"
        const val CORE = "androidx.paging:paging-common:${Version.Paging.IDENTITY}"
        const val COMPOSE = "androidx.paging:paging-compose:${Version.Paging.COMPOSE}"
    }

    object Retrofit {
        const val IDENTITY = "com.squareup.retrofit2:retrofit:${Version.Retrofit.IDENTITY}"
    }

    object Compose {
        const val ACTIVITY = "androidx.activity:activity-compose:${Version.Compose.ACTIVITY}"
        const val MATERIAL = "androidx.compose.material3:material3:${Version.Compose.MATERIAL}"
        const val NAVIGATION = "androidx.navigation:navigation-compose:${Version.Compose.NAVIGATION}"

        object Ui {

            const val IDENTITY = "androidx.compose.ui:ui:${Version.Compose.IDENTITY}"

            object Tooling {
                const val IDENTITY =  "androidx.compose.ui:ui-tooling:${Version.Compose.Tooling.IDENTITY}"
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