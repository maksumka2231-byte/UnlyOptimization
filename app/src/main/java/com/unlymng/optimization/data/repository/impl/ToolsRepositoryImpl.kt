package com.unlymng.optimization.data.repository.impl

import android.content.Context
import android.app.ActivityManager
import com.unlymng.optimization.data.repository.ToolsRepository
import com.unlymng.optimization.util.ProcessKiller
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Реализация ToolsRepository
 */
class ToolsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val processKiller: ProcessKiller
) : ToolsRepository {

    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    override suspend fun toggleGameMode(enabled: Boolean) {
        if (enabled) {
            // Отключаем автояркость и автосинк
            disableAutoSync()
            disableAutoScreenBrightness()
        } else {
            // Восстанавливаем нормальные настройки
            enableAutoSync()
            enableAutoScreenBrightness()
        }
    }

    override suspend fun cleanRAM() {
        processKiller.killBackgroundProcesses(context)
    }

    override suspend fun openAppManager() {
        // Открываем Settings -> Applications
        val intent = android.content.Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS)
        context.startActivity(intent)
    }

    override suspend fun testPing() {
        // Проверяем пинг до популярных серверов
    }

    override suspend fun optimizeNetwork() {
        // Цистим сетевые настройки
    }

    private fun disableAutoSync() {
        try {
            android.provider.Settings.Secure.putInt(
                context.contentResolver,
                android.provider.Settings.Secure.MASTER_SYNC_AUTOMATIC,
                0
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enableAutoSync() {
        try {
            android.provider.Settings.Secure.putInt(
                context.contentResolver,
                android.provider.Settings.Secure.MASTER_SYNC_AUTOMATIC,
                1
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun disableAutoScreenBrightness() {
        try {
            android.provider.Settings.System.putInt(
                context.contentResolver,
                android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                0 // Manual
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enableAutoScreenBrightness() {
        try {
            android.provider.Settings.System.putInt(
                context.contentResolver,
                android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                1 // Automatic
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
