package com.example.app.network.service

import android.util.Log
import com.example.app.model.FoxPic
import com.example.app.network.ApiFoxPic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.GET

interface FoxPicService{

    @GET(".")
    suspend fun getFoxPic(): ApiFoxPic

}

fun FoxPicService.getAsFlow(): Flow<ApiFoxPic> = flow{
    emit(getFoxPic())
}