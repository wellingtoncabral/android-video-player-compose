plugins {
    id("plugin.android.library")
}

dependencies {
    implementation(project(":core-model"))
    implementation(project(":core-data-network"))

    implementation(libs.koin.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}

