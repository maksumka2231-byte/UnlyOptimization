package com.unlymng.optimization.domain.usecase

import com.unlymng.optimization.data.repository.GameManagerRepository
import javax.inject.Inject

/**
 * UseCase для управления установленными играми
 */
class GameManagerUseCase @Inject constructor(
    private val gameManagerRepository: GameManagerRepository
) {

    /**
     * Получает список установленных популярных игр
     */
    suspend fun getInstalledGames(): List<String> {
        return gameManagerRepository.getInstalledGames()
    }

    /**
     * Запускает игру по названию пакета
     */
    suspend fun launchGame(gameName: String) {
        gameManagerRepository.launchGame(gameName)
    }
}
