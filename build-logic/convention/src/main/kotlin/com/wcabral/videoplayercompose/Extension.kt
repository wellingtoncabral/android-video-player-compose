package com.wcabral.videoplayercompose

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun DependencyHandlerScope.addImplementation(dependencyName: Any) {
    add("implementation", dependencyName)
}

internal fun DependencyHandlerScope.addTestImplementation(dependencyName: Any) {
    add( "testImplementation", dependencyName)
}

internal fun DependencyHandlerScope.addAndroidTestImplementation(dependencyName: Any) {
    add("androidTestImplementation", dependencyName)
}

internal fun DependencyHandlerScope.addDebugImplementation(dependencyName: Any) {
    add( "debugImplementation", dependencyName)
}
