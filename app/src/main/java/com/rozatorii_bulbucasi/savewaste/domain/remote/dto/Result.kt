package com.rozatorii_bulbucasi.savewaste.domain.remote.dto

import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint

data class Result(
    val records: List<RecyclePointDto>,
    val sql: String
)

fun Result.toListOfRecyclePoint(): List<RecyclePoint> =
    records.map {
        it.toRecyclePoint()
    }
