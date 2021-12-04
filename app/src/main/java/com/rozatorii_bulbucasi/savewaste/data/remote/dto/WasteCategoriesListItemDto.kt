package com.rozatorii_bulbucasi.savewaste.data.remote.dto

import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory

data class WasteCategoriesListItemDto(
    val advantages: List<String>,
    val categoryName: String
)

fun WasteCategoriesListItemDto.toWasteCategory() = WasteCategory(
    categoryName = categoryName,
    advantagesList = advantages
)