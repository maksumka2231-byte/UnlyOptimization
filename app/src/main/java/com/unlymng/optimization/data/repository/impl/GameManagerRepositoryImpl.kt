package com.unlymng.optimization.data.repository.impl

import android.content.Context
import android.content.Intent
import com.unlymng.optimization.data.repository.GameManagerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Реализация GameManagerRepository
 */
class GameManagerRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GameManagerRepository {

    override suspend fun getInstalledGames(): List<String> {
        // На демо - возвращаем популярные игры
        return listOf(
            "PUBG Mobile",
            "Mobile Legends",
            "Call of Duty",
            "Free Fire",
            "CODM"
        )
    }

    override suspend fun launchGame(gameName: String) {
        // Получаем пакет названия и uзапускаем
        val packageName = mapGameNameToPackage(gameName)
        try {
            val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun mapGameNameToPackage(gameName: String): String {
        return when (gameName) {
            "PUBG Mobile" -> "com.tencent.ig"
            "Mobile Legends" -> "com.mobile.legends"
            "Call of Duty" -> "com.activision.callofduty.shooter"
            "Free Fire" -> "com.dts.freefireth"
            "CODM" -> "com.activision.callofduty.shooter"
            else -> gameName
        }
    }
}
