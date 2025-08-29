package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderConfirmModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
    }

    private final By modal = By.xpath("//div[contains(@class,'Order_Modal')]");
    private final By yes = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Да']");

    public OrderCreatedModal confirmYes(){
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(yes));
        btn.click();
        return new OrderCreatedModal(driver);
    }
}
