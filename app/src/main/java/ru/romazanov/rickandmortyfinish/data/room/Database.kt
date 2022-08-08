package ru.romazanov.rickandmortyfinish.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.romazanov.rickandmortyfinish.data.room.dao.CharacterDao
import ru.romazanov.rickandmortyfinish.data.room.dao.RemoteKeysDao
import ru.romazanov.rickandmortyfinish.data.room.entitys.CharacterEntity
import ru.romazanov.rickandmortyfinish.data.room.entitys.RemoteKeys


@Database(
    entities = [CharacterEntity::class, RemoteKeys::class],
    version = 1,
)
abstract class Database: RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

}