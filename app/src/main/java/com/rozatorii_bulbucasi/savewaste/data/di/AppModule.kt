package com.rozatorii_bulbucasi.savewaste.data.di

import com.rozatorii_bulbucasi.savewaste.data.common.Constants
import com.rozatorii_bulbucasi.savewaste.data.repository.TimisoaraRecyclePointsRepositoryImpl
import com.rozatorii_bulbucasi.savewaste.domain.remote.TimisoaraRecyclePointsApi
import com.rozatorii_bulbucasi.savewaste.domain.repository.TimisoaraRecyclePointsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
}