plugins {
    id("ify.kotlin.library")
    id("ify.kotlin.library.remote")
}

dependencies {
    implementation(project(":core:remote"))
}
