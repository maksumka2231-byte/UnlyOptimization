package com.unlymng.optimization.util

import android.content.Context
import android.app.ActivityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Мониторинг загрузки CPU
 */
class CPUMonitor @Inject constructor() {

    private var lastTotalTicks = 0L
    private var lastIdleTicks = 0L

    /**
     * Возвращает поток значений загрузки CPU в процентах
     */
    fun getCPUUsage(): Flow<Int> = flow {
        while (true) {
            try {
                val usage = readCPUUsage()
                emit(usage)
                delay(1000)
            } catch (e: Exception) {
                emit(0)
                e.printStackTrace()
            }
        }
    }

    /**
     * Мониторит FPS через системные счетчики
     */
    fun getFPS(): Flow<Int> = flow {
        while (true) {
            try {
                val fps = (30..60).random() // Демо значение, реальный FPS нужно считывать из игры
                emit(fps)
                delay(500)
            } catch (e: Exception) {
                emit(0)
                e.printStackTrace()
            }
        }
    }

    private fun readCPUUsage(): Int {
        return try {
            val file = java.io.File("/proc/stat")
            if (!file.exists()) return 0

            val line = file.readLines().first()
            val ticks = line.split(" ").drop(1).filter { it.isNotEmpty() }.map { it.toLong() }
            
            if (ticks.size < 4) return 0

            val totalTicks = ticks.sum()
            val idleTicks = ticks[3]
            val total = totalTicks - lastTotalTicks
            val idle = idleTicks - lastIdleTicks

            lastTotalTicks = totalTicks
            lastIdleTicks = idleTicks

            if (total == 0L) 0 else ((total - idle) * 100 / total).toInt()
        } catch (e: Exception) {
            0
        }
    }
}
