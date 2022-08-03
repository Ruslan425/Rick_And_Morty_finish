package ru.romazanov

import android.app.Application
import android.content.Context
import ru.romazanov.rickandmortyfinish.di.AppComponent
import ru.romazanov.rickandmortyfinish.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()
    }

}

val Context.appComponent: AppComponent
get() = when (this) {
    is App -> appComponent
    else -> this.applicationContext.appComponent
}