package com.example.app.network

import android.util.Log
import com.example.app.model.FoxPic
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ApiFoxPic(val image: String, val link: String)

fun List<ApiFoxPic>.asDomainObjects(): List<FoxPic> {
    var list = this.map {
        it.asDomainObject()
    }
    return list
}

fun ApiFoxPic.asDomainObject(): FoxPic {
    Log.d("repo", "values: " + image + " " + link)
    return FoxPic("", image, Date())
}
