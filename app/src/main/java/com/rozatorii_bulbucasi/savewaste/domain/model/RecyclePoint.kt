package com.rozatorii_bulbucasi.savewaste.domain.model

data class RecyclePoint(
    val id: Int = 0,
    val tipColectare: String = "",
    val adresa: String = "",
    val latitudine: Double = 0.0,
    val longitudine: Double = 0.0
)
