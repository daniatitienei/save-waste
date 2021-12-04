package com.rozatorii_bulbucasi.savewaste.domain.repository

import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiResultDto

interface TimisoaraRecyclePointsRepository {

    suspend fun getRecyclePoints(): RecycleApiResultDto
}