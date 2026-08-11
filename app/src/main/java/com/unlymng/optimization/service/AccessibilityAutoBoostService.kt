package com.unlymng.optimization.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.unlymng.optimization.data.service.ActivationCodeService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Accessibility Service для автоматического буста при запуске игры
 * Отслеживает события запуска приложений и автоматически запускает оптимизацию
 */
@AndroidEntryPoint
class AccessibilityAutoBoostService : AccessibilityService() {

    @Inject
    lateinit var activationCodeService: ActivationCodeService

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Обрабатываем события доступности
        if (event != null) {
            when (event.eventType) {
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                    // Приложение было открыто
                    val packageName = event.packageName?.toString()
                    if (packageName != null && isGameApp(packageName)) {
                        // Автоматиче��кий буст при запуске игры
                        // TODO: Запустить performBoost()
                    }
                }
            }
        }
    }

    override fun onInterrupt() {
        // Вызывается при прерывании сервиса
    }

    private fun isGameApp(packageName: String): Boolean {
        return packageName.contains("game", ignoreCase = true) ||
               packageName == "com.tencent.ig" || // PUBG Mobile
               packageName == "com.mobile.legends" || // Mobile Legends
               packageName == "com.activision.callofduty.shooter" // Call of Duty
    }
}
