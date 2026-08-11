# 🚀 Unly Optimization - Мобильное приложение для оптимизации производительности игр

## 📱 Описание

**Unly Optimization** - это профессиональное приложение для оптимизации производительности мобильных игр на Android. Приложение помогает улучшить FPS, снизить задержку сети и оптимизировать использование системных ресурсов.

### Основные возможности:
- 🎮 **Автоматический буст при запуске игры** - оптимизирует систему перед игрой
- 📊 **Мониторинг в реальном времени** - отслеживает CPU, RAM, температуру, FPS и пинг
- ⚡ **Инструменты оптимизации** - чистка памяти, управление приложениями, тест пинга
- 🌐 **Поддержка 5 языков** - Русский, Английский, Португальский, Турецкий, Арабский
- 🔐 **Система активации** - 150 постоянных кодов + 5 пробных кодов на 7 дней
- 🎯 **Интегрированный реестр игр** - предзагруженный список популярных игр

## 🏗️ Архитектура

Приложение построено на принципах **Clean Architecture** с использованием:

```
App Architecture:
├── Presentation Layer (UI/Screens)
│   ├── BoostScreen
│   ├── MonitorScreen
│   ├── ToolsScreen
│   └── SettingsScreen
├── Domain Layer (Business Logic)
│   ├── UseCases
│   └── Models
└── Data Layer (Repositories & Services)
    ├── Repositories
    ├── Services
    └── Utils
```

## 📦 Технологический стек

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture with MVVM
- **DI**: Hilt
- **Storage**: DataStore Preferences
- **Database**: Room (для истории)
- **Concurrency**: Coroutines + Flow
- **Navigation**: Compose Navigation

## 🎯 Основные компоненты

### 1. Экран Boost (Оптимизация)
- Большая кнопка для ручного бустирования системы
- Отображение результатов оптимизации
- Быстрый доступ к установленным играм
- Одиночный запуск игры с предварительной оптимизацией

### 2. Экран Monitor (Мониторинг)
- Использование CPU в процентах
- Использование RAM в процентах
- Температура батареи/процессора (°C)
- FPS (кадры в секунду)
- Пинг до сервера (ms)

### 3. Экран Tools (Инструменты)
- **Режим игры** - оптимизация для игр (отключение синхронизации, автояркости)
- **Очистка RAM** - ручная чистка оперативной памяти
- **Менеджер приложений** - управление установленными приложениями
- **Тест пинга** - проверка задержки сети
- **Оптимизация сети** - настройка сетевых параметров

### 4. Экран Settings (Настройки)
- Включение/отключение автобуста при запуске игры
- Выбор языка интерфейса
- Ссылка на Telegram канал (@unlymng)
- Информация о версии приложения

## 🔐 Система активации

### Постоянные коды (150 шт)
Формат: `UNLY-2024-0001` до `UNLY-2024-0150`
- Действуют **навсегда** после активации
- Каждый код можно использовать только один раз
- Доступ ко всем функциям приложения

### Пробные коды (5 шт)
Формат: `UNLY-TRIAL-001` до `UNLY-TRIAL-005`
- Действуют **7 дней** после активации
- Каждый код можно использовать только один раз
- Полный доступ на время пробного периода

### Использование кодов
```kotlin
val activationService = ActivationCodeService(context)

// Проверка кода
if (activationService.validateCode("UNLY-2024-0001")) {
    // Код активирован
}

// Проверка статуса
val isActivated = activationService.isActivated()
val isTrialValid = activationService.isTrialValid()
val remainingDays = activationService.getRemainingTrialDays()
```

## 🛠️ Установка и сборка

### Требования
- Android SDK 26+
- Kotlin 1.9+
- Gradle 8.0+

### Сборка проекта

```bash
# Клонирование репозитория
git clone https://github.com/maksumka2231-byte/UnlyOptimization.git
cd UnlyOptimization

# Сборка Debug версии
./gradlew assembleDebug

# Сборка Release версии
./gradlew assembleRelease
```

## 📁 Структура проекта

