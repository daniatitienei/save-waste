package com.rozatorii_bulbucasi.savewaste.data.di

import android.app.Application
import com.rozatorii_bulbucasi.savewaste.SaveWasteApplication
import com.rozatorii_bulbucasi.savewaste.data.remote.WasteCategoriesApi
import com.rozatorii_bulbucasi.savewaste.data.repository.WasteCategoriesRepositoryImpl
import com.rozatorii_bulbucasi.savewaste.domain.repository.WasteCategoriesRepository
import com.rozatorii_bulbucasi.savewaste.utils.Common
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
        .addLast(
            KotlinJsonAdapterFactory()
        )
        .build()

    @Provides
    @Singleton
    fun provideWasteCategoriesApi(moshi: Moshi): WasteCategoriesApi = Retrofit.Builder()
        .baseUrl(Common.SAVE_WASTE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(WasteCategoriesApi::class.java)

    @Provides
    @Singleton
    fun provideWasteCategoriesRepository(api: WasteCategoriesApi): WasteCategoriesRepository =
        WasteCategoriesRepositoryImpl(api)
}