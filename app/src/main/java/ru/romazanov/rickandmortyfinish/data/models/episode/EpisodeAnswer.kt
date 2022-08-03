package ru.romazanov.rickandmortyfinish.data.models.episode

import com.google.gson.annotations.SerializedName
import ru.romazanov.rickandmortyfinish.data.models.Info

data class EpisodeAnswer(
    val info: Info,
    @SerializedName("results")
    val episodes: List<Episode>
)