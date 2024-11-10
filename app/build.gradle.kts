plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // dagger hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    //realm database
    id("io.realm.kotlin")

}

android {
    namespace = "com.example.opensooqtask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.opensooqtask"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // dagger hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    // gson
    implementation("com.google.code.gson:gson:2.10.1")

// realm data base
    implementation("io.realm.kotlin:library-base:2.0.0")

    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.3")

    // System UI by compose
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.0")

    //coil
    implementation("io.coil-kt:coil-compose:2.2.2")
}

kapt {
    correctErrorTypes = true
}