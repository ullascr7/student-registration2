
package com.student;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRegistrationTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        String filePath = Path.of(
            "src/main/resources/index.html"
        ).toAbsolutePath().toUri().toString();

        driver.get(filePath);
    }

    @Test
    public void testSuccessfulRegistration() {

        driver.findElement(By.id("name"))
              .sendKeys("Ullas");

        driver.findElement(By.id("email"))
              .sendKeys("ullas@gmail.com");

        driver.findElement(By.id("usn"))
              .sendKeys("4VV25CI418");

        Select department = new Select(
            driver.findElement(By.id("department"))
        );

        department.selectByValue("AIML");

        driver.findElement(
            By.cssSelector("button[type='submit']")
        ).click();

        String message = driver.findElement(
            By.id("message")
        ).getText();

        assertEquals(
            "Registration Successful!",
            message
        );
    }

    @Test
    public void testEmptyFieldValidation() {

        driver.findElement(
            By.cssSelector("button[type='submit']")
        ).click();

        WebElement nameField =
            driver.findElement(By.id("name"));

        String validationMessage =
            nameField.getAttribute("validationMessage");

        assertNotNull(validationMessage);

        assertFalse(validationMessage.isEmpty());
    }

    @AfterEach
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
