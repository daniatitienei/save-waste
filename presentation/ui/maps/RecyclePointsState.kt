package com.rozatorii_bulbucasi.savewaste.presentation.ui.maps

import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint

data class RecyclePointsState(
    val isLoading: Boolean = false,
    val data: List<RecyclePoint> = emptyList(),
    val error: String? = null
)
