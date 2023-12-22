package com.example.app.ui.AddPicScreen

import android.util.Log
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.app.AppApplication
import com.example.app.data.FoxPicRepository
import com.example.app.model.FoxPic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.Date

class AddFoxPicViewModel(private val repo : FoxPicRepository,
                         private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _uifoxPicState = MutableStateFlow(FoxPicState())
    val uifoxPicState : StateFlow<FoxPicState> = _uifoxPicState.asStateFlow()


    var apiState : RandomFoxPicApiState by mutableStateOf(RandomFoxPicApiState.Loading)
        private set

    init{
        getNewFoxPic()
    }

    fun getNewFoxPic(){
            try {
                _uifoxPicState.value = _uifoxPicState.value.copy(linkImage="")
                viewModelScope.launch {
                    repo.getRandomFoxPic() //Backgroupnd thread
                        .flowOn(dispatcherIO).collect{
                        f ->
                        Log.d("values", f.name + " " + f.link)
                        _uifoxPicState.value  = _uifoxPicState.value.copy(linkImage = f.link)
                    }
                }
                Log.d("values", "values : " + _uifoxPicState.value.toString())
                apiState = RandomFoxPicApiState.Succes
            } catch(e :Exception){
                apiState = RandomFoxPicApiState.Error
            }
    }

    fun addFoxPic(name : String){
        Log.d("Values added", name)
        viewModelScope.launch {
            val newPic = FoxPic(name,  _uifoxPicState.value.linkImage, Date())
            repo.addFoxPic(newPic)
        }
    }
    fun asyncImageSucces(){
        apiState = RandomFoxPicApiState.Succes
    }
    fun asyncImageLoading(){
        apiState = RandomFoxPicApiState.Loading
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddFoxPicViewModel(repo = (this[APPLICATION_KEY] as AppApplication).container.foxPicRepo)
            }
        }
    }
}