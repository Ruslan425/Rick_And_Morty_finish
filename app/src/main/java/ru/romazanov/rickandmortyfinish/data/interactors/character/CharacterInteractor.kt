package ru.romazanov.rickandmortyfinish.data.interactors.character


import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.mediators.CharacterRemoteMediator
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import ru.romazanov.rickandmortyfinish.data.pagingSources.CharacterPagingSource
import ru.romazanov.rickandmortyfinish.data.room.Database
import javax.inject.Inject

class CharacterInteractor @Inject constructor(
    private val repository: Repository,
    private val database: Database
) {



    @OptIn(ExperimentalPagingApi::class)
    fun getCharacterStream(query: Map<String, String>): Flow<PagingData<Character>> {

        return Pager(
            config = PagingConfig(
                pageSize = 1,
            ),
            remoteMediator = CharacterRemoteMediator(
                query,
                repository,
                database
            ),
            pagingSourceFactory = {

            }
        ).flow

    }

}