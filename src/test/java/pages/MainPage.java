package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Кнопки «Заказать» на главной
    private final By orderTopButton    = By.xpath("//button[contains(@class,'Button_Button__')][text()='Заказать']");
    private final By orderBottomButton = By.xpath("//button[contains(@class,'Button_UltraBig__')][text()='Заказать']");

    // Кнопка куков «Да все привыкли» на главной
    private final By cookieButton = By.id("rcc-confirm-button");

    // Вопросы на главной
    public static final By faqCostAndPaymentQuestion   = By.id("accordion__heading-0");
    public static final By faqCostAndPaymentAnswer     = By.id("accordion__panel-0");

    public static final By faqMultipleScootersQuestion = By.id("accordion__heading-1");
    public static final By faqMultipleScootersAnswer   = By.id("accordion__panel-1");

    public static final By faqRentTimeQuestion         = By.id("accordion__heading-2");
    public static final By faqRentTimeAnswer           = By.id("accordion__panel-2");

    public static final By faqSameDayOrderQuestion     = By.id("accordion__heading-3");
    public static final By faqSameDayOrderAnswer       = By.id("accordion__panel-3");

    public static final By faqExtendOrReturnQuestion   = By.id("accordion__heading-4");
    public static final By faqExtendOrReturnAnswer     = By.id("accordion__panel-4");

    public static final By faqChargerIncludedQuestion  = By.id("accordion__heading-5");
    public static final By faqChargerIncludedAnswer    = By.id("accordion__panel-5");

    public static final By faqCancelOrderQuestion      = By.id("accordion__heading-6");
    public static final By faqCancelOrderAnswer        = By.id("accordion__panel-6");

    public static final By faqOutsideMKADQuestion      = By.id("accordion__heading-7");
    public static final By faqOutsideMKADAnswer        = By.id("accordion__panel-7");

    public void open() {
        driver.get(TestConfig.BASE_URL);
        closeCookiesIfPresent();
    }

    public void closeCookiesIfPresent() {
        try {
            WebElement btn = new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.elementToBeClickable(cookieButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
        } catch (TimeoutException ignore) {
        }
    }

    public void clickOrderTop() {
        waitAndClick(orderTopButton);
    }

    public void clickOrderBottom() {
        WebElement bottom = wait.until(ExpectedConditions.visibilityOfElementLocated(orderBottomButton));
        scrollIntoView(bottom);
        bottom.click();
    }

    private void waitAndClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(el);
        el.click();
    }

    private void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    public void scrollFaqQuestionIntoView(By faqQuestion) {
        WebElement q = wait.until(ExpectedConditions.presenceOfElementLocated(faqQuestion));
        scrollIntoView(q);
    }

    /** Клик по вопросу */
    public void clickFaqQuestion(By faqQuestion) {
        WebElement q = wait.until(ExpectedConditions.elementToBeClickable(faqQuestion));
        scrollIntoView(q);
        q.click();
    }

    /** Текущее состояние раскрытия */
    public boolean isFaqQuestionExpanded(By faqQuestion) {
        WebElement q = wait.until(ExpectedConditions.presenceOfElementLocated(faqQuestion));
        String expanded = q.getAttribute("aria-expanded");
        return "true".equalsIgnoreCase(expanded);
    }

    /** Текст ответа из панели */
    public String getFaqAnswerText(By faqAnswerPanel) {
        WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(faqAnswerPanel));
        return panel.getText().trim();
    }

    /** Раскрыть (кликнуть, если свернут) */
    public void expandFaq(By faqQuestion) {
        if (!isFaqQuestionExpanded(faqQuestion)) {
            clickFaqQuestion(faqQuestion);
        }
    }
}
