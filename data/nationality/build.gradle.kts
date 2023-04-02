plugins {
    id("ify.android.library.data")
}

android {
    namespace = "com.buggily.ify.data.nationality"
}

dependencies {
    implementation(project(":local:nationality"))
    implementation(project(":remote:nationality"))
}
