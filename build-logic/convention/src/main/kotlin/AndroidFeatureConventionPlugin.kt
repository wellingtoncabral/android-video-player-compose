import com.wcabral.videoplayercompose.addImplementation
import com.wcabral.videoplayercompose.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("plugin.android.koin")
            apply("plugin.android.koin.compose")
        }
    }

    private fun Project.applyDependencies() {
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

            // Coroutines
            addImplementation(libs.findLibrary("kotlinx.coroutines.android").get())
        }
    }
}