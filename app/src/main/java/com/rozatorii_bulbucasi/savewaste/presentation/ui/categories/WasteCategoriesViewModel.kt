package com.rozatorii_bulbucasi.savewaste.presentation.ui.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.domain.use_case.waste_categories.GetWasteCategories
import com.rozatorii_bulbucasi.savewaste.data.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WasteCategoriesViewModel @Inject constructor(
    private val getWasteCategoriesUseCase: GetWasteCategories
) : ViewModel() {

    private val _state = mutableStateOf(WasteCategoriesState())
    val state: State<WasteCategoriesState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getWasteCategoriesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(categories = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class WasteCategoriesState(
    val isLoading: Boolean = false,
    val categories: List<WasteCategory> = emptyList(),
    val error: String = ""
)
