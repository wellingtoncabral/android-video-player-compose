import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.wcabral.videoplayercompose.addAndroidTestImplementation
import com.wcabral.videoplayercompose.addDebugImplementation
import com.wcabral.videoplayercompose.addImplementation
import com.wcabral.videoplayercompose.configureAndroidCompose
import com.wcabral.videoplayercompose.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            configureAndroidCompose(extensions.getByType<BaseAppModuleExtension>())
            dependenciesConfig()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply("com.android.application")
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            addImplementation(libs.findLibrary("androidx.compose.ui").get())
            addImplementation(libs.findLibrary("androidx.compose.material").get())
            addImplementation(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
            addImplementation(libs.findLibrary("androidx.activity.compose").get())
            addDebugImplementation(libs.findLibrary("androidx.compose.ui.tooling").get())
            addDebugImplementation(libs.findLibrary("androidx.compose.ui.test.manifest").get())

            // Android Test
            addAndroidTestImplementation(libs.findLibrary("androidx.compose.ui.test.junit4").get())
        }
    }

}