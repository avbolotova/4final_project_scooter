package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStepTwoPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderStepTwoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By periodControl = By.xpath("//div[contains(@class,'Dropdown-control')]");
    private By periodOption(String t){
        return By.xpath("//div[contains(@class,'Dropdown-menu')]//div[contains(@class,'Dropdown-option') and text()='"+t+"']");
    }
    private final By selectedPeriod = By.xpath("//div[contains(@class,'Dropdown-placeholder') and contains(@class,'is-selected')]");
    private final By black = By.id("black");
    private final By grey = By.id("grey");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderBtn = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");

    public OrderStepTwoPage setDate(String d){
        WebElement f = wait.until(ExpectedConditions.elementToBeClickable(date));
        f.sendKeys(Keys.CONTROL+"a"); f.sendKeys(Keys.DELETE); f.sendKeys(d); f.sendKeys(Keys.ENTER);
        return this;
    }
    public OrderStepTwoPage selectPeriod(String text){
        click(periodControl); click(periodOption(text));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(selectedPeriod, text));
        return this;
    }
    public OrderStepTwoPage setColors(String colors){
        if (colors==null) return this;
        String lc = colors.toLowerCase();
        if (lc.contains("чёрный")) toggle(black);
        if (lc.contains("серая"))  toggle(grey);
        return this;
    }
    public OrderStepTwoPage setComment(String v){ type(comment, v); return this; }

    public OrderConfirmModal clickOrder(){
        click(orderBtn);
        return new OrderConfirmModal(driver);
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
    private void toggle(By loc){
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(loc));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cb);
        if(!cb.isSelected()) cb.click();
    }
}
