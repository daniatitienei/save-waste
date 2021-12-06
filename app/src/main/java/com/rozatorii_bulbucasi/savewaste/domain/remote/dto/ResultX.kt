package com.rozatorii_bulbucasi.savewaste.domain.remote.dto

import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint

data class ResultX(
    val include_total: Boolean,
    val limit: Int,
    val q: String,
    val records: List<RecyclePointDto>,
    val records_format: String,
    val resource_id: String,
    val total_estimation_threshold: Any?
)

fun ResultX.toListOfRecyclePoint(): List<RecyclePoint> =
    records.map {
        it.toRecyclePoint()
    }