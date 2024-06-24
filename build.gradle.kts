// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kps) apply false
}

buildscript {
    repositories {
        @Suppress("DEPRECATION")
        //noinspection JcenterRepositoryObsolete
        jcenter()
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri(buildString { append("https://jitpack.io") }) }
        maven { url = uri("https://maven.google.com") }
    }
    dependencies {
        classpath(libs.hilt.plugin)
        classpath(libs.gradle.tools)
        classpath(libs.gradle.plugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.dokka.gradle.plugin)
    }
}

