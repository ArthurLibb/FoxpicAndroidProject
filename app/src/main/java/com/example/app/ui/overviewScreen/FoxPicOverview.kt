package com.example.app.ui.overviewScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.model.FoxPic
import com.example.app.ui.components.FoxPicComposable
import com.example.app.ui.theme.OrangeFox

@Composable
fun FoxPicOverview(
    modifier: Modifier = Modifier,
    overviewViewModel: AppOverviewViewModel = viewModel(factory = AppOverviewViewModel.Factory)
){
    val mContext = LocalContext.current
    val toast = Toast.makeText(mContext, "Pic has been deleted!", Toast.LENGTH_LONG)
    val overviewState by overviewViewModel.uiState.collectAsState()
    val foxPicListState by overviewViewModel.foxpicListState.collectAsState()
    val foxPicApiState = overviewViewModel.apiState

    Box(modifier = modifier){
        when(foxPicApiState){
            is FoxApiState.Loading -> {
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
            is FoxApiState.Error -> Text(text = "An error as occured")
            is FoxApiState.Succes -> {
                FoxPicListComposable(overviewState = overviewState,
                    foxPicListState = foxPicListState,
                    modifier = modifier,
                    onDelete = {
                        overviewViewModel.deleteFoxPic(it)
                        toast
                    })
            }
        }
    }
}

@Composable
fun FoxPicListComposable(
    modifier: Modifier,
    overviewState: OverviewState,
    foxPicListState : FoxPicListState,
    onDelete : (FoxPic) -> Unit
){

    if(foxPicListState.foxpicList.isEmpty()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
           /* Image(painter = painterResource(id = R.drawable), contentDescription = null)*/
            Text(text = "You have no foxpics saved!", color = OrangeFox)
        }
    }
    else{
        val lazyListState = rememberLazyListState()
        LazyColumn(state = lazyListState,
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            items(foxPicListState.foxpicList){
                FoxPicComposable(pic = it, onDelete = {piv -> onDelete(piv)})
                Log.d("overview", it.name + " " + it.link)
            }
        }
    }
}
