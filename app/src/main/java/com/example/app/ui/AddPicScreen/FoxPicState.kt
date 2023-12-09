package com.example.app.ui.AddPicScreen

import com.example.app.model.FoxPic

data class FoxPicState (
    val newPicName : String ="",
    val newPicLink : String = "",
    val addPic : Boolean = false
)

data class PicState(val picObj : FoxPic = FoxPic("",""))

sealed interface RandomFoxPicApiState{
    object Succes : RandomFoxPicApiState
    object Loading : RandomFoxPicApiState
    object Error : RandomFoxPicApiState
}