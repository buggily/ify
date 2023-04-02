plugins {
    id("ify.android.library.domain")
}

android {
    namespace = "com.buggily.ify.domain.age"
}

dependencies {
    implementation(project(":data:age"))
}
