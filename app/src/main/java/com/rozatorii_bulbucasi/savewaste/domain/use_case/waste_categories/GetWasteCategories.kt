package com.rozatorii_bulbucasi.savewaste.domain.use_case.waste_categories

import com.rozatorii_bulbucasi.savewaste.data.remote.dto.toWasteCategory
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.domain.repository.WasteCategoriesRepository
import com.rozatorii_bulbucasi.savewaste.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetWasteCategories @Inject constructor(
    private val repository: WasteCategoriesRepository
) {
    operator fun invoke(): Flow<Resource<List<WasteCategory>>> = flow {
        try {
            emit(Resource.Loading<List<WasteCategory>>())

            val wasteCategoriesList = repository.getCategoriesWithInfo().map {
                it.toWasteCategory()
            }

            emit(Resource.Success<List<WasteCategory>>(wasteCategoriesList))

        } catch (e: HttpException) {
            emit(
                Resource.Error<List<WasteCategory>>(
                    message = e.localizedMessage ?: "An unexpected error occurred."
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<WasteCategory>>(message = "Check your internet connection."))
        }
    }
}