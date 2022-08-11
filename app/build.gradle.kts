plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
    id ("kotlin-android-extensions")
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
    val lifecycleVersion = "2.6.0-alpha01"

    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    implementation ("com.google.android.material:material:1.7.0-alpha03")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    implementation("androidx.activity:activity-ktx:1.5.1")
    implementation("androidx.fragment:fragment-ktx:1.5.1")

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
    kapt("com.google.dagger:dagger-compiler:2.43")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")


    // ROOM
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt ("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    implementation ("androidx.room:room-rxjava3:$roomVersion")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Paging library
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")

    //moxy
    implementation ("com.github.moxy-community:moxy:2.2.2")
    implementation ("com.github.moxy-community:moxy-ktx:2.2.2")
    implementation ("com.github.moxy-community:moxy-androidx:2.2.2")
    kapt ("com.github.moxy-community:moxy-compiler:2.2.2")

    //rxJava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation ("io.reactivex.rxjava3:rxkotlin:3.0.0")
}


