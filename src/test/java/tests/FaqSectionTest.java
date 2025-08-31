package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqSectionTest extends BaseTest {

    @Parameterized.Parameter(0)
    public By question;

    @Parameterized.Parameter(1)
    public By answer;

    @Parameterized.Parameters(name = "FAQ {index}")
    public static Object[][] data() {
        return new Object[][]{
                {MainPage.faqCostAndPaymentQuestion,   MainPage.faqCostAndPaymentAnswer},
                {MainPage.faqMultipleScootersQuestion, MainPage.faqMultipleScootersAnswer},
                {MainPage.faqRentTimeQuestion,         MainPage.faqRentTimeAnswer},
                {MainPage.faqSameDayOrderQuestion,     MainPage.faqSameDayOrderAnswer},
                {MainPage.faqExtendOrReturnQuestion,   MainPage.faqExtendOrReturnAnswer},
                {MainPage.faqChargerIncludedQuestion,  MainPage.faqChargerIncludedAnswer},
                {MainPage.faqCancelOrderQuestion,      MainPage.faqCancelOrderAnswer},
                {MainPage.faqOutsideMKADQuestion,      MainPage.faqOutsideMKADAnswer}
        };
    }

    @Test
    public void faqQuestion_ShouldShowNonEmptyAnswer() {
        MainPage main = new MainPage(driver);
        main.open();

        main.scrollFaqQuestionIntoView(question);
        main.expandFaq(question);

        String text = main.getFaqAnswerText(answer);
        assertTrue("Ожидали непустой текст ответа", text != null && !text.trim().isEmpty());
    }
}
