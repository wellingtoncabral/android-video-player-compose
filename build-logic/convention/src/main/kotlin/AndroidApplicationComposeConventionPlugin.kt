import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.wcabral.videoplayercompose.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            configureAndroidCompose(extensions.getByType<BaseAppModuleExtension>())
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply("com.android.application")
    }
}