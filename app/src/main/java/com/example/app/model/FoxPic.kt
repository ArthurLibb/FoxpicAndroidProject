package com.example.app.model

data class FoxPic (
    var name : String = "",
    var link : String
) {
    override fun toString(): String {
        return "FoxPic(name='$name', link='$link')"
    }
}