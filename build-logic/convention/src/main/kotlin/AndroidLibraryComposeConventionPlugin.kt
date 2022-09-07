import com.android.build.gradle.LibraryExtension
import com.wcabral.videoplayercompose.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            composeConfig()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply("com.android.library")
    }

    private fun Project.composeConfig() {
        val extension = extensions.getByType<LibraryExtension>()
        configureAndroidCompose(extension)
    }
}
