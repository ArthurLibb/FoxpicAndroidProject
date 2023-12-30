package com.example.app.model

import java.util.Date

data class FoxPic(
    var name: String = "",
    var link: String,
    var date: Date
) {
    override fun toString(): String {
        return "FoxPic(name='$name', link='$link')"
    }
}
