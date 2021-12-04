package com.rozatorii_bulbucasi.savewaste.domain.repository

import com.rozatorii_bulbucasi.savewaste.data.remote.dto.WasteCategoriesListItemDto
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory

interface WasteCategoriesRepository {

    suspend fun getCategoriesWithInfo(): List<WasteCategoriesListItemDto>
}