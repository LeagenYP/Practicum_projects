import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MakeOrderTest extends BaseUITest {
    public final String firstName;
    public final String lastName;
    public final String deliveryAddressField;
    public final String metroStationField;
    public final String phoneNumberField;

    public MakeOrderTest(String firstName, String lastName, String deliveryAddressField, String metroStationField, String phoneNumberField) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.deliveryAddressField = deliveryAddressField;
        this.metroStationField = metroStationField;
        this.phoneNumberField = phoneNumberField;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2} {3} {4}")
    public static Object[][] expectedAnswers() {
        return new Object[][] {
                {"Юрий", "Пономарев", "Московская 83", "Черкизовская ", "79835342909"},
                {"Никита", "Сергеев", "Климова 3", "Сокольники ", "79090430505"},
        };
    }

    // Тест заказа через верхнюю кнопку "Заказать"
    @Test
    public void makeOrderFromTopButton() {
        // открываем страницу
        mainPage.openPage();
        // принимаем куки
        mainPage.acceptCookie();
        // нажать верхнюю кнопку "Заказать"
        orderPage.topOrderButtonClick();
        // Заполнить данные и оформить заказ
        orderPage.fillUserDataAndCreateOrder(firstName, lastName, deliveryAddressField, metroStationField, phoneNumberField);

        assertTrue("Элемент не отображается на экране", driver.findElement(orderPage.successCreatedOrderPage).isDisplayed());
    }

    // Тест заказа через нижнюю кнопку "Заказать"
    @Test
    public void makeOrderFromBottomButton() {
        // открываем страницу
        mainPage.openPage();
        // принимаем куки
        mainPage.acceptCookie();
        // нажать нижнюю кнопку "Заказать"
        orderPage.bottomOrderButtonClick();
        // Заполнить данные и оформить заказ
        orderPage.fillUserDataAndCreateOrder(firstName, lastName, deliveryAddressField, metroStationField, phoneNumberField);
        // Проверка видимости сообщения, что заказ оформлен
        assertTrue("Заказ не оформлен", driver.findElement(orderPage.successCreatedOrderPage).isDisplayed());
    }
}
