package ru.romazanov.rickandmortyfinish.ui.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.romazanov.rickandmortyfinish.data.interactors.character.CharacterInteractor
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import javax.inject.Inject


class CharacterListViewModel @Inject constructor(
    val interactor: CharacterInteractor
) : ViewModel() {

    fun searchTest(query: Map<String, String>): Flow<PagingData<Character>> {
        return interactor.getCharacterStream(query).map { characterEntity ->
            characterEntity.map { it.toModel() }
        }
            .cachedIn(viewModelScope)
    }
}

