import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardOrderTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void shouldSendFormSuccessfully() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); // без графического интерфейса

        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");

        // Заполняем поля
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петр-Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012345678");

        // Ставим галочку согласия
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();

        // Нажимаем кнопку "Продолжить"
        driver.findElement(By.cssSelector("button.button")).click();

        // Проверяем появление сообщения об успешной отправке
        WebElement successMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(successMessage.isDisplayed());

        driver.quit(); // закрыть браузер
    }
}