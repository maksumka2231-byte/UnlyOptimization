package com.unlymng.optimization.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlymng.optimization.domain.usecase.MonitoringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана Monitor
 * Отслеживает системные параметры в реальном времени
 */
@HiltViewModel
class MonitorViewModel @Inject constructor(
    private val monitoringUseCase: MonitoringUseCase
) : ViewModel() {

    private val _cpuUsage = MutableStateFlow(0)
    val cpuUsage: StateFlow<Int> = _cpuUsage.asStateFlow()

    private val _ramUsage = MutableStateFlow(0)
    val ramUsage: StateFlow<Int> = _ramUsage.asStateFlow()

    private val _temperature = MutableStateFlow(0)
    val temperature: StateFlow<Int> = _temperature.asStateFlow()

    private val _fps = MutableStateFlow(0)
    val fps: StateFlow<Int> = _fps.asStateFlow()

    private val _ping = MutableStateFlow(0)
    val ping: StateFlow<Int> = _ping.asStateFlow()

    init {
        startMonitoring()
    }

    /**
     * Запускает мониторинг системных параметров
     * Обновляет данные в реальном времени
     */
    private fun startMonitoring() {
        viewModelScope.launch {
            try {
                monitoringUseCase.monitorCPU().collect { value ->
                    _cpuUsage.value = value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                monitoringUseCase.monitorRAM().collect { value ->
                    _ramUsage.value = value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                monitoringUseCase.monitorTemperature().collect { value ->
                    _temperature.value = value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                monitoringUseCase.monitorFPS().collect { value ->
                    _fps.value = value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                monitoringUseCase.monitorPing().collect { value ->
                    _ping.value = value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
