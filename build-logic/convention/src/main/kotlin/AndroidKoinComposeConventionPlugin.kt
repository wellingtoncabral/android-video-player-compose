import com.wcabral.videoplayercompose.addImplementation
import com.wcabral.videoplayercompose.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyDependencies()
    }

    private fun Project.applyDependencies() {
        dependencies {
            addImplementation(libs.findLibrary("koin.androidx.compose").get())
        }
    }
}