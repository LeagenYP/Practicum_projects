package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;

    public final By personalAccountButtonOnMainPage = By.xpath("//*[contains(@class, 'AppHeader_header__linkText') and text()='Личный Кабинет']");
    public final By loginInAccountButtonOnMainPage = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
    public final By loginInAccountButtonFromRegisterForm = By.cssSelector(".Auth_link__1fOlj");
    public final By loginInAccountButtonFromForgotPasswordForm = By.cssSelector(".Auth_link__1fOlj");

    public final By loginFieldAuthorization = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='name']");
    public final By passwordFieldAuthorization = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    public final By enterButtonAuthorization = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти']");;
    public final By registerFormFromAuthorizationForm = By.xpath("//*[contains(@class, 'Auth_link__1fOlj') and text()='Зарегистрироваться']");

    public final By firstNameFieldForRegister = By.xpath("(//input[contains(@class, 'input__textfield') and contains(@class, 'text_type_main-default')])[1]");
    public final By emailFieldForRegister = By.xpath("(//input[contains(@class, 'input__textfield') and contains(@class, 'text_type_main-default')])[2]");
    public final By passwordFieldForRegister = By.xpath("(//input[contains(@class, 'input__textfield') and contains(@class, 'text_type_main-default')])[3]");
    public final By confirmRegisterButton = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");

    public final By profilePage = By.cssSelector(".Account_link__2ETsJ.text.text_type_main-medium.text_color_inactive.Account_link_active__2opc9");

    public final By mainLogoButton = By.className("AppHeader_header__logo__2D0X2");
    public final By incorrectPasswordError = By.cssSelector(".input__error.text_type_main-default");
    public final By forgotPasswordButtonFromAuthorizationForm = By.xpath("//a[@class='Auth_link__1fOlj' and normalize-space(text()) = 'Восстановить пароль']");

    public final By constructorBun = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text() = 'Булки']]");
    public final By constructorSauce = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text() = 'Соусы']]");
    public final By constructorFilling = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text() = 'Начинки']]");



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Отрыть страницу сайта")
    public void openPage() {
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @Step("Регистрация нового пользователя")
    public void fillDataUserAndRegister(String firstName, String login, String password) {
        driver.findElement(firstNameFieldForRegister).sendKeys(firstName);
        driver.findElement(emailFieldForRegister).sendKeys(login);
        driver.findElement(passwordFieldForRegister).sendKeys(password);
        driver.findElement(confirmRegisterButton).click();
    }

    @Step("Авторизация пользователя в личном кабинете")
    public void fillDataUserForAuthorization(String login, String password) {
        driver.findElement(loginFieldAuthorization).sendKeys(login);
        driver.findElement(passwordFieldAuthorization).sendKeys(password);
        driver.findElement(enterButtonAuthorization).click();
    }

    @Step("Перейти на главную страницу")
    public void returnToMainPage() {
        driver.findElement(mainLogoButton).click();
    }

    @Step("Проверка успешной авторизации")
    public boolean checkSuccessLogin() {
        driver.findElement(personalAccountButtonOnMainPage).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement profileElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(profilePage)
        );
        return profileElement.isDisplayed();
    }

    @Step("Проверка ошибки некорректный пароль")
    public boolean checkIncorrectPasswordError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(incorrectPasswordError)
        );
        return errorElement.isDisplayed();
    }

    @Step("Проверка активна ли вкладка конструктора")
    public boolean isTabActive(By tabLocator) {
        WebElement element = driver.findElement(tabLocator);
        String classes = element.getAttribute("class");
        assert classes != null;
        return classes.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Переход на страницу регистрации")
    public void goToRegisterForm() {
        driver.findElement(personalAccountButtonOnMainPage).click();
        driver.findElement(registerFormFromAuthorizationForm).click();
    }

    @Step("Нажатие по кнопке Личный кабинет на главной странице")
    public void clickOnPersonalAccountButtonOnMainPage() {
        driver.findElement(personalAccountButtonOnMainPage).click();
    }

    @Step("Нажатие по кнопке Войти в аккаунт на главной странице")
    public void clickOnLoginInAccountButtonOnMainPage() {
        driver.findElement(loginInAccountButtonOnMainPage).click();
    }

    @Step("Нажатие по кнопке Войти в аккаунт из формы регистарции")
    public void clickOnLoginInAccountButtonFromRegisterForm() {
        driver.findElement(loginInAccountButtonFromRegisterForm).click();
    }

    @Step("Переход на форму Восстановление пароля")
    public void goToForgotPasswordForm() {
        driver.findElement(personalAccountButtonOnMainPage).click();
        driver.findElement(forgotPasswordButtonFromAuthorizationForm).click();
    }

    @Step("Нажатие по кнопке Войти в аккаунт из формы Восстановление пароля")
    public void goToLoginInButtonFromForgotPasswordForm() {
        driver.findElement(loginInAccountButtonFromForgotPasswordForm).click();
    }

    @Step("Открыть вкладку Булки в конструкторе")
    public void openBunTabInConstructor() {
        driver.findElement(constructorBun).click();
    }

   @Step("Открыть вкладку Соусы в конструкторе")
    public void openSauceTabInConstructor() {
        driver.findElement(constructorSauce).click();
    }

   @Step("Открыть вкладку Начинки в конструкторе")
    public void openFillingTabInConstructor() {
        driver.findElement(constructorFilling).click();
    }
}
