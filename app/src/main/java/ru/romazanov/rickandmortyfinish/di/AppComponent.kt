package ru.romazanov.rickandmortyfinish.di

import dagger.Component
import ru.romazanov.rickandmortyfinish.MainActivity
import ru.romazanov.rickandmortyfinish.di.module.ViewModelModule
import ru.romazanov.rickandmortyfinish.ui.character.CharacterList
import javax.inject.Singleton



@Component(
    modules = [
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

    fun injectCharacterListFragment(characterList: CharacterList)

}