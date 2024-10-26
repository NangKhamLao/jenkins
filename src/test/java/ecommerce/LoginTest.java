package ecommerce;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set up the ChromeDriver
      //.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();

        // Navigate to the Saucedemo website
        driver.get("https://www.saucedemo.com/");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, boolean expectedSuccess) {
        // Locate the username field and enter the username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);

        // Locate the password field and enter the password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Locate the login button and click it
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        if (expectedSuccess) {
            // Assert successful login by checking the presence of the products title
            WebElement productsTitle = driver.findElement(By.className("title"));
            AssertJUnit.assertEquals(productsTitle.getText(), "Products");
        } else {
            // Assert failure by checking the presence of the error message
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
            AssertJUnit.assertTrue(errorMessage.isDisplayed());
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
            {"standard_user", "secret_sauce", true},  // Valid username and password
            {"invalid_user", "secret_sauce", false},  // Invalid username and valid password
            {"standard_user", "wrong_password", false},  // Valid username and invalid password
            {"invalid_user", "wrong_password", false},  // Invalid username and invalid password
        };
    }

    @AfterMethod
    public void teardown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }


}
