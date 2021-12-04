package com.rozatorii_bulbucasi.savewaste.data.remote

import com.rozatorii_bulbucasi.savewaste.data.remote.dto.WasteCategoriesListItemDto
import retrofit2.http.*

interface WasteCategoriesApi {

    @GET("wasteApi.json")
    suspend fun getCategoriesWithInfo(): List<WasteCategoriesListItemDto>
}