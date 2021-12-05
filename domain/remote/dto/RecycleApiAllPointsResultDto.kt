package com.rozatorii_bulbucasi.savewaste.domain.remote.dto

import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint

data class RecycleApiResultDto(
    val help: String,
    val result: Result,
    val success: Boolean
)

fun RecycleApiResultDto.toListOfRecyclePoint(): List<RecyclePoint> =
    result.toListOfRecyclePoint()
