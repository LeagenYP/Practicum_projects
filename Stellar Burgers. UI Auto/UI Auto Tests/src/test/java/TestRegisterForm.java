import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.LoginModel;
import org.junit.After;
import org.junit.Test;

import static data.UserData.*;
import static org.junit.Assert.assertTrue;
import static steps.UserSteps.deleteUser;
import static steps.UserSteps.loginUser;

public class TestRegisterForm extends BaseUITest {

    @Test
    @Description("Успешная регистрация нового пользователя")
    public void testRegisterFormSuccess() {
        // Открытие страницы и регистрация
        mainPage.openPage();
        mainPage.goToRegisterForm();
        mainPage.fillDataUserAndRegister(NAME, EMAIL, PASSWORD);

        // Авторизация через главную страницу
        mainPage.returnToMainPage();
        mainPage.clickOnPersonalAccountButtonOnMainPage();
        mainPage.fillDataUserForAuthorization(EMAIL, PASSWORD);

        // Проверка видимости элемента данных пользователя
        assertTrue(mainPage.checkSuccessLogin());
    }

    @Test
    @Description("Появляется ошибка, если при регистрации введен пароль менее 6 символов")
    public void testIncorrectPasswordErrorWhenPasswordLessThanSixSymbolsDuringRegistration() {
        // Открытие страницы и регистрация с вводом пароля в 5 симфолов
        mainPage.openPage();
        mainPage.goToRegisterForm();
        mainPage.fillDataUserAndRegister(NAME, EMAIL, "5sybm");

        // Проверка видимости ошибки
        assertTrue(mainPage.checkIncorrectPasswordError());

    }

    @After
    @Step("Очистка тестовых данных: удаление пользователя")
    public void clearData() {
        LoginModel loginModel = new LoginModel(EMAIL, PASSWORD);
        Response response = loginUser(loginModel);
        String userAuthToken = response.jsonPath().get("accessToken");
        if (userAuthToken != null) {
            deleteUser(userAuthToken);
        }
    }
}
