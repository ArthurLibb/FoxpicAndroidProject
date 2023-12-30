package com.example.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.components.AppBar
import com.example.app.ui.components.TopBarApp
import com.example.app.ui.navigation.OverviewScreen
import com.example.app.ui.navigation.navComponent
import com.example.app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val goHome: () -> Unit = {
        navController.popBackStack(
            OverviewScreen.Start.name,
            inclusive = false
        )
    }
    val addNewFoxPic = {
        navController.navigate(OverviewScreen.AddFoxPic.name) { launchSingleTop = true }
    }

    val currentScreen = OverviewScreen.valueOf(
        backStackEntry?.destination?.route ?: OverviewScreen.Start.name
    ).title
    Scaffold(
        containerColor = Color.Transparent,
        topBar = { TopBarApp(currentScreen = currentScreen) },
        bottomBar = { AppBar(goHome, addNewFoxPic) }
    ) {
            innerPadding ->
        navComponent(navController, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun AppPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AndroidApp()
        }
    }
}
