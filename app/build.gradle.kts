plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk  = 32

    defaultConfig {
        applicationId = "ru.romazanov.rickandmortyfinish"
        minSdk =  24
        targetSdk  = 32
        versionCode =  1
        versionName  = "1.0"

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }


}

dependencies {

    val navVersion = "2.5.1"
    val roomVersion = "2.4.2"
    val pagingVersion = "3.1.1"

    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    //Dagger2
    implementation ("com.google.dagger:dagger:2.43")
    implementation ("com.google.dagger:dagger-android:2.35.1")
    implementation ("com.google.dagger:dagger-android-support:2.35.1")
    annotationProcessor ("com.google.dagger:dagger-compiler:2.43")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.0")


    // ROOM
    implementation ("androidx.room:room-runtime:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Paging library
    implementation("androidx.paging:paging-runtime:$pagingVersion")
}
