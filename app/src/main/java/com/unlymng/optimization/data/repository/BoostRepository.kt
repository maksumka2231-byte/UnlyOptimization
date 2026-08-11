package com.unlymng.optimization.data.repository

import com.unlymng.optimization.domain.model.BoostResult

/**
 * Repository для операций буста и оптимизации
 */
interface BoostRepository {
    suspend fun getAvailableMemory(): Long
    suspend fun clearAppCache(): Unit
    suspend fun killBackgroundProcesses(): Int
    suspend fun enableGameMode(): Unit
    suspend fun getClearedCacheSize(): Long
}
