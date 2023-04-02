plugins {
    id("ify.android.library.feature")
    id("ify.android.library.compose")
}

android {
    namespace = "com.buggily.ify.feature.age"
}

dependencies {
    implementation(project(":domain:age"))
    implementation(project(":data:age"))
}
