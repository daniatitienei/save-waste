package com.rozatorii_bulbucasi.savewaste.domain.remote

import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiResultDto
import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.RecycleApiSingleCategoryResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TimisoaraRecyclePointsApi {

    @GET("datastore_search_sql?sql=SELECT * from \"d0134630-84d9-40b8-9bcb-dfdc926d66ab\"")
    suspend fun getAllPoints(): RecycleApiResultDto

    @GET("datastore_search?resource_id=d0134630-84d9-40b8-9bcb-dfdc926d66ab")
    suspend fun getPointsOfACategory(@Query("q") categoryName: String): RecycleApiSingleCategoryResultDto
}