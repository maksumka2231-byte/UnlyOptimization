package com.unlymng.optimization.data.repository.impl

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.unlymng.optimization.data.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import javax.inject.Inject

const val SETTINGS_DATASTORE_NAME = "unly_settings"
val Context.settingsDataStore by preferencesDataStore(SETTINGS_DATASTORE_NAME)

val AUTO_BOOST_KEY = booleanPreferencesKey("auto_boost")
val LANGUAGE_KEY = stringPreferencesKey("language")

/**
 * Реализация SettingsRepository с DataStore
 */
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsRepository {

    private val dataStore = context.settingsDataStore

    override suspend fun isAutoBoostEnabled(): Boolean {
        return dataStore.data.first()[AUTO_BOOST_KEY] ?: false
    }

    override suspend fun setAutoBoostEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTO_BOOST_KEY] = enabled
        }
    }

    override suspend fun getLanguage(): String {
        return dataStore.data.first()[LANGUAGE_KEY] ?: "Русский"
    }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }
}
