plugins {
    id("plugin.android.library")
    id("plugin.android.library.compose")
    id("plugin.android.feature")
}

dependencies {
    implementation(project(":video-player"))
}
