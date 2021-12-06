package com.rozatorii_bulbucasi.savewaste.data.di

import android.content.Context
import com.rozatorii_bulbucasi.savewaste.SaveWasteApplication
import com.rozatorii_bulbucasi.savewaste.data.common.Constants
import com.rozatorii_bulbucasi.savewaste.data.repository.StoreCounterRepositoryImpl
import com.rozatorii_bulbucasi.savewaste.data.repository.TimisoaraRecyclePointsRepositoryImpl
import com.rozatorii_bulbucasi.savewaste.domain.remote.TimisoaraRecyclePointsApi
import com.rozatorii_bulbucasi.savewaste.domain.repository.StoreCounterRepository
import com.rozatorii_bulbucasi.savewaste.domain.repository.TimisoaraRecyclePointsRepository
import com.rozatorii_bulbucasi.savewaste.domain.use_case.store_counter_use_cases.GetCounter
import com.rozatorii_bulbucasi.savewaste.domain.use_case.store_counter_use_cases.IncreaseCounter
import com.rozatorii_bulbucasi.savewaste.domain.use_case.store_counter_use_cases.StoreCounterUseCases
import com.rozatorii_bulbucasi.savewaste.domain.use_case.timisoara_recycle_points_use_cases.GetACategoryOfRecyclePoints
import com.rozatorii_bulbucasi.savewaste.domain.use_case.timisoara_recycle_points_use_cases.GetRecyclePoints
import com.rozatorii_bulbucasi.savewaste.domain.use_case.timisoara_recycle_points_use_cases.RecyclePointsUseCases
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp() = SaveWasteApplication()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideTimisoaraRecyclePointsApi(moshi: Moshi): TimisoaraRecyclePointsApi =
        Retrofit.Builder()
            .baseUrl(Constants.TIMISOARA_RECYCLE_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TimisoaraRecyclePointsApi::class.java)

    @Provides
    @Singleton
    fun provideTimisoaraRecyclePointsRepositoryImpl(api: TimisoaraRecyclePointsApi): TimisoaraRecyclePointsRepository =
        TimisoaraRecyclePointsRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideRecyclePointsUseCases(repository: TimisoaraRecyclePointsRepository): RecyclePointsUseCases =
        RecyclePointsUseCases(
            getRecyclePoints = GetRecyclePoints(repository),
            getACategoryOfRecyclePoints = GetACategoryOfRecyclePoints(repository)
        )

    @Provides
    @Singleton
    fun provideStoreCounterRepository(application: SaveWasteApplication): StoreCounterRepository =
        StoreCounterRepositoryImpl(application)

    @Provides
    @Singleton
    fun providesStoreCounterUseCases(repository: StoreCounterRepository) = StoreCounterUseCases(
        getCounter = GetCounter(repository),
        increaseCounter = IncreaseCounter(repository)
    )
}