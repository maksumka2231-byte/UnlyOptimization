package com.unlymng.optimization.util

import android.app.ActivityManager
import android.content.Context
import javax.inject.Inject

/**
 * Утилита для завершения фоновых процессов и приложений
 */
class ProcessKiller @Inject constructor() {

    /**
     * Убивает фоновые приложения и возвращает количество убитых процессов
     */
    fun killBackgroundProcesses(context: Context): Int {
        var killedCount = 0
        try {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val runningApps = activityManager.runningAppProcesses
            
            for (appProcess in runningApps) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    try {
                        android.os.Process.killProcess(appProcess.pid)
                        killedCount++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return killedCount
    }
}
