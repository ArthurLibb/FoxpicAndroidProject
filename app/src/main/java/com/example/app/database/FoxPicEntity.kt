package com.example.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.FoxPic
import java.util.Date

@Entity(tableName = "foxpics", primaryKeys = ["link", "date"])
data class FoxPicEntity(
    val name : String = "",
    val link : String = "",
    val date: Date = Date()
)

fun FoxPicEntity.asDomainFoxPic(): FoxPic{
    return FoxPic(this.name, this.link, this.date)
}

fun FoxPic.asEntity(): FoxPicEntity{
    return FoxPicEntity(name=this.name, link = this.link, date = this.date)
}

fun List<FoxPicEntity>.asDomainFoxpics() : List<FoxPic>{
    var list = this.map{
        FoxPic(it.name, it.link, it.date)
    }
    return list
}
