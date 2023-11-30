package com.example.app.ui.overviewScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.model.FoxPic
import com.example.app.ui.FoxPicComposable
import com.example.app.ui.overviewScreen.AppOverviewViewModel
import kotlinx.coroutines.launch

@Composable
fun FoxPicOverview(
    modifier: Modifier = Modifier,
    overviewViewModel: AppOverviewViewModel = viewModel(factory = AppOverviewViewModel.Factory)
){
    val overviewState by overviewViewModel.uiState.collectAsState()
    val foxPicListState by overviewViewModel.foxpicListState.collectAsState()

    val foxPicApiState = overviewViewModel.apiState

    Box(modifier = modifier){
        when(foxPicApiState){
            is FoxApiState.Loading -> Text(text = "Loading...")
            is FoxApiState.Error -> Text(text = "An error as occured")
            is FoxApiState.Succes -> FoxPicListComposable(overviewState = overviewState, foxPicListState = foxPicListState, modifier = modifier)
        }
    }
}

@Composable
fun FoxPicListComposable(
    modifier: Modifier,
    overviewState: OverviewState,
    foxPicListState : FoxPicListState
){
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState){
        items(foxPicListState.foxpicList){
            FoxPicComposable(name = it.name, link = it.link)
        }
    }

    /*val coroutineScope = rememberCoroutineScope()
    LaunchedEffect{
        coroutineScope.launch {

        }
    }*/
}