package com.example.app.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.ui.theme.OrangeFox

@Composable
fun FoxPicComposable(
    modifier : Modifier = Modifier,
    name : String ,
    link : String = "")
{
    Column(modifier = Modifier.padding(5.dp)) {
        Card(colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ), modifier = modifier) {
            Row {
                Text(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    text = name,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(16.dp),
                )
            }
           Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
               Column(
                   modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ){
                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(200.dp)
                   ) {
                       AsyncImage(model = link, contentDescription = name)
                   }
               }
           }
            Row {
                    ElevatedButton(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    )
                    {
                        Text(text = "Delete pic")
                        Icon(Icons.Outlined.Delete, contentDescription = "refresh")
                    }
                }
            }
        }
}



@Preview
@Composable
fun FoxPicPreview() {
    FoxPicComposable(
        name = "HAllloooooo",
        link = "https://randomfox.ca/?i=17",
    )
}