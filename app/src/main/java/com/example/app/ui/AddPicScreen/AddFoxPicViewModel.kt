package com.example.app.ui.AddPicScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.FoxPicRepository
import com.example.app.model.FoxPic
import com.example.app.network.ApiFoxPic
import com.example.app.network.asDomainObject
import com.example.app.network.service.FoxPicService
import com.example.app.network.service.getAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddFoxPicViewModel(private val service: FoxPicService, private val repo : FoxPicRepository) : ViewModel() {

    private val _uifoxPicState = MutableStateFlow(FoxPicState())
    val uifoxPicState : StateFlow<FoxPicState> = _uifoxPicState.asStateFlow()

    var ApiState : RandomFoxPicApiState by mutableStateOf(RandomFoxPicApiState.Loading)
        private set
    init{
        getNewFoxPic()
    }



    private fun getNewFoxPic(){
        viewModelScope.launch {
            try {
                val pic = service.getFoxPic()
                _uifoxPicState.update {
                    it -> it.copy(
                        newPicLink = pic.link,
                        newPicName = pic.name
                    )
                }

            } catch(e :Exception){

            }
        }

    }
    fun setNamePic(name : String){
        _uifoxPicState.update {
            it.copy(newPicName = name)
        }
    }

    fun showAddDialog(){
        _uifoxPicState.update {
            it.copy(addPic = true)
        }
    }

    fun addFoxPic(name : String){
        viewModelScope.launch {
            val newPic = FoxPic(name, _uifoxPicState.value.newPicLink)
            repo.addFoxPic(newPic)
        }
    }
}