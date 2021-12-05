package com.rozatorii_bulbucasi.savewaste.domain.model

data class RecyclePoint(
    val id: Int = 0,
    val collectionType: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)
