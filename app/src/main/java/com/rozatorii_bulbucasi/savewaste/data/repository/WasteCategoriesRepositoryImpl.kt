package com.rozatorii_bulbucasi.savewaste.data.repository

import com.rozatorii_bulbucasi.savewaste.data.remote.WasteCategoriesApi
import com.rozatorii_bulbucasi.savewaste.data.remote.dto.WasteCategoriesListItemDto
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.domain.repository.WasteCategoriesRepository
import javax.inject.Inject

class WasteCategoriesRepositoryImpl @Inject constructor(
    private val categoriesApi: WasteCategoriesApi
) : WasteCategoriesRepository {

    override suspend fun getCategoriesWithInfo(): List<WasteCategoriesListItemDto> = categoriesApi.getCategoriesWithInfo()
}