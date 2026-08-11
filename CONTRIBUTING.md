# Contributing to Unly Optimization

## 🤝 Как внести вклад

Мы приветствуем вклад в развитие проекта! Вот как вы можете помочь:

### 1. Репортинг ошибок
- Откройте Issues на GitHub
- Опишите проблему подробно
- Приложите скриншоты или видео если возможно
- Укажите версию Android и модель устройства

### 2. Предложение новых функций
- Обсудите идею в Issues
- Получите одобрение от мейнтейнеров
- Создайте Pull Request

### 3. Исправление кода
- Создайте fork репозитория
- Создайте новую ветку: `git checkout -b fix/issue-name`
- Внесите изменения
- Создайте Pull Request

## 📋 Правила кодирования

### Kotlin стиль
```kotlin
// ✅ Правильно
class MyClass {
    fun myFunction(): String = "value"
    
    companion object {
        const val CONSTANT = "value"
    }
}

// ❌ Неправильно
class myClass{
    fun my_function() : String {
        return "value"
    }
}
```

### Комментарии
```kotlin
/**
 * Краткое описание функции
 * @param param1 описание параметра
 * @return описание возвращаемого значения
 */
fun myFunction(param1: String): Boolean {
    // Комментарий для сложной логики
    return true
}
```

## 🔄 Git Workflow

1. Fork репозитория
2. Создайте feature ветку
   ```bash
   git checkout -b feature/new-feature
   ```
3. Commit ваши изменения
   ```bash
   git commit -m "[feat]: Add new feature"
   ```
4. Push ветку
   ```bash
   git push origin feature/new-feature
   ```
5. Создайте Pull Request

## 📝 Commit messages

```
[type]: Brief description

Optional detailed explanation

Types:
- [feat]: New feature
- [fix]: Bug fix
- [refactor]: Code refactoring
- [docs]: Documentation changes
- [style]: Code style changes
- [test]: Test changes
- [chore]: Build/dependency changes
```

## 🧪 Тестирование

- Тестируйте на разных версиях Android (26+)
- Проверяйте на разных устройствах
- Тестируйте все языки интерфейса
- Проверяйте производительность приложения

## 📖 Документация

- Обновляйте README.md при добавлении новых функций
- Документируйте публичные API
- Добавляйте примеры использования

## 🎯 Требования к Pull Request

- Название: `[type]: Brief description`
- Описание: Подробно объясните изменения
- Ссылка на Issue: Укажите номер Issue если есть
- Скриншоты: Приложите скриншоты UI изменений
- Тестирование: Опишите как вы тестировали

## 📞 Контакты

- **Telegram**: [@unlymng](https://t.me/unlymng)
- **Email**: maksumka2231@gmail.com
- **GitHub**: [maksumka2231-byte](https://github.com/maksumka2231-byte)

---

Спасибо за вклад в развитие Unly Optimization! 🚀
