package com.example.app.network

import com.example.app.model.FoxPic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiFoxPic(val name : String, val link : String) {}

fun List<ApiFoxPic>.asDomainObjects(): List<FoxPic>{
    var list = this.map {
        it.asDomainObject()
    }
    return list
}

fun ApiFoxPic.asDomainObject(): FoxPic{
    return FoxPic(name, link)
}