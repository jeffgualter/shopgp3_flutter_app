import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dev.flutter.flutter-gradle-plugin")
}

// ✅ Carrega as propriedades da keystore
val keystoreProperties = Properties().apply {
    val keystoreFile = rootProject.file("android/key.properties")
    if (keystoreFile.exists()) {
        load(FileInputStream(keystoreFile))
    }
}

android {
    namespace = "com.example.shopgp3_flutter_app"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = "27.0.12077973"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        applicationId = "com.example.shopgp3_flutter_app"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    signingConfigs {
        create("release") {
val storePassword = keystoreProperties["storePassword"]?.toString() ?: ""
val keyAlias = keystoreProperties["keyAlias"]?.toString() ?: ""
val keyPassword = keystoreProperties["keyPassword"]?.toString() ?: ""

storeFile = file("android/app/shopgp3-release-key.jks")
this.storePassword = storePassword
this.keyAlias = keyAlias
this.keyPassword = keyPassword

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

flutter {
    source = "../.."
}
