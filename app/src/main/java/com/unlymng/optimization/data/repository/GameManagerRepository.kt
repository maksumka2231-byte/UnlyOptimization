package com.unlymng.optimization.data.repository

/**
 * Repository для управления играми
 */
interface GameManagerRepository {
    suspend fun getInstalledGames(): List<String>
    suspend fun launchGame(gameName: String): Unit
}
