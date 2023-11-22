package com.example.app.network.service

import com.example.app.model.FoxPic
import retrofit2.Response
import retrofit2.http.GET

interface FoxPicService{

    @GET
    suspend fun getFoxPic(): Response<FoxPic>

}