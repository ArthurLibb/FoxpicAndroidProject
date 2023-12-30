package com.example.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.model.FoxPic
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun FoxPicComposable(
    modifier: Modifier = Modifier,
    pic: FoxPic,
    onDelete: (FoxPic) -> Unit
) {
    val simpleDateFormat = SimpleDateFormat("dd MMM yyyy 'on' HH:mm", Locale.getDefault())
    val openDeleteDialog = remember { mutableStateOf(false) }
    val imageLoading = remember {
        mutableStateOf(false)
    }

    if (openDeleteDialog.value) {
        DeletePicDialog(
            onDismissRequest = { openDeleteDialog.value = false },
            onConfim = { onDelete(pic) }
        )
    }

    Column(
        modifier = Modifier.padding(5.dp).fillMaxSize()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = modifier.fillMaxSize()
        ) {
            Row {
                Text(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    text = pic.name,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(16.dp)
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp)
            ) {
                if (imageLoading.value) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                AsyncImage(
                    model = pic.link,
                    contentDescription = pic.name,
                    onSuccess = { imageLoading.value = false },
                    onLoading = { imageLoading.value = true }
                )
            }
            Row(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                Text(
                    text = "Date pic added : " + simpleDateFormat.format(pic.date),
                    color = Color.Gray
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)
            ) {
                ElevatedButton(
                    onClick = { openDeleteDialog.value = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Delete pic")
                    Icon(Icons.Outlined.Delete, contentDescription = "delete")
                }
            }
        }
    }
}
