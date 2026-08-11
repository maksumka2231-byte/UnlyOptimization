package com.unlymng.optimization.data.repository.impl

import android.content.Context
import android.os.Build
import com.unlymng.optimization.data.repository.MonitoringRepository
import com.unlymng.optimization.util.CPUMonitor
import com.unlymng.optimization.util.RAMMonitor
import com.unlymng.optimization.util.TemperatureReader
import com.unlymng.optimization.util.PingUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Реализация MonitoringRepository
 */
class MonitoringRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cpuMonitor: CPUMonitor,
    private val ramMonitor: RAMMonitor,
    private val temperatureReader: TemperatureReader,
    private val pingUtil: PingUtil
) : MonitoringRepository {

    override fun getCPUUsage(): Flow<Int> {
        return cpuMonitor.getCPUUsage()
    }

    override fun getRAMUsage(): Flow<Int> {
        return ramMonitor.getRAMUsage()
    }

    override fun getTemperature(): Flow<Int> {
        return temperatureReader.getTemperature()
    }

    override fun getFPS(): Flow<Int> {
        // FPS мониторинг через Choreographer
        return cpuMonitor.getFPS()
    }

    override fun getPing(): Flow<Int> {
        return pingUtil.getPing()
    }
}
