package ru.romazanov.rickandmortyfinish.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.romazanov.rickandmortyfinish.MainActivity
import ru.romazanov.rickandmortyfinish.di.module.AppModule
import ru.romazanov.rickandmortyfinish.ui.character.CharacterList
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun injectMainActivity(mainActivity: MainActivity)

    fun injectCharacterListFragment(characterList: CharacterList)

}