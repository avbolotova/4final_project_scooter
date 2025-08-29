package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStepOnePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath("//div[contains(@class,'select-search')]//input");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextBtn = By.xpath("//button[text()='Далее']");

    public OrderStepOnePage setFirstName(String v){ type(firstName, v); return this; }
    public OrderStepOnePage setLastName(String v){ type(lastName, v); return this; }
    public OrderStepOnePage setAddress(String v){ type(address, v); return this; }
    public OrderStepOnePage setMetro(String v){
        WebElement m = wait.until(ExpectedConditions.visibilityOfElementLocated(metro));
        m.clear(); m.sendKeys(v); m.sendKeys(Keys.ARROW_DOWN); m.sendKeys(Keys.ENTER);
        return this;
    }
    public OrderStepOnePage setPhone(String v){ type(phone, v); return this; }

    public OrderStepTwoPage clickNext(){
        click(nextBtn);
        return new OrderStepTwoPage(driver);
    }

    private void type(By loc, String text){
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
        el.clear(); if(text!=null && !text.isEmpty()) el.sendKeys(text);
    }
    private void click(By loc){
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(loc));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.click();
    }
}
