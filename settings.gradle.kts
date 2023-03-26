@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("builder")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ify"

include(":app")

include(":core:ui")
include(":core:domain")
include(":core:remote")
include(":core:model")

include(":feature:age")
include(":feature:gender")
include(":feature:nationality")

include(":domain:age")
include(":domain:gender")
include(":domain:nationality")

include(":data:age")
include(":data:gender")
include(":data:nationality")

include(":remote:age")
include(":remote:gender")
include(":remote:nationality")
