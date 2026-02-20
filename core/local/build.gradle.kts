plugins {
    id("ify.android.library")
    id("ify.android.hilt")
}

android {
    namespace = "com.buggily.ify.core.local"
}

dependencies {
    implementation(project(":local:age"))
    implementation(project(":local:gender"))
    implementation(project(":local:nationality"))

    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.runtime)
}
