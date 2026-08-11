package com.unlymng.optimization.data.repository

/**
 * Repository для различных тоолов оптимизации
 */
interface ToolsRepository {
    suspend fun toggleGameMode(enabled: Boolean): Unit
    suspend fun cleanRAM(): Unit
    suspend fun openAppManager(): Unit
    suspend fun testPing(): Unit
    suspend fun optimizeNetwork(): Unit
}
