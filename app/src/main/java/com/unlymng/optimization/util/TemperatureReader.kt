package com.unlymng.optimization.util

import android.content.Context
import android.os.BatteryManager
import android.os.Build
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

/**
 * Утилита для чтения температуры батареи и процессора
 */
class TemperatureReader @Inject constructor() {

    /**
     * Читает температуру батареи и возвращает поток значений
     */
    fun getTemperature(): Flow<Int> = flow {
        while (true) {
            try {
                val temp = readBatteryTemperature()
                emit(temp)
                kotlinx.coroutines.delay(1000) // Обновляем каждую секунду
            } catch (e: Exception) {
                emit(0)
                e.printStackTrace()
            }
        }
    }

    private fun readBatteryTemperature(): Int {
        return try {
            val file = File("/sys/class/thermal/thermal_zone0/temp")
            if (file.exists()) {
                val temperature = file.readText().trim().toInt() / 1000 // Конвертируем в градусы
                temperature
            } else {
                0
            }
        } catch (e: Exception) {
            0
        }
    }
}
