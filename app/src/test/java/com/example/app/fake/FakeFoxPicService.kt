package com.example.app.fake

import com.example.app.network.ApiFoxPic
import com.example.app.network.service.FoxPicService

class FakeFoxPicService : FoxPicService {
    override suspend fun getFoxPic(): ApiFoxPic {
        return FakeDataSource.randomPic
    }
}
