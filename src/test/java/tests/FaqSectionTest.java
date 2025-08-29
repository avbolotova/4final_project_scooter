package tests;

import pages.TestConfig;
import pages.MainPage;
import pages.FaqSection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FaqSectionTest extends BaseTest {

    @Parameterized.Parameters(name = "FAQ index={0}")
    public static Object[][] data() {
        return new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        };
    }

    @Parameterized.Parameter
    public int index;

    @Test
    public void questionShouldExpandAndShowAnswer() {
        driver.get(TestConfig.BASE_URL);
        MainPage main = new MainPage(driver);
        main.closeCookiesIfPresent();

        FaqSection faq = new FaqSection(driver);

        faq.scrollIntoView(index);
        faq.expand(index);

        assertTrue("После клика вопрос должен быть раскрыт", faq.isExpanded(index));
        assertTrue("Ответ должен быть видимым", faq.isAnswerVisible(index));
        String answer = faq.getAnswerText(index);
        assertNotNull("Ответ не должен быть null", answer);
        assertFalse("Ответ не должен быть пустым", answer.trim().isEmpty());
    }
}
