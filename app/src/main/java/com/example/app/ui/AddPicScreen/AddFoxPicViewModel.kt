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
import java.util.Date

class AddFoxPicViewModel(private val repo : FoxPicRepository) : ViewModel() {

    private val _uifoxPicState = MutableStateFlow(FoxPicState())
    val uifoxPicState : StateFlow<FoxPicState> = _uifoxPicState.asStateFlow()

    lateinit var picState : StateFlow<PicState>

    var apiState : RandomFoxPicApiState by mutableStateOf(RandomFoxPicApiState.Loading)
        private set
    init{
        getNewFoxPic()
    }

    private fun getNewFoxPic(){
            try {

                viewModelScope.launch {
                    picState = repo.getRandomFoxPic().map{
                        PicState(FoxPic(it.name, it.link, Date()))
                    }.stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(),
                        initialValue = PicState(),
                    )
                }
            Log.d("values", "values : " + picState .value.picObj.toString())
                apiState = RandomFoxPicApiState.Succes
            } catch(e :Exception){
                apiState = RandomFoxPicApiState.Error
            }
    }

    fun addFoxPic(name : String){
        viewModelScope.launch {
            val newPic = FoxPic(name, picState.value.picObj.link, Date())
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