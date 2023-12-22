package com.example.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletePicDialog(modifier: Modifier = Modifier,
                    onDismissRequest: () -> Unit,
                    onConfim: () -> Unit) {

    AlertDialog(
        icon = { Icon(Icons.Filled.Delete, "Delete icon")},
        title = { Text(text = "Deleting foxpic")},
        text = { Text(text = "Do you want to delete this foxpic?")},
        onDismissRequest = {onDismissRequest()},
        confirmButton = {
            TextButton(onClick = { onConfim() }) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Keep foxpic", color = Color.Gray)
            }
        }
    )

}