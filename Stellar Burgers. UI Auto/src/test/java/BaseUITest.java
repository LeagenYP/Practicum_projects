import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

public class BaseUITest {
    static WebDriver driver;
    MainPage mainPage;
    public String yandexDriverPath = "F:/Java/yandex-driver/yandexdriver.exe";

    // Выбор браузера
    @Before
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome"); // "chrome" - по умолчанию
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("yandex")) {
            startBrowserYandex();
        }
    }

    public static final String BASE_URI = "https://stellarburgers.education-services.ru/";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    // Chrome браузер
    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
    }

    // Yandex браузер
    public void startBrowserYandex() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", yandexDriverPath);
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
    }

    // Закрытие браузера
    @After
    public void closeBrowser() {
        if (driver != null) {
          driver.quit();
        }
    }
}