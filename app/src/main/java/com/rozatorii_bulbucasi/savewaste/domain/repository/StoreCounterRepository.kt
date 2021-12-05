package com.rozatorii_bulbucasi.savewaste.domain.repository

import kotlinx.coroutines.flow.Flow

interface StoreCounterRepository {
    fun getCounter(): Flow<Int>

    suspend fun increaseCounter(counter: Int)
}