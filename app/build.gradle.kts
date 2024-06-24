import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kps)
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = 34
    namespace = "com.mstech.testapp"

    defaultConfig {
        applicationId = "com.mstech.testapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        abortOnError = false
    }

    buildFeatures {
        viewBinding = true
        this.dataBinding = true
        buildConfig = true
    }

    signingConfigs {
        create("release") {
            storeFile = file("../testapp_keystore.jks")
            storePassword = "testapp"
            keyAlias = "testapp"
            keyPassword = "testapp"
        }
    }

    val keystorePropertiesFile = rootProject.file("keystore.properties")
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))


    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", keystoreProperties["BASE_URL"].toString())
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("String", "BASE_URL", keystoreProperties["BASE_URL"].toString())
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    
    /****
     * Android
     ****/
    implementation(libs.androidx.activity)
    implementation(libs.androidx.core)
    implementation(libs.appcompat)
    implementation(libs.fragment)
    implementation(libs.material)
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    /****
     * Hilt
     ****/
    implementation(libs.hilt.android)
    implementation(libs.hilt.common)
    implementation(libs.activity)
    implementation(libs.androidx.constraintlayout)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    /****
     * Picasso
     ****/
    implementation (libs.picasso)

    /****
     * Retrofit
     ****/
    implementation(libs.retrofit)
    implementation(libs.retrofit.squareup.okhttp)
    implementation(libs.retrofit.squareup.okhttp.interceptor)
    implementation(libs.retrofit.squareup.okhttp.interceptor)
    implementation(libs.retrofit.squareup.moshi)
    implementation(libs.retrofit.squareup.moshi.kotlin)
    implementation(libs.retrofit.squareup.moshi.converter)
    implementation (libs.gson)
    ksp(libs.retrofit.squareup.moshi.codegen)


}


