package ru.romazanov.rickandmortyfinish.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import ru.romazanov.rickandmortyfinish.data.room.Database
import ru.romazanov.rickandmortyfinish.data.room.entitys.RemoteKeys

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val query: Map<String, String>,
    private val repository: Repository,
    private val database: Database
) : RemoteMediator<String, Character>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<String, Character>
    ): MediatorResult {

        val page: String = when (loadType) {
            LoadType.REFRESH -> {
                "0"
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
        }

        try {

            val apiResponse = repository.getCharacter(page, query)

            val info = apiResponse.body()?.info
            val repos = apiResponse.body()?.character

            val endOfPaginationReached = info?.next == null

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.getRemoteKeysDao().deleteAll()
                    database.getCharacterDao().deleteAll()
                }
                val prevKey = if (info?.prev == null) null else info.prev.substringAfter("=").split("&")[0]
                val nextKey = if (info?.next != null) info.next.substringAfter("=").split("&")[0] else null
                val keys = repos?.map {
                    RemoteKeys(characterId = it.id, nextKey = nextKey, prevKey = prevKey)
                }
                val repoEntity = repos?.map { it.toEntity() }

                database.getRemoteKeysDao().addAll(keys!!)
                database.getCharacterDao().addAll(repoEntity!!)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<String, Character>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                database.getRemoteKeysDao().getRemoteKeysFromId(character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<String, Character>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                database.getRemoteKeysDao().getRemoteKeysFromId(character.id)
            }
    }

}