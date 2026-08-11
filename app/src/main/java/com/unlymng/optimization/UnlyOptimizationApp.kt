package com.unlymng.optimization

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Точка входа приложения Unly Optimization с инициализацией Hilt
 * для внедрения зависимостей
 */
@HiltAndroidApp
class UnlyOptimizationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Hilt автоматически инициализируется через @HiltAndroidApp
    }
}
