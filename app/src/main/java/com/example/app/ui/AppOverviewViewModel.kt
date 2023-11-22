package com.example.app.ui

import android.text.Spannable.Factory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.app.AppApplication
import com.example.app.data.FoxPicRepository
import com.example.app.model.FoxPic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

class AppOverviewViewModel(private val repo : FoxPicRepository) : ViewModel(){

    private val _ui_state = MutableStateFlow(OverviewState())
    lateinit var foxpiclistState : StateFlow<FoxPicListState>

    val uiState : StateFlow<OverviewState> = _ui_state.asStateFlow()

    var apiState : FoxApiState by mutableStateOf(FoxApiState.Loading)
        private set

    /*private fun getApiTasks(){
        viewModelScope.launch {
            try{
              val foxpics
            }
            catch (e: IOException){

            }

        }
    }*/

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AppApplication)
                val foxPicRepo= application.container.foxPicRepo
                AppOverviewViewModel(repo = foxPicRepo)
            }
        }
    }
}