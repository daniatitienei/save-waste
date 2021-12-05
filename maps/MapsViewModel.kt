package com.rozatorii_bulbucasi.savewaste.presentation.ui.maps

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rozatorii_bulbucasi.savewaste.domain.use_case.timisoara_recycle_points_use_cases.GetRecyclePoints
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.rozatorii_bulbucasi.savewaste.data.common.Resource
import com.rozatorii_bulbucasi.savewaste.domain.use_case.timisoara_recycle_points_use_cases.RecyclePointsUseCases
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val useCases: RecyclePointsUseCases
) : ViewModel() {
    private val _state = mutableStateOf(RecyclePointsState())
    val state: State<RecyclePointsState> = _state

    fun getRecyclePoints() {

        useCases.getRecyclePoints().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(data = result.data!!, isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getACategoryOfRecyclePoints(categoryName: String) {

        useCases.getACategoryOfRecyclePoints(categoryName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(data = result.data!!, isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }


}