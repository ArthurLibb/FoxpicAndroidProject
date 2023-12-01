package com.example.app.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app.database.FoxPicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoxPicDao {

    @Query("SELECT * FROM foxpics WHERE id = :id")
    fun getFoxPic(id : Int): Flow<FoxPicEntity>

    @Delete
    suspend fun deleteFoxPic(foxpic : FoxPicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoxPic(foxPic: FoxPicEntity): Long

    @Query("SELECT * FROM foxpics order by name ASC")
    fun getAll(): Flow<List<FoxPicEntity>>
}