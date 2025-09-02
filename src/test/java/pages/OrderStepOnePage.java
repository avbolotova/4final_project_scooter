package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStepOnePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public OrderStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextBtn = By.xpath("//button[contains(@class,'Button_Middle__') and text()='Далее']");

    /** Дождаться видимости элемента по локатору. */
    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Дождаться кликабельности элемента по локатору. */
    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'})", el);
    }

    /** Ввести текст в поле: видимость */
    protected void type(By locator, String value) {
        WebElement el = waitVisible(locator);
        scrollIntoView(el);
        el.clear();
        el.sendKeys(value);
    }

    /** Клик по локатору */
    protected void click(By locator) {
        WebElement el = waitClickable(locator);
        scrollIntoView(el);
        el.click();
    }

    public OrderStepOnePage setFirstName(String v) {
        type(firstName, v);
        return this;
    }

    public OrderStepOnePage setLastName(String v) {
        type(lastName, v);
        return this;
    }
    public OrderStepOnePage setAddress(String v) {
        type(address, v);
        return this;
    }

    public OrderStepOnePage setMetro(String v) {
        WebElement m = waitVisible(metro);
        scrollIntoView(m);
        m.clear();
        m.sendKeys(v);
        m.sendKeys(Keys.ARROW_DOWN);
        m.sendKeys(Keys.ENTER);
        return this;
    }

    public OrderStepOnePage setPhone(String v) {
        type(phone, v);
        return this;
    }

    /** Перейти на шаг 2 */
    public OrderStepTwoPage clickNext() {
        click(nextBtn);
        return new OrderStepTwoPage(driver);
    }
}
