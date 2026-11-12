plugins {
    // Con AGP 9 el soporte de Kotlin viene integrado: no hace falta el plugin kotlin-android
    alias(libs.plugins.android.application)
}

android {
    namespace = "es.iesoretania.fragmentosestaticosdinamicos"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "es.iesoretania.fragmentosestaticosdinamicos"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
    buildFeatures {
        viewBinding = true   // genera una clase Binding por cada layout
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)   // commit { }, replace<F>() ...
}
