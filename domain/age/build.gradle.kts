plugins {
    id("ify.kotlin.library")
    id("ify.kotlin.library.domain")
}

dependencies {
    implementation(project(":data:age"))
}
