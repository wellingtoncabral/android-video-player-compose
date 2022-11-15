plugins {
    `kotlin-dsl`
}

group = "com.wcabral.videoplayercompose.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "plugin.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "plugin.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "plugin.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "plugin.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "plugin.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "plugin.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("AndroidKoin") {
            id = "plugin.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }
        register("AndroidKoinCompose") {
            id = "plugin.android.koin.compose"
            implementationClass = "AndroidKoinComposeConventionPlugin"
        }
    }
}
