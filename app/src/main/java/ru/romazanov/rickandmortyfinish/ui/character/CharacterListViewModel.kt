package ru.romazanov.rickandmortyfinish.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.romazanov.rickandmortyfinish.data.interactors.character.CharacterInteractor
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import javax.inject.Inject


class CharacterListViewModel @Inject constructor(
    private val interactor: CharacterInteractor
) : ViewModel() {

    val listFlow: Flow<PagingData<Character>> =
        interactor.getCharacterStream(hashMapOf()).cachedIn(viewModelScope)


    fun list(query: Map<String, String>) {
        interactor.getCharacterStream(query = query)
    }


}
