import io.qameta.allure.Description;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestConstructor extends BaseUITest {

    @Test
    @Description("Активация вкладки Булки в конструкторе")
    public void testBunSectionActive()
    {
        mainPage.openPage();
        if(!mainPage.isTabActive(mainPage.constructorBun))
        {
            mainPage.openBunTabInConstructor();
        }
        assertTrue(mainPage.isTabActive(mainPage.constructorBun));
    }

    @Test
    @Description("Активация вкладки Соусы в конструкторе")
    public void testSauceSectionActive()
    {
        mainPage.openPage();
        if(!mainPage.isTabActive(mainPage.constructorSauce))
        {
            mainPage.openSauceTabInConstructor();
        }
        assertTrue(mainPage.isTabActive(mainPage.constructorSauce));
    }

    @Test
    @Description("Активация вкладки Начинки в конструкторе")
    public void testFillingSectionActive()
    {
        mainPage.openPage();
        if(!mainPage.isTabActive(mainPage.constructorFilling))
        {
            mainPage.openFillingTabInConstructor();
        }
        assertTrue(mainPage.isTabActive(mainPage.constructorFilling));
    }
}
