package com.example.app.ui.AddPicScreen

import com.example.app.model.FoxPic
import java.util.Date

data class FoxPicState (
    val newPicName : String ="",
    val addPic : Boolean = false
)

data class PicState(val picObj : FoxPic = FoxPic("","", Date()))

sealed interface RandomFoxPicApiState{
    object Succes : RandomFoxPicApiState
    object Loading : RandomFoxPicApiState
    object Error : RandomFoxPicApiState
}