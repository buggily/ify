import org.gradle.api.JavaVersion

object Version {

    const val CODE = 1
    const val NAME = "1.0.0"

    val JAVA: JavaVersion = JavaVersion.VERSION_1_8

    object Kotlin {
        const val CORE = "1.8.0"
    }

    object Hilt {
        const val IDENTITY = "2.43.2"
        const val ANDROID = "1.0.0"
    }

    object Retrofit {
        const val IDENTITY = "2.9.0"
        const val SERIALIZATION = "0.8.0"
    }

    object Serialization {
        const val IDENTITY = "1.4.0-RC"
    }

    object Compose {

        const val IDENTITY = "1.3.0-alpha01"
        const val COMPILER = "1.2.0"
        const val ACTIVITY = "1.6.0-alpha05"
        const val MATERIAL = "1.0.0-alpha16"
        const val LIFECYCLE = "2.6.0-alpha01"
        const val NAVIGATION = "2.5.0"

        object Tooling {
            const val IDENTITY = "1.1.1"
        }
    }

    object Coil {
        const val IDENTITY = "2.1.0"
    }
}
