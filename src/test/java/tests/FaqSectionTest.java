package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FaqSection;

import static org.junit.Assert.*;

public class FaqSectionTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ff = new FirefoxOptions();
                if (headless) ff.setHeadless(true);
                driver = new FirefoxDriver(ff);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions ch = new ChromeOptions();
                if (headless) ch.addArguments("--headless");
                ch.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(ch);
                break;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void questionShouldExpandAndShowAnswer() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        FaqSection faq = new FaqSection(driver);
        faq.scrollIntoView();
        faq.collapse();
        assertFalse("Ожидали, что вопрос свернут", faq.isExpanded());
        faq.expand();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-0")));

        assertTrue("После клика вопрос должен быть раскрыт", faq.isExpanded());
        String answer = faq.getAnswerText();
        assertNotNull("Ответ не должен быть null", answer);
        assertFalse("Ответ не должен быть пустым", answer.trim().isEmpty());
    }
}
