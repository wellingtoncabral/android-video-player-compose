plugins {
    id("plugin.android.library")
    id("plugin.android.library.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)
    // TODO : Remove these dependency once we upgrade to Android Studio Dolphin b/228889042
    // These dependencies are currently necessary to render Compose previews
    debugImplementation(libs.androidx.customview.poolingcontainer)
}
