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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddFoxPicViewModel(private val repo : FoxPicRepository) : ViewModel() {

    private val _uifoxPicState = MutableStateFlow(FoxPicState())
    val uifoxPicState : StateFlow<FoxPicState> = _uifoxPicState.asStateFlow()

    lateinit var picState: StateFlow<PicState>

    var apiState : RandomFoxPicApiState by mutableStateOf(RandomFoxPicApiState.Loading)
        private set
    init{
        getNewFoxPic()
    }

    private fun getNewFoxPic(){
        viewModelScope.launch {
            try {
                picState = repo.getRandomFoxPic().map{
                    Log.d("values", it.toString())
                    PicState(FoxPic("", it.link))

                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(1_000L),
                    initialValue = PicState(),
                )
                Log.d("values","Values pic : " + picState.value.picObj.toString())
                apiState = RandomFoxPicApiState.Succes
            } catch(e :Exception){
                apiState = RandomFoxPicApiState.Error
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

    companion object{
        private var Instance : AddFoxPicViewModel? = null
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if(Instance == null){
                    val appli =  (this[APPLICATION_KEY] as AppApplication)
                    val repository = appli.container.foxPicRepo
                    Instance = AddFoxPicViewModel(repo = repository)
                }
                Instance!!
            }
        }
    }
}