```
app/src/main/
├── java/com/unlymng/optimization/
│   ├── MainActivity.kt
│   ├── UnlyOptimizationApp.kt
│   ├── di/
│   │   └── AppModule.kt
│   ├── domain/
│   │   ├── model/
│   │   │   └── BoostResult.kt
│   │   └── usecase/
│   │       ├── BoostUseCase.kt
│   │       ├── GameManagerUseCase.kt
│   │       ├── MonitoringUseCase.kt
│   │       ├── ToolsUseCase.kt
│   │       └── SettingsUseCase.kt
│   ├── data/
│   │   ├── repository/
│   │   │   ├── BoostRepository.kt
│   │   │   ├── GameManagerRepository.kt
│   │   │   ├── MonitoringRepository.kt
│   │   │   ├── ToolsRepository.kt
│   │   │   ├── SettingsRepository.kt
│   │   │   └── impl/
│   │   │       ├── BoostRepositoryImpl.kt
│   │   │       ├── GameManagerRepositoryImpl.kt
│   │   │       ├── MonitoringRepositoryImpl.kt
│   │   │       ├── ToolsRepositoryImpl.kt
│   │   │       └── SettingsRepositoryImpl.kt
│   │   └── service/
│   │       └── ActivationCodeService.kt
│   ├── ui/
│   │   ├── screens/
│   │   │   ├── BoostScreen.kt
│   │   │   ├── MonitorScreen.kt
│   │   │   ├── ToolsScreen.kt
│   │   │   └── SettingsScreen.kt
│   │   ├── viewmodel/
│   │   │   ├── BoostViewModel.kt
│   │   │   ├── MonitorViewModel.kt
│   │   │   ├── ToolsViewModel.kt
│   │   │   └── SettingsViewModel.kt
│   │   ├── navigation/
│   │   │   ├── NavGraph.kt
│   │   │   └── BottomNav.kt
│   │   └── theme/
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── service/
│   │   ├── FloatingOverlayService.kt
│   │   └── AccessibilityAutoBoostService.kt
│   ├── receiver/
│   │   └── PackageEventReceiver.kt
│   └── util/
│       ├── MemoryCleaner.kt
│       ├── ProcessKiller.kt
│       ├── TemperatureReader.kt
│       ├── PingUtil.kt
│       ├── CPUMonitor.kt
│       └── RAMMonitor.kt
├── res/
│   ├── values/
│   │   └── strings.xml (Русский)
│   ├── values-en/
│   │   └── strings.xml (English)
│   ├── values-pt/
│   │   └── strings.xml (Português)
│   ├── values-tr/
│   │   └── strings.xml (Türkçe)
│   ├── values-ar/
│   │   └── strings.xml (العربية)
│   ├── xml/
│   │   └── accessibility_service_config.xml
│   └── mipmap/
│       └── ic_launcher.xml
└── AndroidManifest.xml
```

## 🔑 Все доступные коды активации

### Постоянные коды (150 шт)
```
UNLY-2024-0001 до UNLY-2024-0150
```

### Пробные коды (5 шт)
```
UNLY-TRIAL-001
UNLY-TRIAL-002
UNLY-TRIAL-003
UNLY-TRIAL-004
UNLY-TRIAL-005
```

## 🎮 Поддерживаемые игры

- PUBG Mobile (com.tencent.ig)
- Mobile Legends (com.mobile.legends)
- Call of Duty Mobile (com.activision.callofduty.shooter)
- Free Fire (com.dts.freefireth)
- И другие популярные игры

## 📋 Разрешения (Permissions)

```xml
<!-- Система и оптимизация -->
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.PACKAGE_USAGE_STATS" />
<uses-permission android:name="android.permission.BIND_ACCESSIBILITY_SERVICE" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />

<!-- Сеть -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />

<!-- Аудио -->
<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />

<!-- Уведомления -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

<!-- Информация о приложениях -->
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES" />
```

## 🌐 Поддерживаемые языки

| Язык | Код | Статус |
|------|------|--------|
| Русский | ru | ✅ Готово |
| English | en | ✅ Готово |
| Português | pt | ✅ Готово |
| Türkçe | tr | ✅ Готово |
| العربية | ar | ✅ Готово |

## 👨‍💻 Разработка

### Структура Git
- **main** - стабильная версия
- **develop** - версия в разработке
- **feature/** - новые функции

### Commit сообщения
```
[Type]: Description

Types: feat, fix, refactor, docs, style, test, chore
```

## 📞 Контакты

- **Telegram**: [@unlymng](https://t.me/unlymng)
- **GitHub**: [maksumka2231-byte](https://github.com/maksumka2231-byte)

## 📄 Лицензия

Это приложение распространяется свободно для личного и коммерческого использования.

## ⚠️ Дисклеймер

Это приложение предоставляется "как есть" без каких-либо гарантий. Автор не несет ответственности за потерю данных или какие-либо другие убытки, возникшие из-за использования этого приложения.

## 🎉 Спасибо за использование Unly Optimization!

---

**Версия**: 1.0.0  
**Последнее обновление**: 11.08.2026  
**Разработчик**: maksumka2231-byte
