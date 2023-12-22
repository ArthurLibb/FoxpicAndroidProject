package com.example.app.ui.overviewScreen

import com.example.app.model.FoxPic
import java.util.Date


data class FoxPicListState(val foxpicList: List<FoxPic> = listOf())

sealed interface FoxApiState{
    object Succes : FoxApiState
    object Error : FoxApiState
    object Loading : FoxApiState
}