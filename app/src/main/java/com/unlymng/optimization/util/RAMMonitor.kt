package com.unlymng.optimization.util

import android.app.ActivityManager
import android.content.Context
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Мониторинг использования оперативной памяти (RAM)
 */
class RAMMonitor @Inject constructor() {

    /**
     * Возвращает поток значений использования RAM в процентах
     */
    fun getRAMUsage(): Flow<Int> = flow {
        while (true) {
            try {
                val usage = readRAMUsage()
                emit(usage)
                delay(1000)
            } catch (e: Exception) {
                emit(0)
                e.printStackTrace()
            }
        }
    }

    private fun readRAMUsage(): Int {
        return try {
            val runtime = Runtime.getRuntime()
            val maxMemory = runtime.maxMemory() / (1024 * 1024) // MB
            val totalMemory = runtime.totalMemory() / (1024 * 1024) // MB
            val freeMemory = runtime.freeMemory() / (1024 * 1024) // MB
            val usedMemory = totalMemory - freeMemory
            val percentage = (usedMemory * 100 / maxMemory).toInt()
            percentage
        } catch (e: Exception) {
            0
        }
    }
}
