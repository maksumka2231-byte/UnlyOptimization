package com.unlymng.optimization.data.repository

/**
 * Repository для управления настройками
 */
interface SettingsRepository {
    suspend fun isAutoBoostEnabled(): Boolean
    suspend fun setAutoBoostEnabled(enabled: Boolean): Unit
    suspend fun getLanguage(): String
    suspend fun setLanguage(language: String): Unit
}
