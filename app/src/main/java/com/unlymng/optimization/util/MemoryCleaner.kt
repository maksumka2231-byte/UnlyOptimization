package com.unlymng.optimization.util

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import javax.inject.Inject

/**
 * Утилита для очистки памяти и кэша приложений
 */
class MemoryCleaner @Inject constructor() {

    /**
     * Очищает кэш всех приложений и возвращает количество очищенных байт
     */
    fun clearAppCache(context: Context): Long {
        var totalClearedSize = 0L
        try {
            val pm = context.packageManager
            val packages = pm.getInstalledPackages(0)
            
            for (packageInfo in packages) {
                val packageName = packageInfo.packageName
                try {
                    // Получаем размер кэша приложения
                    val cacheDir = context.getDir(packageName, Context.MODE_PRIVATE)
                    totalClearedSize += deleteDir(cacheDir)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return totalClearedSize
    }

    private fun deleteDir(dir: java.io.File): Long {
        var size = 0L
        if (dir.isDirectory) {
            val children = dir.listFiles()
            if (children != null) {
                for (child in children) {
                    size += deleteDir(child)
                }
            }
        }
        if (dir.delete()) {
            size += dir.length()
        }
        return size
    }
}
