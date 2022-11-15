import com.android.build.gradle.LibraryExtension
import com.wcabral.videoplayercompose.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyAndroidConfig()
        }
    }

    private fun Project.applyPlugins() {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
    }

    private fun Project.applyAndroidConfig() {
        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = 32
        }
    }
}