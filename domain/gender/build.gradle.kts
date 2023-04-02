plugins {
    id("ify.android.library.domain")
}

android {
    namespace = "com.buggily.ify.domain.gender"
}

dependencies {
    implementation(project(":data:gender"))
}
