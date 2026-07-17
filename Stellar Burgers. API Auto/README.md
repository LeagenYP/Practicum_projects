# 🧪 Проект: автотесты API для Stellar Burgers

## 📋 О проекте
Проект посвящён автоматизированному тестированию API сервиса Stellar Burgers. Реализованы тесты на RestAssured для ключевых сценариев: регистрация и авторизация пользователей, создание заказов (с разными комбинациями параметров: с/без авторизации, с/без ингредиентов, с некорректными данными). Применены Allure‑отчёты для наглядной аналитики, очистка тестовых данных после каждого теста, использование JavaFaker (в перспективе) и Lombok для сокращения шаблонного кода. Проект — Maven‑модуль на Java 11 с JUnit 4.

---

## 🧪 Реализованные тесты

### 🔹 TestCreateUser — тесты создания пользователя
- `testCreateUser` — успешное создание уникального пользователя: валидация статуса `200 OK` (в задании так, хотя семантически чаще используют `201 Created`).
- `testCreateUserTwice` — попытка повторного создания пользователя с теми же данными: статус `403 Forbidden` и сообщение «User already exists»; реализована дополнительная очистка данных, если второй пользователь всё же был создан.
- `testCannotCreateUserWithoutEmail` — проверка валидации отсутствия email: статус `403 Forbidden`, сообщение «Email, password and name are required fields».
- `testCannotCreateUserWithoutName` — проверка валидации отсутствия имени: статус `403 Forbidden`, аналогичное сообщение об обязательных полях.
- `testCannotCreateUserWithoutPassword` — проверка валидации отсутствия пароля: статус `403 Forbidden`, аналогичное сообщение.
- `clearData` (`@After`) — удаление тестового пользователя по токену доступа для изоляции тестов и предотвращения накопления мусора в тестовой среде.

### 🔹 TestLoginUser — тесты авторизации пользователя
- `setUp` (`@Before`) — подготовка тестового пользователя перед каждым тестом (создание).
- `testLoginUser` — успешная авторизация существующего пользователя: статус `200 OK`, поле `success: true`.
- `testLoginUserWithIncorrectPassword` — авторизация с неверным паролем: статус `401 Unauthorized`, сообщение «email or password are incorrect».
- `testLoginUserWithIncorrectEmail` — авторизация с неверным email: статус `401 Unauthorized`, аналогичное сообщение.
- `clearData` (`@After`) — очистка тестовых данных: получение токена через повторную авторизацию и удаление пользователя.

### 🔹 TestCreateOrder — тесты создания заказа
- `setUp` (`@Before`) — создание тестового пользователя и получение токена авторизации перед каждым тестом.
- `testCreateOrderWithoutAuthToken` — создание заказа без авторизации: статус `200 OK`, поля `success: true` и наличие объекта `order`.
- `testCreateOrderWithAuthTokenAndWithIngredients` — создание заказа с авторизацией и списком ингредиентов: статус `200 OK`, `success: true`, наличие `order`.
- `testCreateOrderWithAuthTokenAndWithoutIngredients` — попытка создать заказ с авторизацией, но без ингредиентов: статус `400 Bad Request`, сообщение «Ingredient ids must be provided».
- `testCreateOrderWithAuthTokenAndWithWrongIngredientsHash` — заказ с некорректным хешем ингредиента: статус `500 Internal Server Error` (отмечено несоответствие документации: логичнее было бы ожидать `400`).
- `clearData` (`@After`) — очистка: удаление тестового пользователя по токену (если он был получен).

---

## 🛠️ Стек технологий и версий (pom.xml)
Java: 11 (Compiler source/target)  
JUnit: 4.13.1 — фреймворк для тестирования  
RestAssured: 5.3.2 — библиотека для автоматизации API‑тестирования  
Allure: 2.15.0 — генерация наглядных отчётов (AllureJunit4, Allure‑RestAssured)  
AspectJ: 1.9.7 — интеграция Allure с JUnit  
JavaFaker: 1.0.2 — генерация уникальных тестовых данных (например, случайных email)  
Lombok: 1.18.46 — сокращение шаблонного кода (геттеры, сеттеры, конструкторы)  
Gson & Jackson: работа с JSON (сериализация/десериализация)  
JaCoCo: 0.8.7 — отчёт о покрытии кода (подключен, но для API‑тестов покрытие менее критично, чем для юнит‑тестов)  
Apache Maven Compiler Plugin: сборка проекта под Java 11  
Apache Maven Surefire Plugin: запуск тестов с поддержкой Allure  