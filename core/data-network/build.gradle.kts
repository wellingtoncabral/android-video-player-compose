import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    id("plugin.android.library")
    id("plugin.android.koin")
}

android {
    defaultConfig {
        buildConfigField("String", "API_KEY", getApiKey())
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
}

fun getApiKey(): String {
    val fl = rootProject.file("apikey.properties")

    if (fl.exists()) {
        val properties = Properties()
        properties.load(FileInputStream(fl))
        return properties.getProperty("API_KEY")
    } else {
        throw FileNotFoundException()
    }
}
