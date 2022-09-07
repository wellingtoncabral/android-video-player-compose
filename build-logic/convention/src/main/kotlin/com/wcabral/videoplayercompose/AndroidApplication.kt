package com.wcabral.videoplayercompose

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidApplication(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 33

        defaultConfig {
            minSdk = 22
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
            }
            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }

        packagingOptions {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}
