package com.example.app.ui.overviewScreen

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class AppOverviewViewModel(private val repo : FoxPicRepository) : ViewModel(){

    private val _ui_state = MutableStateFlow(OverviewState())
    lateinit var foxpicListState : StateFlow<FoxPicListState>

    val uiState : StateFlow<OverviewState> = _ui_state.asStateFlow()

    var apiState : FoxApiState by mutableStateOf(FoxApiState.Loading)
        private set

    init {
        getFoxPics()
    }

    private fun getFoxPics(){
        try{
            foxpicListState = repo.getFoxpics().map {
                FoxPicListState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = FoxPicListState())

            apiState = FoxApiState.Succes
        }catch(e :IOException){
            apiState = FoxApiState.Error
        }
    }

    companion object{
        private var Instance : AppOverviewViewModel? = null
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if(Instance == null){
                    val application = (this[APPLICATION_KEY] as AppApplication)
                    val foxPicRepo= application.container.foxPicRepo
                    Instance = AppOverviewViewModel(repo = foxPicRepo)
                }
                Instance!!
            }
        }
    }
}