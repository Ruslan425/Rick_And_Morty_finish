package ru.romazanov.rickandmortyfinish.data.models.location

import com.google.gson.annotations.SerializedName
import ru.romazanov.rickandmortyfinish.data.models.Info

data class LocationAnswer(
    val info: Info,
    @SerializedName("results")
    val location: List<Location>
)