package com.example.app.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.app.R

enum class OverviewScreen(@StringRes val title: Int, val icon: ImageVector) {
    Start(title = R.string.title_overview, icon = Icons.Filled.Home),
    Welcome(title = R.string.welcome, icon = Icons.Filled.Home),
    AddFoxPic(title = R.string.add_foxpic, icon = Icons.Filled.Add)
}