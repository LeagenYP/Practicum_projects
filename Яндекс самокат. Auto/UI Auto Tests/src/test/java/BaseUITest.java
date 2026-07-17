import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

public class BaseUITest {
    static WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    // Выбор браузера
    @Before
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome"); // "chrome" - по умолчанию
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
        }
    }

    // Chrome браузер
    public void startBrowserChrome() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    // Firefox браузер
    public void startBrowserFirefox() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    // Закрытие браузера
    @After
    public void closeBrowser() {
       driver.quit();
    }
}
