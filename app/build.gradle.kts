plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.rushikesh.tasks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rushikesh.tasks"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    sourceSets {
        getByName("androidTest") {
            java.srcDirs("src/sharedTest/java")
        }
        getByName("test") {
            java.srcDirs("src/sharedTest/java")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
     debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit core
    implementation(libs.retrofit)

    // Gson converter for parsing JSON
    implementation(libs.converter.gson)

    // ViewModel for Jetpack Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation("androidx.navigation:navigation-compose:2.7.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    // For Coroutine Testing
    testImplementation(libs.kotlinx.coroutines.test)
    // Core Mockito library
    testImplementation("org.mockito:mockito-core:5.7.0")
    // Mockito support for Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")

    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0") // Use the latest version available
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0") // Use the latest version available
}