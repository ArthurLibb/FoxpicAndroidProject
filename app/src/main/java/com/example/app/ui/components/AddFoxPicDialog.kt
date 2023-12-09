package com.example.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.app.ui.theme.OrangeFox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPicDialog(modifier: Modifier = Modifier,
                 onDismissRequest: () -> Unit,
                 onConfim: (String) -> Unit
){
    var naamPic by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismissRequest() }, ) {
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(4.dp))
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                TextField(value = "",
                    onValueChange = { naam -> naamPic = naam },
                    label = { Text(text = "How would you like to name the pic?") })
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OrangeFox,
                            contentColor = Color.Black
                        ),
                        onClick = { onConfim(naamPic) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                    TextButton(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        ),
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        }
    }
}