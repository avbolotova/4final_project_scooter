package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaqSection {

    private final WebDriver driver;
    private final WebDriverWait wait; // Selenium 3: timeout в секундах

    // Вопросы
    public static final By Q1 = By.id("accordion__heading-0");
    public static final By Q2 = By.id("accordion__heading-1");
    public static final By Q3 = By.id("accordion__heading-2");
    public static final By Q4 = By.id("accordion__heading-3");
    public static final By Q5 = By.id("accordion__heading-4");
    public static final By Q6 = By.id("accordion__heading-5");
    public static final By Q7 = By.id("accordion__heading-6");
    public static final By Q8 = By.id("accordion__heading-7");

    // Панели ответов
    public static final By A1 = By.id("accordion__panel-0");
    public static final By A2 = By.id("accordion__panel-1");
    public static final By A3 = By.id("accordion__panel-2");
    public static final By A4 = By.id("accordion__panel-3");
    public static final By A5 = By.id("accordion__panel-4");
    public static final By A6 = By.id("accordion__panel-5");
    public static final By A7 = By.id("accordion__panel-6");
    public static final By A8 = By.id("accordion__panel-7");

    // Массивы для доступа по индексу
    private static final By[] QUESTIONS = { Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8 };
    private static final By[] ANSWERS   = { A1, A2, A3, A4, A5, A6, A7, A8 };

    public FaqSection(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private By q(int index) { return QUESTIONS[index]; }
    private By a(int index) { return ANSWERS[index]; }

    public void scrollIntoView(int index) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(q(index)));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'})", el);
    }

    /** Клик по вопросу */
    public void clickQuestion(int index) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(q(index)));
        el.click();
    }

    /** Текущее состояние раскрытия */
    public boolean isExpanded(int index) {
        String expanded = driver.findElement(q(index)).getAttribute("aria-expanded");
        return "true".equalsIgnoreCase(expanded);
    }

    /** Текст ответа из панели */
    public String getAnswerText(int index) {
        WebElement panel = wait.until(ExpectedConditions.presenceOfElementLocated(a(index)));
        return panel.getText().trim();
    }

    /** Ответ виден на странице */
    public boolean isAnswerVisible(int index) {
        WebElement panel = wait.until(ExpectedConditions.presenceOfElementLocated(a(index)));
        return panel.isDisplayed();
    }

    /** Раскрыть (кликнуть, если свернут) */
    public void expand(int index) {
        if (!isExpanded(index)) clickQuestion(index);
    }

    /** Свернуть (кликнуть, если раскрыт) */
    public void collapse(int index) {
        if (isExpanded(index)) clickQuestion(index);
    }
}
