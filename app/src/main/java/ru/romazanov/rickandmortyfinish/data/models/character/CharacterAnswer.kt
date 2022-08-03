package ru.romazanov.rickandmortyfinish.data.models.character

import com.google.gson.annotations.SerializedName
import ru.romazanov.rickandmortyfinish.data.models.Info

data class CharacterAnswer(
    val info: Info,
    @SerializedName("results")
    val character: List<Character>
)