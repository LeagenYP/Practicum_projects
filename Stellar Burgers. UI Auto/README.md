# 🧪 Проект: UI‑автотесты для Stellar Burgers

## 📋 О проекте
Проект реализует автоматизированное тестирование веб‑интерфейса Stellar Burgers по паттерну Page Object. Проверяются сценарии регистрации, авторизации разными способами и навигации в разделе «Конструктор». Тесты запускаются в Google Chrome и Яндекс Браузере, результаты фиксируются в Allure‑отчётах. Проект — Maven‑модуль на Java 11 с JUnit 4, Selenium 4.33.0 и WebDriverManager для управления драйверами.

---

## 🗺️ Карта элементов (Page Object)

### MainPage
Главная страница и точка входа в остальные страницы. Содержит:
- **Локаторы:**
  - `constructorLink` — ссылка «Конструктор» (переход в конструктор бургеров).
  - `personalAccountButtonOnMainPage` — кнопка «Личный кабинет» на главной.
  - `loginInAccountButtonOnMainPage` — кнопка «Войти в аккаунт» на главной.
  - `constructorBun`, `constructorSauce`, `constructorFilling` — вкладки разделов «Булки», «Соусы», «Начинки» (видны в конструкторе).
- **Методы:**
  - `openPage()` — открывает главную страницу.
  - `clickOnLoginInAccountButtonOnMainPage()` — кликает на «Войти в аккаунт».
  - `clickOnPersonalAccountButtonOnMainPage()` — кликает на «Личный кабинет».
  - `goToRegisterForm()` — переходит к форме регистрации (через кнопку/ссылку).
  - `goToForgotPasswordForm()` — переходит к форме восстановления пароля.
  - `fillDataUserForAuthorization(email, password)` — заполняет поля логина и пароля.
  - `checkSuccessLogin()` — проверяет успешность входа (например, наличие элемента профиля).
  - `isTabActive(tabElement)` — проверяет активность вкладки (CSS‑класс или атрибут).
  - `openBunTabInConstructor()`, `openSauceTabInConstructor()`, `openFillingTabInConstructor()` — переключает вкладки конструктора.

### RegisterPage (подразумевается в MainPage.goToRegisterForm + методы заполнения)
Форма регистрации:
- **Поля:**
  - `registerNameInput`, `registerEmailInput`, `registerPasswordInput` — имя, email, пароль.
- **Элементы:**
  - `registerSubmitButton` — кнопка регистрации.
  - `incorrectPasswordError` — сообщение об ошибке «пароль должен быть не менее 6 символов».
- **Методы:**
  - `fillDataUserAndRegister(name, email, password)` — вводит данные и нажимает кнопку регистрации.
  - `checkIncorrectPasswordError()` — проверяет видимость сообщения об ошибке при коротком пароле.

### LoginPage / ForgotPasswordPage
Формы входа и восстановления пароля (в текущей реализации также управляются через MainPage):
- **Поля:** `loginEmailInput`, `loginPasswordInput`.
- **Кнопки:** `loginSubmitButton`.
- **Ссылки:** `goToRegisterFromLogin`, `goToLoginFromForgotPassword`.

---

## 🧪 Реализованные тесты

### 🔹 TestRegisterForm — регистрация
- `testRegisterFormSuccess` — успешная регистрация нового пользователя: переход на форму, ввод корректных данных (имя, email, пароль ≥6 символов), возврат на главную, вход через «Личный кабинет», проверка успешного входа.
- `testIncorrectPasswordErrorWhenPasswordLessThanSixSymbolsDuringRegistration` — регистрация с паролем из 5 символов: проверка появления сообщения об ошибке валидации.

### 🔹 TestAuthorizationWays — способы входа
- `testAuthorizationWayFromMainPage` — вход по кнопке «Войти в аккаунт» на главной: открытие страницы, клик по кнопке, ввод учётных данных, проверка успешного входа.
- `testAuthorizationWayFromPersonalAccountButtonOnMainPage` — вход через «Личный кабинет»: клик по кнопке, форма входа, ввод данных, проверка входа.
- `testAuthorizationWayFromRegisterForm` — переход из формы регистрации к форме входа: клик по ссылке «Войти», ввод данных, проверка входа.
- `testAuthorizationWayFromForgotPasswordForm` — переход из формы восстановления пароля к форме входа: переход к форме, клик «Войти», ввод данных, проверка входа.

### 🔹 TestConstructor — навигация в конструкторе
- `testBunSectionActive` — проверка, что можно активировать вкладку «Булки» и она становится активной.
- `testSauceSectionActive` — активация и проверка вкладки «Соусы».
- `testFillingSectionActive` — активация и проверка вкладки «Начинки».

---

## 🌐 Поддержка браузеров: Chrome и Яндекс

Реализована через `BaseUITest.startBrowser()`:
- По умолчанию запускается Google Chrome.
- При указании `-Dbrowser=yandex` запускается Яндекс Браузер с драйвером по пути `yandexDriverPath`.

**Как запускать:**
- Chrome (по умолчанию):  
  `mvn clean test`
- Яндекс Браузер:  
  `mvn clean test -Dbrowser=yandex -Dyandex.driver.path="путь/к/yandexdriver.exe"`

> ⚠️ Для Яндекс Браузера важно, чтобы `yandexdriver.exe` был доступен, а путь указан корректно. WebDriverManager автоматически скачивает ChromeDriver для Chrome.

---

## 🛠️ Стек технологий и версий (pom.xml)
Java: 11 (Compiler source/target)  
JUnit: 4.13.1 — фреймворк для тестирования  
Selenium Java: 4.33.0 — автоматизация браузера  
WebDriverManager: 6.3.3 — автоматическое управление драйверами (Chrome, Yandex)  
Allure: 2.15.0 — генерация отчётов (AllureJunit4, Allure‑RestAssured)  
AspectJ: 1.9.7 — интеграция Allure с JUnit  
JavaFaker: 1.0.2 — генерация уникальных тестовых данных (в перспективе)  
Lombok: 1.18.46 — сокращение шаблонного кода  
RestAssured: 5.3.2 — вспомогательные API‑запросы (например, удаление тестовых пользователей)  
Gson & Jackson: работа с JSON  
JaCoCo: 0.8.7 — отчёт о покрытии кода  
Apache Maven Compiler Plugin: сборка под Java 11  
Apache Maven Surefire Plugin: запуск тестов с поддержкой Allure  