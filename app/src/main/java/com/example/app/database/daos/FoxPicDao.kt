package com.example.app.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app.database.foxPicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoxPicDao {

    @Query("SELECT * FROM foxpics WHERE id = :id")
    fun getFoxPic(id : Int): Flow<foxPicEntity>

    @Delete
    suspend fun deleteFoxPic(foxpic : foxPicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoxPic(foxPic: foxPicEntity): Long

    @Query("SELECT * FROM foxpics order by name ASC")
    suspend fun getAll(): Flow<List<foxPicEntity>>
}