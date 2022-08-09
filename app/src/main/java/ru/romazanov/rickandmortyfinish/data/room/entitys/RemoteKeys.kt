package ru.romazanov.rickandmortyfinish.data.room.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val characterId: Int,
    val nextKey: Int?,
    val prevKey: Int?
)
