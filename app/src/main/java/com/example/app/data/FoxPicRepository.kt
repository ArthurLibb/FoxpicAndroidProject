package com.example.app.data

import com.example.app.database.daos.FoxPicDao
import com.example.app.model.FoxPic
import com.example.app.network.service.FoxPicService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FoxPicRepository{
    suspend fun getFoxPic(): FoxPic
    suspend fun getFoxpics(): List<FoxPic>
    suspend fun addFoxPic(foxpic : FoxPic)
}

class OfflineFoxPicRepository (private val foxPicDao: FoxPicDao,private val foxPicSerivice : FoxPicService) : FoxPicRepository{
    override suspend fun getFoxPic(): FoxPic{
            return foxPicSerivice.getFoxPic().body()!!
    }

    override suspend fun addFoxPic(foxPic: FoxPic){
        AddFoxPicTORoomDb(foxPic)
    }

    override suspend fun getFoxpics(): Flow<List<FoxPic>> {
        return foxPicDao.getAll().map {
            it.
        }
    }

    private suspend fun AddFoxPicTORoomDb(foxPic: FoxPic){
        foxPicDao.addFoxPic(foxPic)
    }
}