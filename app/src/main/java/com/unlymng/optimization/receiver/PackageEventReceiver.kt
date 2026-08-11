package com.unlymng.optimization.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Broadcast Receiver для отслеживания установки и удаления приложений
 */
class PackageEventReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_PACKAGE_ADDED -> {
                // Приложен��е было установлено
            }
            Intent.ACTION_PACKAGE_REMOVED -> {
                // Приложение было удалено
            }
        }
    }
}
