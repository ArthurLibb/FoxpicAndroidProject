package com.example.app.ui.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.ui.AddPicScreen.AddFoxPicSreen
import com.example.app.ui.overviewScreen.FoxPicOverview

@Composable
fun navComponent(navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = OverviewScreen.Start.name,
        modifier = modifier ){
        composable(route = OverviewScreen.Start.name){
            FoxPicOverview()
        }
        composable(route = OverviewScreen.Detail.name){
            Text("Detail")
        }
        composable(route = OverviewScreen.AddFoxPic.name){
            Log.i("vm navigation", "Nev to addFoxPic")
            AddFoxPicSreen()
        }
    }
}