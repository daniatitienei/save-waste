package com.rozatorii_bulbucasi.savewaste.domain.use_case.store_counter_use_cases

import com.rozatorii_bulbucasi.savewaste.domain.repository.StoreCounterRepository
import javax.inject.Inject

class IncreaseCounter @Inject constructor(
    private val repository: StoreCounterRepository
) {

    suspend operator fun invoke(counter: Int) {
        repository.increaseCounter(counter)
    }
}