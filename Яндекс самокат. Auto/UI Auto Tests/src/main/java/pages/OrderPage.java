package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    WebDriver driver;

    // Кнопки "Заказать" для оформления заказа
    private final By makeOrderTopButton = By.className("Button_Button__ra12g");
    private final By makeOrderBottomButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By finishOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    private final By finishConfirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Да')]");

    // Поля заполнения данных пользователя
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By deliveryAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.className("select-search__input");
    private final By metroStationFieldHint = By.className("select-search__select");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By whenToDeliverField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By whenToDeliverFieldCalendarHint = By.cssSelector(".react-datepicker__day--today");
    private final By rentTimeField = By.className("Dropdown-placeholder");
    private final By rentTimeFieldHint = By.className("Dropdown-option"); // Берем просто первый в списке
    private final By scooterColor = By.className("Checkbox_Label__3wxSf"); // Берем просто первый в списке

    // Кнопка "Далее" после заполнения контактных данных пользователя
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Табло, показывающее, что заказ создан успешно
//    public By successCreatedOrderPage = By.className("Order_ModalHeader__3FDaJ");
    public By successCreatedOrderPage = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void topOrderButtonClick() {
        driver.findElement(makeOrderTopButton).click();
    }

    public void bottomOrderButtonClick() {
        driver.findElement(makeOrderBottomButton).click();
    }

    public void fillUserDataAndCreateOrder(String firstNameData, String lastNameData, String deliveryAddressFieldData,
                                           String metroStationFieldData, String phoneNumberFieldData) {
        // Ввод имени, фамилии и адреса доставки
        driver.findElement(firstNameField).sendKeys(firstNameData);
        driver.findElement(lastNameField).sendKeys(lastNameData);
        driver.findElement(deliveryAddressField).sendKeys(deliveryAddressFieldData);
        // Ввод станции метро, применяем кликом станцию из подсказки
        driver.findElement(metroStationField).sendKeys(metroStationFieldData);
        driver.findElement(metroStationFieldHint).click();
        // Ввод номера телефона
        driver.findElement(phoneNumberField).sendKeys(phoneNumberFieldData);
        // Нажимаем на кнопку "Далее"
        driver.findElement(nextButton).click();
        // Клик, чтобы выбрать дату доставки, выбираем число из календаря (по умолчанию на сегодня)
        driver.findElement(whenToDeliverField).click();
        driver.findElement(whenToDeliverFieldCalendarHint).click();
        // Клик, чтобы выбрать время аренды, выбираем из списка (по умолчанию первое в списке - "сутки")
        driver.findElement(rentTimeField).click();
        driver.findElement(rentTimeFieldHint).click();
        // Выбираем цвет самоката (по умолчанию - "черный")
        driver.findElement(scooterColor).click();
        // Нажимаем кнопку снизу "Заказать", чтобы завершить оформление и подтверждаем создание заказа
        driver.findElement(finishOrderButton).click();
        driver.findElement(finishConfirmOrderButton).click();
    }
}
