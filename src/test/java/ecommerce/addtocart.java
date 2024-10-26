package ecommerce;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class addtocart {
	WebDriver driver;
  @Test
  public void verifyLogin() {
	  System.out.println("Test 1");
	  String actualUrl = driver.getCurrentUrl();
	  if (actualUrl.equals("https://www.saucedemo.com/inventory.html")) {
		  System.out.println("login Successful");
	  }
  }
  
  @Test
  public void addItemtoCart() {
	  System.out.println("test 2");
	  
	  int initialCount = 0;

      // Check if the cart count element is present
      if (isElementPresent(By.className("shopping_cart_badge"))) {
          WebElement cartCountElement = driver.findElement(By.className("shopping_cart_badge"));
          initialCount = Integer.parseInt(cartCountElement.getText());
      }

      // Add an item to the cart
      WebElement addItemButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
      addItemButton.click();

      // Wait for the cart count to update (use implicit or explicit wait)
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

      // Verify that the cart count has been updated
      int updatedCount = 0;
      if (isElementPresent(By.className("shopping_cart_badge"))) {
          WebElement cartCountElement = driver.findElement(By.className("shopping_cart_badge"));
          updatedCount = Integer.parseInt(cartCountElement.getText());
      }

      // Assert that the cart count has increased by 1
      Assert.assertEquals(updatedCount, initialCount + 1, "Item was not added to the cart successfully.");
  }

  // Utility method to check if an element is present in the DOM
  private boolean isElementPresent(By locator) {
      try {
          driver.findElement(locator);
          return true;
      } catch (NoSuchElementException e) {
          return false;
      }
  }
  @BeforeMethod
  public void beforeMethod() { //login
	  System.out.println("This is a before method");
	 driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
	 driver.findElement(By.id("password")).sendKeys("secret_sauce");
	 driver.findElement(By.id("login-button")).click();

  }

  @AfterMethod
  public void afterMethod() {
	  driver.findElement(By.id("react-burger-menu-btn")).click();
	  WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].scrollIntoView(true);", logoutLink);
	  logoutLink.click();
	  System.out.println("logout successfully");
  }


//  @DataProvider
//  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
//    };
//  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is a before class");
	  driver.get("https://www.saucedemo.com");

  }

  @AfterClass
  public void afterClass() {
	  System.out.println("This is a after class");
	  driver.close();

  }

  @BeforeTest
  public void beforeTest() throws InterruptedException { //openchrome 
	  System.out.println("This is a before test");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

  }

  @AfterTest
  public void afterTest() {
	  System.out.println("This is a after test");
	  driver.quit();

  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("This is a before suite");

  }
  

  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is a after suite");
	  //driver.manage().deleteAllCookies();

  }

}
