package com.example.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletePicDialog(modifier: Modifier = Modifier,
                    onDismissRequest: () -> Unit,
                    onConfim: () -> Unit) {

    AlertDialog(
        icon = { Icons.Filled.Dangerous},
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
                Text("Keep foxpic")
            }
        }
    )

}