import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

fun getGitCommitCount(): String {
    val process = ProcessBuilder("git", "rev-list", "--count", "--no-merges", "HEAD")
        .redirectErrorStream(true)
        .start()
    process.waitFor()
    return process.inputStream.bufferedReader().readText().trim()
}

android {
    namespace = "com.ctp.zentasks"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ctp.zentasks"
        minSdk = 26
        targetSdk = 34
        versionCode = 1800 + Integer.parseInt(getGitCommitCount())
        versionName = "v1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            val releaseKeystorePropertiesFile = rootProject.file("keystore/release.properties")
            val releaseKeystoreProperties = Properties().apply {
                load(FileInputStream(releaseKeystorePropertiesFile))
            }

            storeFile = rootProject.file(releaseKeystoreProperties["keyStore"] as String)
            storePassword = releaseKeystoreProperties["storePassword"] as String
            keyAlias = releaseKeystoreProperties["keyAlias"] as String
            keyPassword = releaseKeystoreProperties["keyPassword"] as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
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
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    ksp {
        arg(
            "room.schemaLocation",
            "$projectDir/schemas"
        )
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.animation:animation:1.7.4")

    //room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.12.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    //hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.hilt:hilt-work:1.2.0")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    //material icons extended
    implementation("androidx.compose.material:material-icons-extended:1.7.4")

    //splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    //gson
    implementation("com.google.code.gson:gson:2.10.1")

    //work manager
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}