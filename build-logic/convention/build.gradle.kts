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
    }
}