package ru.romazanov.rickandmortyfinish.data.models.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.romazanov.rickandmortyfinish.data.room.entitys.CharacterEntity

@Parcelize
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable {

    fun toEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            created = created,
            episode = episode,
            gender = gender,
            image = image,
            location = location,
            name = name,
            origin = origin,
            species = species,
            status = status,
            type = type,
            url = url
        )
    }
}