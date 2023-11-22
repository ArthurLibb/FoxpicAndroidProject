package com.example.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidApp(navController: NavController = rememberNavController()){
    var addingVisible by rememberSaveable { mutableStateOf(false) }

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = { AppBar() },
    ){

    }
}

@Preview
@Composable
fun AppPreview(){
    AppTheme {
        Surface (modifier = Modifier.fillMaxSize()){
            AndroidApp()
        }
    }
}
