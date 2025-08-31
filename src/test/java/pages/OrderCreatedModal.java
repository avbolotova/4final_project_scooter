package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderCreatedModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderCreatedModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By header = By.xpath("//div[contains(@class,'Order_ModalHeader')][contains(.,'Заказ оформлен')]");
    private final By seeStatus = By.xpath("//button[text()='Посмотреть статус']");

    public boolean isVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).isDisplayed();
    }
}
