package com.example.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FoxPicComposable(
    modifier : Modifier = Modifier,
    name : String ,
    link : String = "")
{
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ), modifier = modifier) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(16.dp),
        )
        AsyncImage(model = link, contentDescription = name)
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