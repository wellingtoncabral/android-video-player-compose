import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.applyPlugins()
    }

    private fun Project.applyPlugins() {
        with(pluginManager) {
            apply("java-library")
            apply("org.jetbrains.kotlin.jvm")
        }
    }
}
