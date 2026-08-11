package com.unlymng.optimization.service

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unlymng.optimization.ui.theme.NeonCyan
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Сервис для вывода оверлея с мониторингом на весь экран
 * Показывает FPS, CPU, RAM, температуру и пинг в реальном времени
 */
@AndroidEntryPoint
class FloatingOverlayService : Service() {

    private var windowManager: WindowManager? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startOverlay()
        return START_STICKY
    }

    private fun startOverlay() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        // Параметры окна оверлея
        val params = WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            format = PixelFormat.TRANSLUCENT
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            width = 200
            height = 400
            x = 0
            y = 0
            gravity = Gravity.TOP or Gravity.START
        }

        // TODO: Добавить Compose представление оверлея
    }

    override fun onDestroy() {
        super.onDestroy()
        // Удаляем оверлей при завершении сервиса
    }
}
