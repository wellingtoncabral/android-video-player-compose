import com.wcabral.videoplayercompose.addDebugImplementation
import com.wcabral.videoplayercompose.addImplementation
import com.wcabral.videoplayercompose.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            featureConfig()
            dependenciesConfig()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
    }

    /**
     * Changes the feature configuration
     * Example:
     * extensions.configure<LibraryExtension> {
     *     defaultConfig {
     *         testInstrumentationRunner = "add instrumentation runner here"
     *     }
     * }
     */
    private fun Project.featureConfig() {}

    private fun Project.dependenciesConfig() {
        dependencies {
            addImplementation(project(":core-model"))
            addImplementation(project(":core-ui"))
            addImplementation(project(":core-designsystem"))
            addImplementation(project(":core-data"))
            addImplementation(project(":core-common"))

            addImplementation(libs.findLibrary("androidx.core.ktx").get())

            addImplementation(libs.findLibrary("androidx.compose.ui").get())
            addImplementation(libs.findLibrary("androidx.compose.material").get())
            addImplementation(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
            addDebugImplementation(libs.findLibrary("androidx.compose.ui.tooling").get())
            // TODO : Remove this dependency once we upgrade to Android Studio Dolphin b/228889042
            // These dependencies are currently necessary to render Compose previews
            addDebugImplementation(libs.findLibrary("androidx.customview.poolingcontainer").get())

            addImplementation(libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())

            addImplementation(libs.findLibrary("kotlinx.coroutines.android").get())

            addImplementation(libs.findLibrary("koin.android").get())

//            add("implementation", project(":core-navigation"))

//            addTestImplementation(project(":core-testing"))
//            addAndroidTestImplementation(project(":core-testing"))

//            addImplementation(libs.findLibrary("coil.kt").get())
//            addImplementation(libs.findLibrary("coil.kt.compose").get())
        }
    }
}