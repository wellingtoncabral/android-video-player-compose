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
            dependenciesConfig()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            // Modules
            addImplementation(project(":core:model"))
            addImplementation(project(":core:ui"))
            addImplementation(project(":core:designsystem"))
            addImplementation(project(":core:data"))
            addImplementation(project(":core:common"))
            addImplementation(project(":core:navigation"))

            // Android X
            addImplementation(libs.findLibrary("androidx.core.ktx").get())
            addImplementation(libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())

            // Compose
            addImplementation(libs.findLibrary("androidx.compose.ui").get())
            addImplementation(libs.findLibrary("androidx.compose.material3").get())
            addImplementation(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
            addDebugImplementation(libs.findLibrary("androidx.compose.ui.tooling").get())

            // Coroutines
            addImplementation(libs.findLibrary("kotlinx.coroutines.android").get())

            // Koin
            addImplementation(libs.findLibrary("koin.android").get())
            addImplementation(libs.findLibrary("koin.androidx.compose").get())
        }
    }
}