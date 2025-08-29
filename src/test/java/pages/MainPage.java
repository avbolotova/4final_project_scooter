package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // без Duration
    }

    // Кнопки «Заказать» на главной
    private final By orderTopButton    = By.xpath("//button[contains(@class,'Button_Button__')][text()='Заказать']");
    private final By orderBottomButton = By.xpath("//button[contains(@class,'Button_UltraBig__')][text()='Заказать']");

    // Кнопка куков «Да все привыкли» на главной
    private final By cookieButton = By.id("rcc-confirm-button");

    // Блок FAQ
    private final By faqSection = By.id("accordion");

    public void open() {
        driver.get(TestConfig.BASE_URL);
        closeCookiesIfPresent();
    }

    public void closeCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click()", btn);
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

    public void scrollFaqIntoView() {
        WebElement faq = wait.until(ExpectedConditions.visibilityOfElementLocated(faqSection));
        scrollIntoView(faq);
    }

    private void waitAndClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(el);
        el.click();
    }

    private void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
}
