package com.unlymng.optimization.domain.usecase

import com.unlymng.optimization.domain.model.BoostResult
import com.unlymng.optimization.data.repository.BoostRepository
import javax.inject.Inject

/**
 * UseCase для выполнения оптимизации системы
 * Управляет очисткой памяти, кэша и остановкой фоновых процессов
 */
class BoostUseCase @Inject constructor(
    private val boostRepository: BoostRepository
) {

    /**
     * Выполняет полный буст системы:
     * 1. Сканирование системы
     * 2. Очистка кэша приложений
     * 3. Остановка фоновых процессов
     * 4. Освобождение RAM
     * 5. Включение режима игры
     */
    suspend fun boost(): BoostResult {
        val startTime = System.currentTimeMillis()
        
        // Получаем начальное значение памяти
        val initialMemory = boostRepository.getAvailableMemory()
        
        // Очищаем кэш
        boostRepository.clearAppCache()
        
        // Останавливаем фоновые процессы
        val stoppedProcesses = boostRepository.killBackgroundProcesses()
        
        // Получаем конечное значение памяти
        val finalMemory = boostRepository.getAvailableMemory()
        val freedMemory = finalMemory - initialMemory
        
        // Включаем режим игры
        boostRepository.enableGameMode()
        
        val duration = System.currentTimeMillis() - startTime
        
        return BoostResult(
            freedMemoryMB = freedMemory,
            stoppedProcesses = stoppedProcesses,
            clearedCacheMB = boostRepository.getClearedCacheSize(),
            durationMs = duration
        )
    }
}
