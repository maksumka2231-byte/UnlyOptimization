package com.unlymng.optimization.domain.usecase

import com.unlymng.optimization.data.repository.SettingsRepository
import javax.inject.Inject

/**
 * UseCase для управления настройками приложения
 */
class SettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    /**
     * Проверяет, включен ли автобуст при запуске игры
     */
    suspend fun isAutoBoostEnabled(): Boolean {
        return settingsRepository.isAutoBoostEnabled()
    }

    /**
     * Устанавливает автобуст при запуске игры
     */
    suspend fun setAutoBoostEnabled(enabled: Boolean) {
        settingsRepository.setAutoBoostEnabled(enabled)
    }

    /**
     * Получает текущий язык приложения
     */
    suspend fun getLanguage(): String {
        return settingsRepository.getLanguage()
    }

    /**
     * Устанавливает язык приложения
     */
    suspend fun setLanguage(language: String) {
        settingsRepository.setLanguage(language)
    }
}
