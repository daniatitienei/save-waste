package com.rozatorii_bulbucasi.savewaste.domain.model

import com.rozatorii_bulbucasi.savewaste.domain.enum.WasteType

data class WasteCategory(
    val categoryName: String,
    val advantagesList: List<String>,
    val wasteType: WasteType
)
