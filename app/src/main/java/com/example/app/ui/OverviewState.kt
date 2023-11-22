package com.example.app.ui

import com.example.app.model.FoxPic

data class OverviewState (
    val currentFoxPic: FoxPic = FoxPic("",""),
)

data class FoxPicListState(val foxpicList: List<FoxPic> = listOf())

sealed interface FoxApiState{
    object Succes : FoxApiState
    object Error : FoxApiState
    object Loading : FoxApiState
}