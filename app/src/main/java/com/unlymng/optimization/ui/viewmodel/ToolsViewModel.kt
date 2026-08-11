package com.unlymng.optimization.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlymng.optimization.domain.usecase.ToolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана Tools
 * Управляет различными инструментами оптимизации
 */
@HiltViewModel
class ToolsViewModel @Inject constructor(
    private val toolsUseCase: ToolsUseCase
) : ViewModel() {

    private val _gameMode = MutableStateFlow(false)
    val gameMode: StateFlow<Boolean> = _gameMode.asStateFlow()

    /**
     * Переключает режим игры
     */
    fun toggleGameMode(enabled: Boolean) {
        viewModelScope.launch {
            try {
                toolsUseCase.toggleGameMode(enabled)
                _gameMode.value = enabled
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Выполняет очистку RAM
     */
    fun cleanRAM() {
        viewModelScope.launch {
            try {
                toolsUseCase.cleanRAM()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Открывает менеджер приложений
     */
    fun openAppManager() {
        viewModelScope.launch {
            try {
                toolsUseCase.openAppManager()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Проверяет пинг до игровых серверов
     */
    fun testPing() {
        viewModelScope.launch {
            try {
                toolsUseCase.testPing()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Оптимизирует сетевые параметры
     */
    fun optimizeNetwork() {
        viewModelScope.launch {
            try {
                toolsUseCase.optimizeNetwork()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
