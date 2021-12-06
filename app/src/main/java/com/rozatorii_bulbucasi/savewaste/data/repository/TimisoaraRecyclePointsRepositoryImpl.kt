package com.rozatorii_bulbucasi.savewaste.data.repository

import com.rozatorii_bulbucasi.savewaste.domain.remote.TimisoaraRecyclePointsApi
import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiResultDto
import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiSingleCategoryResultDto
import com.rozatorii_bulbucasi.savewaste.domain.repository.TimisoaraRecyclePointsRepository
import javax.inject.Inject

class TimisoaraRecyclePointsRepositoryImpl @Inject constructor(
    private val api: TimisoaraRecyclePointsApi
) : TimisoaraRecyclePointsRepository {

    override suspend fun getRecyclePoints(): RecycleApiResultDto = api.getAllPoints()

    override suspend fun getPointsOfACategory(categoryName: String): RecycleApiSingleCategoryResultDto =
        api.getPointsOfACategory(categoryName = categoryName)
}