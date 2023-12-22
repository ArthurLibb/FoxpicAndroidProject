package com.example.app.ui.AddPicScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.app.ui.components.AddPicDialog
import com.example.app.ui.theme.FoxRed
import com.example.app.ui.theme.OrangeFox


@Composable
fun AddFoxPicSreen(viewmodel: AddFoxPicViewModel = viewModel(factory = AddFoxPicViewModel.Factory),
                   modifier: Modifier = Modifier){

    val openAlertDialog = remember {
        mutableStateOf(false)
    }
    val foxPicState by viewmodel.uifoxPicState.collectAsState()
    val apiState = viewmodel.apiState

    Box(modifier = modifier){
         when(apiState){
             is RandomFoxPicApiState.Loading -> {
                 Column(
                     modifier = Modifier.fillMaxSize(),
                     verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally
                 ){
                     CircularProgressIndicator(
                         modifier = Modifier.width(64.dp),
                         color = MaterialTheme.colorScheme.secondary,
                     )
                 }
             }
             is RandomFoxPicApiState.Error -> Text("An error has occured :/")
             is RandomFoxPicApiState.Succes -> {
                 when(openAlertDialog.value){
                     false -> {
                         AddPicComponent(
                             foxPicState,
                             onPicSaved = { openAlertDialog.value = true },
                             onRefresh = {
                                 viewmodel.getNewFoxPic()
                             },
                             onLoadingImage = {viewmodel.asyncImageLoading()},
                             onSuccesImage = {viewmodel.asyncImageSucces()}
                             )

                     }
                     true -> {
                         AddPicDialog(
                             onDismissRequest = { openAlertDialog.value = false },
                             onConfim = {
                                 viewmodel.addFoxPic(it)
                                 openAlertDialog.value = false
                             })
                     }
                 }
             }
         }
    }
}

@Composable
fun AddPicComponent(foxpicstate : FoxPicState, onPicSaved: () -> Unit,
                    modifier: Modifier = Modifier, onRefresh: () -> Unit,
                    onLoadingImage: () -> Unit, onSuccesImage: () -> Unit
) {
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(4.dp))
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    if (foxpicstate.linkImage.isNotEmpty()) {
                        AsyncImage(
                            model = foxpicstate.linkImage,
                            contentDescription = "",
                            modifier = Modifier.fillMaxWidth(),
                            onLoading = { onLoadingImage },
                            onSuccess = {}
                        )
                    }
                    else{
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                        )}
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ){
                    Row(

                    ){
                        ElevatedButton(
                            onClick = { onPicSaved() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OrangeFox,
                                contentColor = Color.Black
                            )
                        )
                        {
                            Text(text = "Add")
                            Icon(Icons.Outlined.Add,  contentDescription ="Add")
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        ElevatedButton(
                            onClick = { onRefresh() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray,
                                contentColor = Color.Black
                            )
                        )
                        {
                            Text(text = "Other Pic")
                            Icon(Icons.Outlined.Autorenew, contentDescription = "refresh")
                        }
                    }

                }
            }
        }


}