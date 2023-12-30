package com.example.app.fake

import com.example.app.database.FoxPicEntity
import com.example.app.network.ApiFoxPic
import java.util.Date

object FakeDataSource {
    const val nameOne = "cute fox"
    const val linkOne = "https://randomfox.ca/images/29.jpg"
    val dateOne = Date(2023, 6, 25, 5, 15)

    const val nameTwo = "i love foxes"
    const val linkTwo = "https://randomfox.ca/images/15.jpg"
    val dateTwo = Date()

    val pics = listOf(
        FoxPicEntity(nameOne, linkOne, dateOne),
        FoxPicEntity(nameTwo, linkTwo, dateTwo)
    )

    val randomPic = ApiFoxPic("", linkOne)
}
