plugins {
    id("ify.android.library.feature")
    id("ify.android.library.compose")
}

android {
    namespace = "com.buggily.ify.feature.gender"
}

dependencies {
    implementation(project(":domain:gender"))
}
