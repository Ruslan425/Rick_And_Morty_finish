// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        val navVersion = "2.5.1"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

plugins {
    id ("com.android.application") version "7.2.1" apply false
    id ("com.android.library") version "7.2.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.10" apply false
}


