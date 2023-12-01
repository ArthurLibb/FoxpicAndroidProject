package com.example.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.FoxPic

@Entity(tableName = "foxpics")
data class FoxPicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name : String = "",
    val link : String = ""
)

fun FoxPicEntity.asDomainFoxPic(): FoxPic{
    return FoxPic(this.name, this.link)
}

fun FoxPic.asEntity(): FoxPicEntity{
    return FoxPicEntity(name=this.name, link = this.link)
}

fun List<FoxPicEntity>.asDomainFoxpics() : List<FoxPic>{
    var list = this.map{
        FoxPic(it.name, it.link)
    }
    return list
}
