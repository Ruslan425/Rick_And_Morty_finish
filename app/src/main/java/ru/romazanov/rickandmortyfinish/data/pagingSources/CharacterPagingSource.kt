package ru.romazanov.rickandmortyfinish.data.pagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.models.character.Character

class CharacterPagingSource(
    private val data: Repository,
    private val query: Map<String, String>
): PagingSource<String, Character>() {

    override fun getRefreshKey(state: PagingState<String, Character>): String {
        return "0"
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Character> {
        val page = params.key ?: "0"
        return try {
            val response = data.getCharacter(page = page, query = query)
            val info = response.body()?.info
            val repos = response.body()?.character
            val nextKey = if (info?.next != null) info.next.substringAfter("=").split("&")[0] else null
            val prevKey = if (info?.prev == null) null else info.prev.substringAfter("=").split("&")[0]

            LoadResult.Page(
                data = repos!!,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}