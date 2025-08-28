package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FaqSection {

    private final WebDriver driver;

    // Вопрос: "Сколько это стоит? И как оплатить?"
    private final By question = By.id("accordion__heading-0");
    // Панель ответа для этого вопроса
    private final By answerPanel = By.id("accordion__panel-0");

    public FaqSection(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollIntoView() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'})",
                driver.findElement(question)
        );
    }

    /** Клик по вопросу */
    public void clickQuestion() {
        driver.findElement(question).click();
    }

    /** Текущее состояние раскрытия */
    public boolean isExpanded() {
        String expanded = driver.findElement(question).getAttribute("aria-expanded");
        return "true".equalsIgnoreCase(expanded);
    }

    /** Текст ответа из панели */
    public String getAnswerText() {
        return driver.findElement(answerPanel).getText();
    }

    /** Раскрыть (кликнуть, если свернут) */
    public void expand() {
        if (!isExpanded()) {
            clickQuestion();
        }
    }

    /** Свернуть (кликнуть, если раскрыт) */
    public void collapse() {
        if (isExpanded()) {
            clickQuestion();
        }
    }

}
