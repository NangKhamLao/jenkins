package ecommerce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProDemo {
	   WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.saucedemo.com/");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	    }

	    @DataProvider(name = "loginData")
	    public Object[][] loginData() {
	        return new Object[][]{
	            {"standard_user", "secret_sauce", true},
	            {"locked_out_user", "secret_sauce", false},
	            {"problem_user", "secret_sauce", true},
	            {"performance_glitch_user", "secret_sauce", true},
	            {"invalid_user", "wrong_password", false}
	        };
	    }

	    @DataProvider(name = "cartData")
	    public Object[][] cartData() {
	        return new Object[][]{
	            {new String[]{"add-to-cart-sauce-labs-backpack", "add-to-cart-sauce-labs-bike-light"}},
	            {new String[]{"add-to-cart-sauce-labs-bolt-t-shirt", "add-to-cart-sauce-labs-fleece-jacket", "add-to-cart-sauce-labs-onesie"}},
	            {new String[]{"add-to-cart-sauce-labs-backpack", "add-to-cart-sauce-labs-fleece-jacket"}}
	        };
	    }

	    @DataProvider(name = "checkoutData")
	    public Object[][] checkoutData() {
	        return new Object[][]{
	            {"John", "Doe", "12345"},
	            {"Jane", "Smith", "54321"},
	            {"Alice", "Johnson", "67890"}
	        };
	    }

	    @Test(dataProvider = "loginData")
	    public void loginTest(String username, String password, boolean expectedSuccess) {
	        driver.findElement(By.id("user-name")).clear();
	        driver.findElement(By.id("password")).clear();
	        driver.findElement(By.id("user-name")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("login-button")).click();

	        if (expectedSuccess) {
	            Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed(), "Login failed for user: " + username);
	        } else {
	            Assert.assertTrue(driver.findElement(By.cssSelector(".error-message-container")).isDisplayed(), "Error message not displayed for user: " + username);
	        }
	    }

	    @Test(dataProvider = "cartData", dependsOnMethods = "loginTest")
	    public void addItemsToCartTest(String[] itemIds) {
	        for (String itemId : itemIds) {
	            driver.findElement(By.id(itemId)).click();
	        }

	        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
	        Assert.assertEquals(cartBadge.getText(), String.valueOf(itemIds.length), "Incorrect number of items in the cart.");
	    }

	    @Test(dependsOnMethods = "addItemsToCartTest")
	    public void viewCartTest() {
	        driver.findElement(By.className("shopping_cart_link")).click();
	        WebElement cartTitle = driver.findElement(By.className("title"));
	        Assert.assertEquals(cartTitle.getText(), "YOUR CART", "Not on the cart page.");
	    }

	    @Test(dataProvider = "checkoutData", dependsOnMethods = "viewCartTest")
	    public void checkoutTest(String firstName, String lastName, String postalCode) {
	        driver.findElement(By.id("checkout")).click();

	        driver.findElement(By.id("first-name")).sendKeys(firstName);
	        driver.findElement(By.id("last-name")).sendKeys(lastName);
	        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
	        driver.findElement(By.id("continue")).click();

	        WebElement overviewTitle = driver.findElement(By.className("title"));
	        Assert.assertEquals(overviewTitle.getText(), "CHECKOUT: OVERVIEW", "Not on the checkout overview page.");

	        driver.findElement(By.id("finish")).click();

	        WebElement completeTitle = driver.findElement(By.className("title"));
	        Assert.assertEquals(completeTitle.getText(), "CHECKOUT: COMPLETE!", "Checkout was not completed.");
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	

}
