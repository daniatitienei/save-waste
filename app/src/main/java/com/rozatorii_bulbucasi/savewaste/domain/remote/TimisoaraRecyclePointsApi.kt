package com.rozatorii_bulbucasi.savewaste.domain.remote

import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiResultDto
import retrofit2.http.GET

interface TimisoaraRecyclePointsApi {

    @GET("datastore_search_sql?sql=SELECT * from \"d0134630-84d9-40b8-9bcb-dfdc926d66ab\"")
    suspend fun getAllPoints(): RecycleApiResultDto
}