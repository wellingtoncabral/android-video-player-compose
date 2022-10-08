pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "VideoPlayerCompose"
include(":app")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":core:ui")
include(":core:data")
include(":core:data-network")
include(":core:navigation")
include(":feature:games")
include(":feature:videos")
include(":video-player")
include(":feature:game-detail")
include(":feature:store-detail")
