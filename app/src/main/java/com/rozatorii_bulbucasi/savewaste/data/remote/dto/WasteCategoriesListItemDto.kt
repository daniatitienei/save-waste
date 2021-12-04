package com.rozatorii_bulbucasi.savewaste.data.remote.dto

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.rozatorii_bulbucasi.savewaste.domain.enum.WasteType
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory

data class WasteCategoriesListItemDto(
    val advantages: List<String>,
    val categoryName: String
)

fun WasteCategoriesListItemDto.toWasteCategory() = WasteCategory(
    categoryName = categoryName,
    advantagesList = advantages,
    wasteType = getWasteCategoryFromCategoryName(categoryName = categoryName)
)

private fun getWasteCategoryFromCategoryName(categoryName: String): WasteType =
    when (categoryName.toLowerCase(locale = Locale.current)) {
        "glass" -> WasteType.GLASS
        "batteries" -> WasteType.BATTERIES
        "clothes" -> WasteType.CLOTHES
        "furniture" -> WasteType.FURNITURE
        "light bulbs" -> WasteType.LIGHT_BULBS
        "meds" -> WasteType.MEDS
        "oil" -> WasteType.OIL
        "paper" -> WasteType.PAPER
        "plastic" -> WasteType.PLASTIC
        else -> WasteType.POLYSTYRENE
    }