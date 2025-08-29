package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import pages.OrderConfirmModal;
import pages.OrderCreatedModal;
import pages.OrderStepOnePage;
import pages.OrderStepTwoPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest {

    private WebDriver driver;

    // Параметры данных заказа
    @Parameterized.Parameter(0) public String entryPoint;
    @Parameterized.Parameter(1) public String firstName;
    @Parameterized.Parameter(2) public String lastName;
    @Parameterized.Parameter(3) public String address;
    @Parameterized.Parameter(4) public String metro;
    @Parameterized.Parameter(5) public String phone;
    @Parameterized.Parameter(6) public String date;
    @Parameterized.Parameter(7) public String rentalPeriod;
    @Parameterized.Parameter(8) public String color;
    @Parameterized.Parameter(9) public String comment;

    @Parameterized.Parameters(name = "[{index}] {0} | {1} {2} | {7} | {8}")
    public static Object[][] data() {
        return new Object[][]{
                {"top",    "Иван",  "Иванов",  "ул. Ленина, 10-5", "Черкизовская", "89675070407", "28.08.2025", "двое суток",   "чёрный жемчуг",                        "позвоните за 10 минут"},
                {"bottom", "Анна",  "Петрова", "пр-т Победы, д. 1","Черкизовская", "89675070407", "29.08.2025", "пятеро суток", "чёрный жемчуг, серая безысходность", "домофон #123"}
        };
    }

    /** Позитивный сценарий заказа самоката с параметрами */
    @Test
    public void orderPositiveFlow() {
        MainPage main = new MainPage(driver);
        main.open();
        main.closeCookiesIfPresent();

        if ("top".equalsIgnoreCase(entryPoint)) {
            main.clickOrderTop();
        } else {
            main.clickOrderBottom();
        }

        OrderStepOnePage step1 = new OrderStepOnePage(driver)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setMetro(metro)
                .setPhone(phone);

        OrderStepTwoPage step2 = step1.clickNext();

        OrderConfirmModal confirm = step2
                .setDate(date)
                .selectPeriod(rentalPeriod)
                .setColors(color)
                .setComment(comment)
                .clickOrder();

        OrderCreatedModal done = confirm.confirmYes();
        assertTrue("Ожидали модалку «Заказ оформлен»", done.isVisible());
    }
}
