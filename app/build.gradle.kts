plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dagger.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.tw.baseproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tw.baseproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:configs:rest:retrofit"))
    implementation(project(":core:configs:sqlite:room"))
    implementation(project(":core:shared-resource"))
    implementation(project(":feature:movielist:domain"))
    implementation(project(":feature:movielist:api"))
    implementation(project(":feature:movielist:apiinfra"))
    implementation(project(":feature:movielist:presentation"))
    implementation(project(":feature:movielist:ui"))
    implementation(project(":feature:moviedetail:domain"))
    implementation(project(":feature:moviedetail:api"))
    implementation(project(":feature:moviedetail:apiinfra"))
    implementation(project(":feature:moviedetail:cache"))
    implementation(project(":feature:moviedetail:cacheinfra"))
    implementation(project(":feature:moviedetail:presentation"))
    implementation(project(":feature:moviedetail:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.google.material)
    implementation(libs.androidx.appcompat)

    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.material)

    implementation(libs.navigation.compose)

    // Injection
    implementation(libs.bundles.hilt)
    kapt(libs.dagger.hilt.compiler)

    // Http
    implementation(libs.bundles.retrofit)

    // Sqlite
    implementation(libs.bundles.room)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)
}