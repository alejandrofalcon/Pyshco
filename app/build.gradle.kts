plugins {
    id("com.android.application")
id( "com.google.gms.google-services")

}


android {
    namespace = "com.example.pyshco"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pyshco"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Firebase BOM (Bill of Materials) para gestionar las versiones
    implementation(platform("com.google.firebase:firebase-bom:28.4.0"))

    // Dependencias Firebase
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-storage")

    // Dependencia de Google Play Services Auth
    implementation("com.google.android.gms:play-services-auth:21.1.1")

    // Car UI Library (asegúrate de que esta biblioteca sea necesaria para tu proyecto)
    implementation("com.android.car.ui:car-ui-lib:2.6.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Otras dependencias
    implementation("de.hdodenhof:circleimageview:1.3.0")
    implementation("com.github.bumptech.glide:glide:3.7.0")

}
