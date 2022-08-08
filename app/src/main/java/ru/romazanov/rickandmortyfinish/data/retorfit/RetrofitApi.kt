package ru.romazanov.rickandmortyfinish.data.retorfit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.romazanov.rickandmortyfinish.data.models.character.CharacterAnswer


interface RetrofitApi {

    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: String,
        @QueryMap query: Map<String, String>
    ): Response<CharacterAnswer>

}