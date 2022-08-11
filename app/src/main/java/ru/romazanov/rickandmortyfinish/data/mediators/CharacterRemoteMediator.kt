package ru.romazanov.rickandmortyfinish.data.mediators

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.room.Database
import ru.romazanov.rickandmortyfinish.data.room.entitys.CharacterEntity
import ru.romazanov.rickandmortyfinish.data.room.entitys.RemoteKeys


const val STARTED_PAGE = 0

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val query: Map<String, String>,
    private val repository: Repository,
    private val database: Database
) : RemoteMediator<Int, CharacterEntity>() {


    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {

        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                val key = remoteKeys?.nextKey?.minus(1) ?: STARTED_PAGE
                key
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

            val apiResponse = repository.getCharacter(page.toString(), query)

            val info = apiResponse.body()?.info
            val repos = apiResponse.body()?.character

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.getRemoteKeysDao().deleteAll()
                    database.getCharacterDao().deleteAll()
                }
                val prevKey = if (info?.prev == null) null else info.prev.substringAfter("=").split("&")[0].toInt()
                val nextKey = if (info?.next == null) null else info.next.substringAfter("=").split("&")[0].toInt()
                val keys = repos?.map {
                    RemoteKeys(characterId = it.id, nextKey = nextKey, prevKey = prevKey)
                }

                val repoEntity = repos?.map { it.toEntity() }

                database.getRemoteKeysDao().addAll(keys!!)
                database.getCharacterDao().addAll(repoEntity!!)
            }
            return MediatorResult.Success(endOfPaginationReached = info?.next == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                database.getRemoteKeysDao().getRemoteKeysFromId(character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                database.getRemoteKeysDao().getRemoteKeysFromId(character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { characterId->
                database.getRemoteKeysDao().getRemoteKeysFromId(characterId)
            }
        }
    }

}