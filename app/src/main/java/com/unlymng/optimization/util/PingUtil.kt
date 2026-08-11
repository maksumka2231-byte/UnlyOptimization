package com.unlymng.optimization.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.InetAddress
import javax.inject.Inject

/**
 * Утилита для проверки пинга до игровых серверов
 */
class PingUtil @Inject constructor() {

    /**
     * Проверяет пинг до серверов и возвращает среднее значение
     */
    fun getPing(): Flow<Int> = flow {
        while (true) {
            try {
                val ping = withContext(Dispatchers.IO) {
                    testPingToHost("8.8.8.8")
                }
                emit(ping)
                kotlinx.coroutines.delay(2000) // Проверяем каждые 2 секунды
            } catch (e: Exception) {
                emit(0)
                e.printStackTrace()
            }
        }
    }

    private fun testPingToHost(host: String): Int {
        return try {
            val startTime = System.currentTimeMillis()
            val address = InetAddress.getByName(host)
            val reachable = address.isReachable(5000)
            val endTime = System.currentTimeMillis()
            if (reachable) (endTime - startTime).toInt() else 0
        } catch (e: Exception) {
            0
        }
    }
}
