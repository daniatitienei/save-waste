package com.rozatorii_bulbucasi.savewaste.domain.remote.dto

import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint

data class RecycleApiSingleCategoryResultDto(
    val help: String,
    val result: ResultX,
    val success: Boolean
)

fun RecycleApiSingleCategoryResultDto.toListOfRecyclePoint(): List<RecyclePoint> =
    result.toListOfRecyclePoint()