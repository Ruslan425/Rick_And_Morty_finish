package ru.romazanov.rickandmortyfinish.data.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import ru.romazanov.rickandmortyfinish.data.room.entitys.CharacterEntity


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(characters: List<CharacterEntity>)


    @Query("DELETE FROM characters")
    suspend fun deleteAll()
    
    @Query("SELECT * FROM characters")
    fun getAll(): PagingSource<Int, CharacterEntity>

}