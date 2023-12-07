package com.example.app.ui.AddPicScreen

data class FoxPicState (
    val newPicName : String ="",
    val newPicLink : String = "",
    val addPic : Boolean = false
)

sealed interface RandomFoxPicApiState{
    object Succes : RandomFoxPicApiState
    object Loading : RandomFoxPicApiState
    object Error : RandomFoxPicApiState
}