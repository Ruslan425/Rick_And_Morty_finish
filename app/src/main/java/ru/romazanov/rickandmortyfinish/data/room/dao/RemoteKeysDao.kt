package ru.romazanov.rickandmortyfinish.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.romazanov.rickandmortyfinish.data.room.entitys.RemoteKeys


@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(remoteKeys: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE characterId = :characterId")
    suspend fun getRemoteKeysFromId(characterId: Int): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()

}