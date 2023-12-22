package com.example.app.data

import android.util.Log
import com.example.app.database.asDomainFoxPic
import com.example.app.database.asDomainFoxpics
import com.example.app.database.asEntity
import com.example.app.database.daos.FoxPicDao
import com.example.app.model.FoxPic
import com.example.app.network.service.FoxPicService
import com.example.app.network.service.getAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

interface FoxPicRepository{
    /*fun getFoxPic(id: String): Flow<FoxPic>*/
    fun getFoxpics(): Flow<List<FoxPic>>
    suspend fun addFoxPic(foxpic : FoxPic)
    suspend fun deleteFoxPic(foxPic: FoxPic)
    suspend fun getRandomFoxPic(): Flow<FoxPic>
}

class OfflineFoxPicRepository (private val foxPicDao: FoxPicDao, private val foxPicSerivice : FoxPicService) : FoxPicRepository{
    /*override fun getFoxPic(id : String): Flow<FoxPic>{
            return foxPicDao.getFoxPic(id.toInt()).map{
                it.asDomainFoxPic()
            }
    }*/

    override suspend fun addFoxPic(foxPic: FoxPic){
        AddFoxPicTORoomDb(foxPic)
    }

    override suspend fun deleteFoxPic(foxPic: FoxPic) {
        foxPicDao.deleteFoxPic(foxPic.asEntity())
    }


    override suspend fun getRandomFoxPic(): Flow<FoxPic>  {
        Log.d("repo", "getting random foxpic")
        return foxPicSerivice.getAsFlow().map { FoxPic("", it.image, Date()) }
    }

    override fun getFoxpics(): Flow<List<FoxPic>> {
        Log.d("repo", "getting all pics")
        return foxPicDao.getAll().map {
            it.asDomainFoxpics()
        }
    }

    private suspend fun AddFoxPicTORoomDb(foxPic: FoxPic){
        foxPicDao.addFoxPic(foxPic.asEntity())
    }
}