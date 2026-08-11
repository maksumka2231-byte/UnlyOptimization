package com.unlymng.optimization.di

import com.unlymng.optimization.data.repository.*
import com.unlymng.optimization.data.repository.impl.*
import com.unlymng.optimization.util.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt модуль для внедрения зависимостей
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Утилиты
    @Provides
    @Singleton
    fun provideMemoryCleaner(): MemoryCleaner = MemoryCleaner()

    @Provides
    @Singleton
    fun provideProcessKiller(): ProcessKiller = ProcessKiller()

    @Provides
    @Singleton
    fun provideTemperatureReader(): TemperatureReader = TemperatureReader()

    @Provides
    @Singleton
    fun providePingUtil(): PingUtil = PingUtil()

    @Provides
    @Singleton
    fun provideCPUMonitor(): CPUMonitor = CPUMonitor()

    @Provides
    @Singleton
    fun provideRAMMonitor(): RAMMonitor = RAMMonitor()

    // Repositories
    @Provides
    @Singleton
    fun provideBoostRepository(
        impl: BoostRepositoryImpl
    ): BoostRepository = impl

    @Provides
    @Singleton
    fun provideGameManagerRepository(
        impl: GameManagerRepositoryImpl
    ): GameManagerRepository = impl

    @Provides
    @Singleton
    fun provideMonitoringRepository(
        impl: MonitoringRepositoryImpl
    ): MonitoringRepository = impl

    @Provides
    @Singleton
    fun provideToolsRepository(
        impl: ToolsRepositoryImpl
    ): ToolsRepository = impl

    @Provides
    @Singleton
    fun provideSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository = impl
}
