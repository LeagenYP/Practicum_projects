package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор первого вопроса для скролла
    public By costQuestionButton = By.id("accordion__heading-0");

    // Текст ответов на вопросы в разделе "Вопросы о важном" (пока не используется)
    public final static String COST_ANSWER_TEXT = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public final static String SEVERAL_SCOOTERS_TEXT = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public final static String TIME_RENTAL_TEXT = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public final static String TODAY_ORDER_TEXT = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public final static String CHANGE_ORDER_TEXT = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public final static String CHARGING_SCOOTER_TEXT = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public final static String CANCEL_ORDER_TEXT = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public final static String AREA_DELIVERY_TEXT = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    // Кнопка принятия куки
    private final By cookieAcceptButton = By.className("App_CookieButton__3cvqF");

    // Получить список вопросов
    private final By questionsList = By.className("accordion__button");
    private final By answersList = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void acceptCookie() {
        WebElement cookieButton = driver.findElement(cookieAcceptButton);
        if (cookieButton.isDisplayed()) {
            cookieButton.click();
        }
    }

    public void clickOnQuestion(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(questionsList));
        List<WebElement> questions = driver.findElements(questionsList);
        WebElement question = questions.get(index);
        question.click();
    }

    public String getAnswerText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(questionsList));
        List<WebElement> questions = driver.findElements(answersList);
        WebElement question = questions.get(index);
        return question.getText();
    }

    public void scrollToQuestionsBlock() {
        WebElement element = driver.findElement(costQuestionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'nearest'});", element);
    }
}