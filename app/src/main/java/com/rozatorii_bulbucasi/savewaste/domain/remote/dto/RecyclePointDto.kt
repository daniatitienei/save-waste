package com.rozatorii_bulbucasi.savewaste.domain.remote.dto

import com.google.gson.annotations.SerializedName
import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint
import com.squareup.moshi.Json

data class RecyclePointDto(
    val _full_text: String,
    val _id: Int,
    val adresa: String,
    val companie: String,
    val count: Any?,
    val id: String,
    val latitudine: String,
    val longitudine: String,
    @Json(name = "tip colectare") val tipColectare: String,
    val website: String
)

fun RecyclePointDto.toRecyclePoint(): RecyclePoint = RecyclePoint(
    id = _id,
    tipColectare = tipColectare ?: "",
    adresa = adresa,
    latitudine = latitudine.toDouble(),
    longitudine = longitudine.toDouble()
)