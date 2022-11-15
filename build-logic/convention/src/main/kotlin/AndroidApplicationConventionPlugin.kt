import com.android.build.api.dsl.ApplicationExtension
import com.wcabral.videoplayercompose.addImplementation
import com.wcabral.videoplayercompose.configureKotlinAndroid
import com.wcabral.videoplayercompose.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            androidConfig()
//            dependenciesConfig()
        }
    }

    private fun Project.applyPlugins() {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }
    }

    private fun Project.androidConfig() {
        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = 32
        }
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            addImplementation(libs.findLibrary("androidx.core.ktx").get())
            addImplementation(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
        }
    }
}