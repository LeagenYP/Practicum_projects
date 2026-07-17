import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsListTest extends BaseUITest {
    public final int index;

    public QuestionsListTest(int index, String expectedAnswers) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] expectedAnswers() {
        return new Object[][] {
                {0, MainPage.COST_ANSWER_TEXT},
                {1, MainPage.SEVERAL_SCOOTERS_TEXT},
                {2, MainPage.TIME_RENTAL_TEXT},
                {3, MainPage.TODAY_ORDER_TEXT},
                {4, MainPage.CHANGE_ORDER_TEXT},
                {5, MainPage.CHARGING_SCOOTER_TEXT},
                {6, MainPage.CANCEL_ORDER_TEXT},
                {7, MainPage.AREA_DELIVERY_TEXT}
        };
    }

    @Test
    public void questionMatchAnswerTest() {
        // открываем страницу
        mainPage.openPage();
        // принимаем куки
        mainPage.acceptCookie();
        // скроллим до блока с вопросами (для наглядности)
        mainPage.scrollToQuestionsBlock();
        // нажимаем на вопрос
        mainPage.clickOnQuestion(index);
        // получаем фактический текст ответа на вопрос
        String actualAnswer = mainPage.getAnswerText(index);
        // получаем ожидаемый текст вопроса
        String expectedAnswerForCompare = (String) expectedAnswers()[index][1];
        // сравниваем результаты
        assertEquals("Текст не соответствует ожидаемому", expectedAnswerForCompare, actualAnswer);
    }
}
