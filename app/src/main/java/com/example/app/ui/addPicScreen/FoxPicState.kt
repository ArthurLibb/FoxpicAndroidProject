package com.example.app.ui.addPicScreen

data class FoxPicState (
    val linkImage  : String ="",
)

sealed interface RandomFoxPicApiState{
    object Succes : RandomFoxPicApiState
    object Loading : RandomFoxPicApiState
    object Error : RandomFoxPicApiState
}