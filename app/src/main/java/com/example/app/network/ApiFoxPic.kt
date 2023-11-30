package com.example.app.network

import com.example.app.model.FoxPic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiFoxPic(val name : String, val link : String) {

}

/*fun Flow<List<ApiFoxPic>>.asDomainObjects(): Flow<List<FoxPic>>{
    var lijst = this.map {
        it.asDomainObjects()
    }
    return lijst
}*/

fun List<ApiFoxPic>.asDomainObjects(): List<FoxPic>{
    var list = this.map {
        FoxPic(it.name, it.link)
    }
    return list
}

suspend fun Flow<ApiFoxPic>.asDomainObject(): FoxPic{
    var domainobj : FoxPic? = null
    this.collect{
        domainobj =  FoxPic(name = it.name, link = it.link)
    }
    return domainobj!!
}