package ru.romazanov.rickandmortyfinish.data.interactors.character


import androidx.paging.*
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.mediators.CharacterRemoteMediator
import ru.romazanov.rickandmortyfinish.data.room.Database
import javax.inject.Inject

class CharacterInteractor @Inject constructor(
    private val repository: Repository,
    private val database: Database
) {

    private val pagingSource = { database.getCharacterDao().getAll() }

    @OptIn(ExperimentalPagingApi::class)
    fun getCharacterStream(query: Map<String, String>) =
        Pager(
            config = PagingConfig(
                pageSize = 3,
            ),
            remoteMediator = CharacterRemoteMediator(
                query,
                repository,
                database
            ),
            pagingSourceFactory = pagingSource
        ).flow
}