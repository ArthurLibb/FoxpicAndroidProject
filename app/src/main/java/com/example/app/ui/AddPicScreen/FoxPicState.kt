package com.example.app.ui.AddPicScreen

import com.example.app.model.FoxPic
import java.util.Date

data class FoxPicState (
    val linkImage  : String ="",
)

sealed interface RandomFoxPicApiState{
    object Succes : RandomFoxPicApiState
    object Loading : RandomFoxPicApiState
    object Error : RandomFoxPicApiState
}