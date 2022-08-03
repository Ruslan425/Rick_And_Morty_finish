package ru.romazanov.rickandmortyfinish.ui.character

import androidx.lifecycle.ViewModel
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.interactors.CharacterInteractor
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    val characterInteractor: CharacterInteractor
) : ViewModel() {

    fun test(): String {
        return "test"
    }
}