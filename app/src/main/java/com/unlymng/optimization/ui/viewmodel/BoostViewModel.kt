package com.unlymng.optimization.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlymng.optimization.domain.usecase.BoostUseCase
import com.unlymng.optimization.domain.usecase.GameManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана Boost
 * Управляет процессом оптимизации и запуском игр
 */
@HiltViewModel
class BoostViewModel @Inject constructor(
    private val boostUseCase: BoostUseCase,
    private val gameManagerUseCase: GameManagerUseCase
) : ViewModel() {

    private val _isBoosting = MutableStateFlow(false)
    val isBoosting: StateFlow<Boolean> = _isBoosting.asStateFlow()

    private val _boostResult = MutableStateFlow(false)
    val boostResult: StateFlow<Boolean> = _boostResult.asStateFlow()

    private val _freedMemory = MutableStateFlow(0L)
    val freedMemory: StateFlow<Long> = _freedMemory.asStateFlow()

    private val _installedGames = MutableStateFlow<List<String>>(emptyList())
    val installedGames: StateFlow<List<String>> = _installedGames.asStateFlow()

    /**
     * Выполняет полный буст системы:
     * 1. Сканирование (анимация 3 сек)
     * 2. Очистка кэша
     * 3. Остановка фоновых процессов
     * 4. Освобождение RAM
     * 5. Включение режима игры
     */
    fun performBoost() {
        viewModelScope.launch {
            _isBoosting.value = true
            try {
                val result = boostUseCase.boost()
                _freedMemory.value = result.freedMemoryMB
                _boostResult.value = true
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isBoosting.value = false
            }
        }
    }

    /**
     * Выполняет буст и запускает игру
     */
    fun boostAndLaunchGame(gameName: String) {
        viewModelScope.launch {
            try {
                performBoost()
                gameManagerUseCase.launchGame(gameName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Загружает список установленных игр
     */
    fun loadInstalledGames() {
        viewModelScope.launch {
            try {
                val games = gameManagerUseCase.getInstalledGames()
                _installedGames.value = games
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
