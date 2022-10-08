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
    // Modules
    implementation(project(":core:designsystem"))
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Android X
    implementation(libs.androidx.core.ktx)

    // Compose
    implementation(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
}