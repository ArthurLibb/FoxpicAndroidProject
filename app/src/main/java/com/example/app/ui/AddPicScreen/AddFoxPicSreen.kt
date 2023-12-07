package com.example.app.ui.AddPicScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.app.ui.components.AddPicDialog
import com.example.app.ui.theme.FoxRed
import com.example.app.ui.theme.OrangeFox


@Composable
fun AddFoxPicSreen(viewmodel : AddFoxPicViewModel = viewModel(),
                   modifier: Modifier = Modifier,
                   addPicDialogVisable: Boolean = false){

    val foxPicState by viewmodel.uifoxPicState.collectAsState()
    val apiState = viewmodel.ApiState

    Box(modifier = modifier){
        when(apiState){
            is RandomFoxPicApiState.Loading -> Text("Loading...")
            is RandomFoxPicApiState.Error -> Text("An error has occured :/")
            is RandomFoxPicApiState.Succes -> AddPicComponent(
                foxPicState,
                onPicSaved = {viewmodel.showAddDialog()})
        }
        if(addPicDialogVisable){
            AddPicDialog(setNamePic = { viewmodel.setNamePic(it)})
        }
    }
}

@Composable
fun AddPicComponent(foxpicstate : FoxPicState, onPicSaved: () -> Unit, modifier: Modifier = Modifier){

    AsyncImage(model = foxpicstate.newPicLink, contentDescription = foxpicstate.newPicName)
    ElevatedButton(
        onClick = {onPicSaved},
        colors = ButtonDefaults.buttonColors(containerColor = OrangeFox, contentColor = Color.Black)
    )
    {
        Text(text = "Add")
    }
}