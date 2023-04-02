plugins {
    id("ify.android.library.data")
}

android {
    namespace = "com.buggily.ify.data.age"
}

dependencies {
    implementation(project(":local:age"))
    implementation(project(":remote:age"))
}
