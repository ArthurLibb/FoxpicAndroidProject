package com.example.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.database.daos.FoxPicDao
import com.example.app.model.FoxPic
import com.example.app.utill.FoxPicParser

@Database(entities = [FoxPicEntity::class], version = 5, exportSchema = false)
@TypeConverters(FoxPicParser::class)
abstract class RoomDB() : RoomDatabase() {

    abstract fun foxPicDao() : FoxPicDao

    companion object{
        @Volatile
        private var instance : RoomDB? = null

        fun getInstance(context: Context): RoomDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): RoomDB =
            Room.databaseBuilder(
                context,
                RoomDB::class.java,
                "android-App-FoxPic"
            ).fallbackToDestructiveMigration()
                .build()
    }
}