plugins {
    id("ify.android.library.domain")
}

android {
    namespace = "com.buggily.ify.domain.nationality"
}

dependencies {
    implementation(project(":data:nationality"))
}
