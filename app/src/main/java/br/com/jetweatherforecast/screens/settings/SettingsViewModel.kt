package br.com.jetweatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jetweatherforecast.model.Unit
import br.com.jetweatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits()
                .distinctUntilChanged()
                .onEach { listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {
                        Log.d("TAG", ": Empty favs")
                    } else {
                        _unitList.value = listOfUnits
                        Log.d("TAG", ": listOfUnits = ${_unitList.value}")
                    }
                }
                .collect()
        }
    }

    fun insertUnit(Unit: Unit) =
        viewModelScope.launch { repository.insertUnit(Unit) }

    fun updateUnit(Unit: Unit) =
        viewModelScope.launch { repository.updateUnit(Unit) }

    fun deleteUnit(Unit: Unit) =
        viewModelScope.launch { repository.deleteUnit(Unit) }

    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
}