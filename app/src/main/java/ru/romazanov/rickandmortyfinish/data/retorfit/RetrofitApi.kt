package ru.romazanov.rickandmortyfinish.data.retorfit

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.romazanov.rickandmortyfinish.data.models.character.CharacterAnswer
import ru.romazanov.rickandmortyfinish.data.models.location.LocationAnswer

interface RetrofitApi {

    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: String,
        @QueryMap query: Map<String, String>
    ): Response<CharacterAnswer>

    @GET("location")
    fun getLocation(
        @Query("page") page: String,
        @QueryMap query: Map<String, String>
    ): Observable<LocationAnswer>

}