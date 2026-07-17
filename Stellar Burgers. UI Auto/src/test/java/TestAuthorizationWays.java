import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.UserData.EMAIL;
import static data.UserData.NAME;
import static data.UserData.PASSWORD;
import static org.junit.Assert.assertTrue;
import static steps.UserSteps.*;

public class TestAuthorizationWays extends BaseUITest {
    private String userAuthToken;

    @Description("Подготовка пользователя")
    @Before
    public void setUp() {
        UserModel user = new UserModel(EMAIL, PASSWORD, NAME);
        Response response = createUser(user);
        userAuthToken = response.jsonPath().get("accessToken");
    }

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void testAuthorizationWayFromMainPage()
    {
        mainPage.openPage();
        mainPage.clickOnLoginInAccountButtonOnMainPage();
        mainPage.fillDataUserForAuthorization(EMAIL, PASSWORD);
        assertTrue(mainPage.checkSuccessLogin());
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет")
    public void testAuthorizationWayFromPersonalAccountButtonOnMainPage()
    {
        mainPage.openPage();
        mainPage.clickOnPersonalAccountButtonOnMainPage();
        mainPage.fillDataUserForAuthorization(EMAIL, PASSWORD);
        assertTrue(mainPage.checkSuccessLogin());
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void testAuthorizationWayFromRegisterForm()
    {
        mainPage.openPage();
        mainPage.goToRegisterForm();
        mainPage.clickOnLoginInAccountButtonFromRegisterForm();
        mainPage.fillDataUserForAuthorization(EMAIL, PASSWORD);
        assertTrue(mainPage.checkSuccessLogin());
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void testAuthorizationWayFromForgotPasswordForm()
    {
        mainPage.openPage();
        mainPage.goToForgotPasswordForm();
        mainPage.goToLoginInButtonFromForgotPasswordForm();
        mainPage.fillDataUserForAuthorization(EMAIL, PASSWORD);
        assertTrue(mainPage.checkSuccessLogin());
    }

    @After
    @Step("Очистка тестовых данных: удаление пользователя")
    public void clearData() {
        if (userAuthToken != null) {
            deleteUser(userAuthToken);
        }
    }
}
