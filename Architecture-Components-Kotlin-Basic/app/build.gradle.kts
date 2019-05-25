import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

val NewsKotlinMvpApiKey: String by project

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.rohitss.aac"
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "ApiKey", NewsKotlinMvpApiKey)
        }
        getByName("release") {
            buildConfigField("String", "ApiKey", NewsKotlinMvpApiKey)
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    val supportVersion = "28.0.0"
    val roomVersion = "1.1.1"
    val archLifecycleVersion = "1.1.1"
    val ankoVersion = "0.10.5"
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation("com.android.support:appcompat-v7:$supportVersion")

    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    implementation("com.android.support:design:$supportVersion")
    implementation("com.android.support:cardview-v7:$supportVersion")
    implementation("org.jetbrains.anko:anko-commons:$ankoVersion")
    implementation("com.amitshekhar.android:android-networking:1.0.1")

    // Room components
    implementation("com.android.support:support-vector-drawable:$supportVersion")
    kapt("android.arch.persistence.room:compiler:$roomVersion")
    implementation("android.arch.persistence.room:runtime:$roomVersion")

    // Lifecycle components
    kapt("android.arch.lifecycle:compiler:$archLifecycleVersion")
    implementation("android.arch.lifecycle:extensions:$archLifecycleVersion")
}
