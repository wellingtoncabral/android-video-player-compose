plugins {
    id("plugin.android.library")
    id("plugin.android.koin")
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data-network"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}

