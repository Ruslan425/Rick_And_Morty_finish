package ru.romazanov.rickandmortyfinish.data


import android.util.Log
import retrofit2.Response
import ru.romazanov.rickandmortyfinish.data.models.character.CharacterAnswer
import ru.romazanov.rickandmortyfinish.data.retorfit.RetrofitApi
import ru.romazanov.rickandmortyfinish.data.room.Database
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RetrofitApi,
    private val database: Database
) {
    suspend fun getCharacter(page: String, query: Map<String, String>): Response<CharacterAnswer> {
        Log.e("API", page)
        return api.getCharacter(page, query)
    }

}