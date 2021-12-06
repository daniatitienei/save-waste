package com.rozatorii_bulbucasi.savewaste.presentation.utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozatorii_bulbucasi.savewaste.domain.use_case.store_counter_use_cases.IncreaseCounter
import com.rozatorii_bulbucasi.savewaste.domain.use_case.store_counter_use_cases.StoreCounterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val useCases: StoreCounterUseCases
) : ViewModel() {

    private val _state = mutableStateOf(0)
    val state: State<Int> = _state

    init {
        getCurrentCounter()
    }

    private fun getCurrentCounter() {
        useCases.getCounter().onEach {
            _state.value = it
        }.launchIn(viewModelScope)
    }

    suspend fun increaseCounter() {
        _state.value++
        useCases.increaseCounter(_state.value)
    }
}