package com.example.app.fake

import android.util.Log
import com.example.app.data.FoxPicRepository
import com.example.app.database.asDomainFoxPic
import com.example.app.model.FoxPic
import com.example.app.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFoxPicRepository : FoxPicRepository {
    override suspend fun getFoxpics(): Flow<List<FoxPic>> = flow{
        FakeDataSource.pics.map {
            it.asDomainFoxPic()
        }
    }

    override suspend fun addFoxPic(foxpic: FoxPic) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFoxPic(foxPic: FoxPic) {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomFoxPic(): Flow<FoxPic> {
        TODO("Not yet implemented")
    }
}