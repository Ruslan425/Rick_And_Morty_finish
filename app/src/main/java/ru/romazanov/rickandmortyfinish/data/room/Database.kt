package ru.romazanov.rickandmortyfinish.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.romazanov.rickandmortyfinish.data.room.dao.CharacterDao
import ru.romazanov.rickandmortyfinish.data.room.dao.RemoteKeysDao
import ru.romazanov.rickandmortyfinish.data.room.entitys.CharacterEntity
import ru.romazanov.rickandmortyfinish.data.room.entitys.RemoteKeys
import ru.romazanov.rickandmortyfinish.data.room.utils.ListConverter


@Database(
    entities = [CharacterEntity::class, RemoteKeys::class],
    version = 1,
)
@TypeConverters(ListConverter::class)
abstract class Database: RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

}