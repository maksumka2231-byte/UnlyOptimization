package com.unlymng.optimization.data.repository

import kotlinx.coroutines.flow.Flow

/**
 * Repository для мониторинга системных параметров
 */
interface MonitoringRepository {
    fun getCPUUsage(): Flow<Int>
    fun getRAMUsage(): Flow<Int>
    fun getTemperature(): Flow<Int>
    fun getFPS(): Flow<Int>
    fun getPing(): Flow<Int>
}
