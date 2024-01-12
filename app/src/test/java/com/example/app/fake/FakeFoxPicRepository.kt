package com.example.app.fake

import com.example.app.data.FoxPicRepository
import com.example.app.database.asDomainFoxPic
import com.example.app.model.FoxPic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFoxPicRepository : FoxPicRepository {
    var datasource = FakeDataSource
    override suspend fun getFoxpics(): Flow<List<FoxPic>> {
        return flow {
            emit(datasource.pics.map { it.asDomainFoxPic() })
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
