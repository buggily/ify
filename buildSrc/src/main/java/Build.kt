object Build {

    const val ID = "com.buggily.ify"
    const val RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    object Sdk {
        const val MIN = 23
        const val COMPILE = 33
        const val TARGET = 32
    }

    object OptIns {
        const val COMPOSE = "androidx.compose.foundation.ExperimentalFoundationApi"
        const val MATERIAL = "androidx.compose.material3.ExperimentalMaterial3Api"
        const val LIFECYCLE = "androidx.lifecycle.compose.ExperimentalLifecycleComposeApi"

        const val FLOW = "kotlinx.coroutines.FlowPreview"
        const val SERIALIZATION = "kotlinx.serialization.ExperimentalSerializationApi"
    }
}
