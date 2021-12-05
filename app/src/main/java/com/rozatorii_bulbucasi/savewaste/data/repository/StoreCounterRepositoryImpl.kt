package com.rozatorii_bulbucasi.savewaste.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rozatorii_bulbucasi.savewaste.domain.repository.StoreCounterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreCounterRepositoryImpl @Inject constructor(
    private val context: Context
) : StoreCounterRepository {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("waste_counter")
        val COUNTER_KEY = intPreferencesKey("counter")
    }

    override fun getCounter(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }

    override suspend fun increaseCounter(counter: Int) {
        context.dataStore.edit { preferences ->
            preferences[COUNTER_KEY] = counter
        }
    }
}