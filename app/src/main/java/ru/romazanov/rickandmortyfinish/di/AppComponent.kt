package ru.romazanov.rickandmortyfinish.di

import dagger.Component
import ru.romazanov.rickandmortyfinish.MainActivity
import ru.romazanov.rickandmortyfinish.di.module.AppModule
import ru.romazanov.rickandmortyfinish.ui.character.CharacterList


@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

    fun injectCharacterListFragment(characterList: CharacterList)

}