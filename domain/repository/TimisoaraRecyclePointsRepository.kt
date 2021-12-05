package com.rozatorii_bulbucasi.savewaste.domain.repository

import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiResultDto
import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiSingleCategoryResultDto

interface TimisoaraRecyclePointsRepository {

    suspend fun getRecyclePoints(): RecycleApiResultDto

    suspend fun getPointsOfACategory(categoryName: String): RecycleApiSingleCategoryResultDto
}