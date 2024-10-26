package ecommerce;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class shoppingTest {
	public static void main(String[] args) throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	Thread.sleep(Duration.ofSeconds(10));
	driver.get("https://www.saucedemo.com/");
	WebElement username = driver.findElement(By.id("user-name"));
	username.sendKeys("standard_user");
	
	WebElement password = driver.findElement(By.id("password"));
	password.sendKeys("secret_sauce");
	
	WebElement loginBtn = driver.findElement(By.name("login-button"));
	loginBtn.click();
	Thread.sleep(Duration.ofSeconds(10));

	String expectedUrl = "https://www.saucedemo.com/inventory.html"; 
	String actualUrl = driver.getCurrentUrl();
	if (expectedUrl.equals(actualUrl)) {
		System.out.println("Login Successful");
	} 
	else {
		System.out.println("Login unsuccessful");
	}
	
	WebElement addtoCartbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
	addtoCartbtn.click();
	Thread.sleep(Duration.ofSeconds(10));

	
	WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
	cartIcon.click();
	
	Thread.sleep(Duration.ofSeconds(10));

	WebElement cartView = driver.findElement(By.id("cart_contents_container"));
	if (cartView.isDisplayed())
	{
		System.out.println("Item Added to Cart Successfully");
	}
	else {
		{
			System.out.println("Item Not Added");
		}
	}
	Thread.sleep(Duration.ofSeconds(10));

	WebElement removebtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
	removebtn.click();
	
	Thread.sleep(Duration.ofSeconds(10));

	WebElement verifyRemove = driver.findElement(By.id("cart_contents_container"));
	if (!(verifyRemove.isDisplayed())) {
		System.out.println("Item Successfully Remove");
	}
	else 
	{
		System.out.println("Item not removed");
	}
	Thread.sleep(Duration.ofSeconds(10));

	WebElement continuebtn = driver.findElement(By.id("continue-shopping"));
	continuebtn.click();
	Thread.sleep(Duration.ofSeconds(10));

	WebElement addtoCbtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
	addtoCbtn.click();
	Thread.sleep(Duration.ofSeconds(10));

	WebElement cIcon = driver.findElement(By.className("shopping_cart_link"));
	cIcon.click();
	Thread.sleep(Duration.ofSeconds(10));

	WebElement checkoutbtn = driver.findElement(By.id("checkout"));
	checkoutbtn.click();
	Thread.sleep(Duration.ofSeconds(10));

	WebElement firstname = driver.findElement(By.id("first-name"));
	firstname.sendKeys("Nang");
	Thread.sleep(Duration.ofSeconds(10));

	WebElement lastname = driver.findElement(By.id("last-name"));
	lastname.sendKeys("Kham");
	Thread.sleep(Duration.ofSeconds(10));

	WebElement postcode = driver.findElement(By.id("postal-code"));
	postcode.sendKeys("1322");
	Thread.sleep(Duration.ofSeconds(10));

	WebElement continuebtn1 = driver.findElement(By.id("continue"));
	continuebtn1.click();
	Thread.sleep(Duration.ofSeconds(10));

	if(driver.findElement(By.id("checkout_summary_container")).isDisplayed()) {
		driver.findElement(By.id("finish")).click();
	}
	Thread.sleep(Duration.ofSeconds(10));

	if (driver.findElement(By.id("checkout_complete_container")).isDisplayed()) {
		System.out.println("Chechout Successful");
	}
	else 
	{
		System.out.println("Chechout unSuccessful");
	}
	
	
	
	
	}
	

}
