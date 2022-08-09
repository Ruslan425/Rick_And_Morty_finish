package ru.romazanov.rickandmortyfinish.ui.character

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
    interactor: CharacterInteractor
) : ViewModel() {

    val listFlow: Flow<PagingData<Character>> =
        interactor.getCharacterStream(hashMapOf())
            .map{ it -> it.map { it.toModel() }}
            .cachedIn(viewModelScope)

}
