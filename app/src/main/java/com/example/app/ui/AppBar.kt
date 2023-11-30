package com.example.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.app.ui.theme.GreenBlueDark
import com.example.app.ui.theme.GreenBlueDarkLess

@Composable
fun AppBar(goHome: () -> Unit){
         BottomAppBar(
             containerColor = GreenBlueDark,
             actions = {
                 IconButton(onClick = { goHome }){
                     Icon(Icons.Filled.Home, contentDescription = "home button")
                 }
             },

             floatingActionButton = {
                 FloatingActionButton(
                     onClick = { },
                     containerColor = GreenBlueDarkLess
                 ) {
                     Icon(Icons.Filled.Add, contentDescription = "add buttons")
                 }
             }
         )
}
