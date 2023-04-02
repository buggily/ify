plugins {
    id("ify.android.library.data")
}

android {
    namespace = "com.buggily.ify.data.gender"
}

dependencies {
    implementation(project(":local:gender"))
    implementation(project(":remote:gender"))
}
