package ru.romazanov.rickandmortyfinish.data


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import ru.romazanov.rickandmortyfinish.data.models.character.CharacterAnswer
import ru.romazanov.rickandmortyfinish.data.models.location.LocationAnswer
import ru.romazanov.rickandmortyfinish.data.retorfit.RetrofitApi
import ru.romazanov.rickandmortyfinish.data.room.Database
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RetrofitApi,
    private val database: Database
) {
    suspend fun getCharacter(page: String, query: Map<String, String>): Response<CharacterAnswer> {
        return api.getCharacter(page, query)
    }

    fun getLocation(page: String, query: Map<String, String>): Observable<LocationAnswer> {
        return api.getLocation(page, query)
    }

}