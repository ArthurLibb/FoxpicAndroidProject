package com.example.app.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.app.ui.theme.FoxRed
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(
    currentScreen: Int
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = FoxRed,
            titleContentColor = Color.White
        ),
        title = {
            Text(text = stringResource(id = currentScreen))
        }
    )
}
