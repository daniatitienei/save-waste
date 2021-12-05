package com.rozatorii_bulbucasi.savewaste.domain.use_case.timisoara_recycle_points_use_cases

import com.rozatorii_bulbucasi.savewaste.data.common.Resource
import com.rozatorii_bulbucasi.savewaste.domain.model.RecyclePoint
import com.rozatorii_bulbucasi.savewaste.domain.remote.dto.toListOfRecyclePoint
import com.rozatorii_bulbucasi.savewaste.domain.repository.TimisoaraRecyclePointsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetRecyclePoints @Inject constructor(
    private val repository: TimisoaraRecyclePointsRepository
) {

    operator fun invoke(): Flow<Resource<List<RecyclePoint>>> = flow {
        try {
            emit(Resource.Loading<List<RecyclePoint>>())

            val result = repository.getRecyclePoints().result.toListOfRecyclePoint()

            emit(Resource.Success<List<RecyclePoint>>(result))

        } catch (e: HttpException) {
            emit(Resource.Error<List<RecyclePoint>>(message = "An unexpected error has occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<RecyclePoint>>(message = "Check your internet connection"))
        }
    }
}