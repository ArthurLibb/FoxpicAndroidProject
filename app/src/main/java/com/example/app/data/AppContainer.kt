package com.example.app.data

import android.content.Context
import com.example.app.database.RoomDB
import com.example.app.network.service.FoxPicService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val foxPicRepo : FoxPicRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer{
    private val baseURL = "https://randomfox.ca/floof/?ref=apilist.fun"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType())
        ).baseUrl(baseURL).build()

    private val retrofitService : FoxPicService by lazy {
        retrofit.create(FoxPicService::class.java)
    }

    override val foxPicRepo: FoxPicRepository by lazy {
        OfflineFoxPicRepository(RoomDB.getInstance(context = context).foxPicDao(), retrofitService)
    }

}