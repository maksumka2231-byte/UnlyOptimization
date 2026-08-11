package com.unlymng.optimization.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlymng.optimization.domain.usecase.SettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана Settings
 * Управляет настройками приложения
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsUseCase: SettingsUseCase
) : ViewModel() {

    private val _autoBoost = MutableStateFlow(false)
    val autoBoost: StateFlow<Boolean> = _autoBoost.asStateFlow()

    private val _language = MutableStateFlow("Русский")
    val language: StateFlow<String> = _language.asStateFlow()

    init {
        loadSettings()
    }

    /**
     * Загружает сохраненные настройки
     */
    private fun loadSettings() {
        viewModelScope.launch {
            try {
                _autoBoost.value = settingsUseCase.isAutoBoostEnabled()
                _language.value = settingsUseCase.getLanguage()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Включает/отключает автоматический буст
     */
    fun setAutoBoost(enabled: Boolean) {
        viewModelScope.launch {
            try {
                settingsUseCase.setAutoBoostEnabled(enabled)
                _autoBoost.value = enabled
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Показывает диалог выбора языка
     */
    fun showLanguageDialog() {
        // Реализация будет в Activity/Screen
    }

    /**
     * Открывает Telegram канал @unlymng
     */
    fun openTelegramChannel(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("tg://resolve?domain=unlymng")
            context.startActivity(intent)
        } catch (e: Exception) {
            // Если Telegram не установлен, открываем в браузере
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://t.me/unlymng")
            context.startActivity(intent)
        }
    }

    /**
     * Устанавливает язык приложения
     */
    fun setLanguage(lang: String) {
        viewModelScope.launch {
            try {
                settingsUseCase.setLanguage(lang)
                _language.value = lang
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
