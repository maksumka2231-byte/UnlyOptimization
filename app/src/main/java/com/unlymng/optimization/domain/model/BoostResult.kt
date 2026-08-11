package com.unlymng.optimization.domain.model

/**
 * Результат операции буста
 */
data class BoostResult(
    val freedMemoryMB: Long,
    val stoppedProcesses: Int,
    val clearedCacheMB: Long,
    val durationMs: Long
)
