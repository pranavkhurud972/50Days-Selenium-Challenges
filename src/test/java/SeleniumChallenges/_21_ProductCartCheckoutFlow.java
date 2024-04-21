package SeleniumChallenges;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _21_ProductCartCheckoutFlow {
	/*
	 * ✅𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨:
	 * 
	 * 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭
	 * 𝐭𝐡𝐚𝐭 𝐟𝐞𝐭𝐜𝐡 𝐚𝐧𝐝 𝐯𝐞𝐫𝐢𝐟𝐲 - 𝐭𝐡𝐞 product cart checkout flow.
	 * 
	 * 𝐒𝐭𝐞𝐩𝐬: 1)Navigate to: https://magento.softwaretestingboard.com/ 2)Create
	 * a Customer with valid info. 3)Sign in with the customer credentials you
	 * created. 4)Take Any product and add it to the cart. 5)Verify that the product
	 * name, product type, product description(if it exists) & size are the same as
	 * you selected. 6) Proceed to checkout and Verify its billing details & its
	 * price calculated at billing.
	 * 
	 * 𝐓𝐡𝐢𝐬 𝐢𝐬 𝐭𝐡𝐞 𝐬𝐢𝐦𝐩𝐥𝐞 𝐄-𝐂𝐨𝐦𝐦𝐞𝐫𝐜𝐞 𝐰𝐞𝐛𝐬𝐢𝐭𝐞
	 * 𝐟𝐥𝐨𝐰.😉
	 * 
	 */
	public static WebDriver driver;
	public static WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://magento.softwaretestingboard.com/");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void AddToCartTest() throws InterruptedException {
//		
//		driver.findElement(By.xpath("(//a[text()='Create an Account'])[1]")).click();
//
//		Thread.sleep(2000);
//		driver.findElement(By.id("firstname")).sendKeys("Tom"); // First Name
//		driver.findElement(By.id("lastname")).sendKeys("Peter"); // Last Name
//		driver.findElement(By.id("email_address")).sendKeys("tom.peter@yopmail.com"); // Email
//		driver.findElement(By.id("password")).sendKeys("Tom@123"); // Password
//		driver.findElement(By.id("password-confirmation")).sendKeys("Tom@123"); // confirm password
//		driver.findElement(By.cssSelector(".action.submit.primary")).click(); // Create acc button
//		
//		Thread.sleep(2000);

		//wait.until(ExpectedConditions.visibilityOfAllElements(prods));
		Map<String,String> prodDetails = new HashMap<>();
		WebElement prodName = driver.findElement(By.cssSelector("a[title='Argus All-Weather Tank']"));
		prodName.click();
		String price = driver.findElement(By.id("product-price-694")).getText();
		driver.findElement(By.id("option-label-size-143-item-168")).click();
		String selectedSize = driver.findElement(By.className("swatch-attribute-selected-option")).getAttribute("Size");
		driver.findElement(By.id("option-label-color-93-item-52")).click();
		String selectedColor = driver.findElement(By.className("swatch-attribute-selected-option")).getAttribute("Color");
		prodDetails.put("price", price);
		prodDetails.put("Size", selectedSize);
		prodDetails.put("Color", selectedColor);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("product-addtocart-button")));
		driver.findElement(By.id("product-addtocart-button")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='action showcart']")).click();
		driver.findElement(By.xpath("//a[@class='action viewcart']")).click();
		Thread.sleep(5000);
		Map<String,String> cartProdDetails = new HashMap<>();
		WebElement cartColor = driver.findElement(By.xpath("//dd[contains(text(),'Gray')]"));
		WebElement cartSize= driver.findElement(By.xpath("//dd[contains(text(),'M')]"));
		WebElement cartPrice = driver.findElement(By.xpath("(//td/span[@class='price'])[1]"));
		cartPrice.getText();
		
		boolean areDetailsMatching = prodDetails.equals(cartProdDetails);
		if(areDetailsMatching) {
			System.out.println("Product details on product description page and cart page match.");
        } else {
            System.out.println("Product details on product description page and cart page do not match.");
        };
		}

}
