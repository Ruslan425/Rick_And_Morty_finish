package ru.romazanov.rickandmortyfinish.data.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import ru.romazanov.rickandmortyfinish.data.models.character.Location
import ru.romazanov.rickandmortyfinish.data.models.character.Origin


@Entity(tableName = "characters")
class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val created: String,

    val episode: List<String>,
    val gender: String,
    val image: String,
    @Embedded(prefix = "location_")
    val location: Location,
    @ColumnInfo(name = "character_name")
    val name: String,
    @Embedded(prefix = "origin_")
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    @ColumnInfo(name = "character_url")
    val url: String
) {

    fun toModel(): Character {
        return Character(
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
