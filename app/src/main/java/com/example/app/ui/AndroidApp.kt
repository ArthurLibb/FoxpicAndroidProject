package com.example.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.navigation.FoxPicNavigationType
import com.example.app.ui.navigation.OverviewScreen
import com.example.app.ui.navigation.navComponent
import com.example.app.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidApp(windowSize: WindowWidthSizeClass,navController: NavHostController = rememberNavController()){
    var addingVisible by rememberSaveable { mutableStateOf(false) }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val goHome : () -> Unit = {
        navController.popBackStack(
            OverviewScreen.Start.name,
            inclusive = false
        )
    }
val navType : FoxPicNavigationType
    when(windowSize){
        WindowWidthSizeClass.Compact -> {
            navType = FoxPicNavigationType.BOTTOM_NAV
        }
        WindowWidthSizeClass.Expanded -> {
            navType = FoxPicNavigationType.NAVIGATION_DRAWER
        }
        else -> {
            navType = FoxPicNavigationType.BOTTOM_NAV
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = { AppBar(goHome)},
    ){
        innerPadding ->
        navComponent(navController, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun AppPreview(){
    AppTheme {
        Surface (modifier = Modifier.fillMaxSize()){
            AndroidApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}
