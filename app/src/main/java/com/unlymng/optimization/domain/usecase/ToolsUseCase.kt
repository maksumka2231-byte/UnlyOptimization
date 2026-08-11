package com.unlymng.optimization.domain.usecase

import com.unlymng.optimization.data.repository.ToolsRepository
import javax.inject.Inject

/**
 * UseCase для различных инструментов оптимизации
 */
class ToolsUseCase @Inject constructor(
    private val toolsRepository: ToolsRepository
) {

    /**
     * Включает/отключает режим игры
     */
    suspend fun toggleGameMode(enabled: Boolean) {
        toolsRepository.toggleGameMode(enabled)
    }

    /**
     * Очищает RAM (убивает фоновые процессы)
     */
    suspend fun cleanRAM() {
        toolsRepository.cleanRAM()
    }

    /**
     * Открывает менеджер приложений
     */
    suspend fun openAppManager() {
        toolsRepository.openAppManager()
    }

    /**
     * Тестирует пинг до разных серверов
     */
    suspend fun testPing() {
        toolsRepository.testPing()
    }

    /**
     * Оптимизирует сетевые параметры
     */
    suspend fun optimizeNetwork() {
        toolsRepository.optimizeNetwork()
    }
}
