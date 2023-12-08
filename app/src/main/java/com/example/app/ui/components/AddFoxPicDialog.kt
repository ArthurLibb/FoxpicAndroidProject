package com.example.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
        Column {
            TextField(value = "",
                onValueChange = {naamPic = it},
                label = { Text(text = "How would you like to name the pic?")})
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangeFox,
                        contentColor = Color.Black),
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Dismiss")
                }
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangeFox,
                        contentColor = Color.Black),
                    onClick = { onConfim(naamPic)},
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Confirm")
                }
            }
        }

    }
}