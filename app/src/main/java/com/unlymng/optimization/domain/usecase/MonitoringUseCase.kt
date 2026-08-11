package com.unlymng.optimization.domain.usecase

import com.unlymng.optimization.data.repository.MonitoringRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase для мониторинга системных параметров в реальном времени
 */
class MonitoringUseCase @Inject constructor(
    private val monitoringRepository: MonitoringRepository
) {

    /**
     * Мониторит использование CPU в процентах
     */
    fun monitorCPU(): Flow<Int> {
        return monitoringRepository.getCPUUsage()
    }

    /**
     * Мониторит использование RAM в процентах
     */
    fun monitorRAM(): Flow<Int> {
        return monitoringRepository.getRAMUsage()
    }

    /**
     * Мониторит температуру батареи и процессора
     */
    fun monitorTemperature(): Flow<Int> {
        return monitoringRepository.getTemperature()
    }

    /**
     * Мониторит FPS (кадры в секунду)
     */
    fun monitorFPS(): Flow<Int> {
        return monitoringRepository.getFPS()
    }

    /**
     * Мониторит пинг до игровых серверов
     */
    fun monitorPing(): Flow<Int> {
        return monitoringRepository.getPing()
    }
}
