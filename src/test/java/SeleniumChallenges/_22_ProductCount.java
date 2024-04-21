package SeleniumChallenges;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/*
 * ✅𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨:

𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐟𝐞𝐭𝐜𝐡 𝐚𝐧𝐝 𝐯𝐞𝐫𝐢𝐟𝐲 - 𝐭𝐡𝐞 𝐩𝐫𝐨𝐝𝐮𝐜𝐭 -𝐩𝐫𝐢𝐜𝐞 𝐬𝐨𝐫𝐭𝐢𝐧𝐠 𝐟𝐫𝐨𝐦 𝐥𝐨𝐰 𝐭𝐨 𝐡𝐢𝐠𝐡.

𝐒𝐭𝐞𝐩𝐬:
1)Navigate to: https://ecommerce-playground.lambdatest.io/
2)Click on "Mega Menu" on the menu bar then click on "Apple" submenu
3)Print the number of Products listed on the page.
4)Fetch the Product name and price of the products on the page.
5)Sort the Product with its price from low to high and print it.

 */
public class _22_ProductCount {
	public static WebDriver driver;
	public String productList, price = null;
	ArrayList<Map<String, String>> productListSort = new ArrayList<>();

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	 @Test
	    public void productPriceSortingTest() {
	    	driver.get("https://ecommerce-playground.lambdatest.io/");

	        // Click on "Mega Menu" then "Apple" submenu
	        WebElement targetElement = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Mega Menu']")));
			WebElement destinationElement = driver.findElement(By.xpath("//a[normalize-space()='Apple']"));
			
			new Actions(driver).moveToElement(targetElement).pause(Duration.ofSeconds(4)).click(destinationElement).perform();
			
	        // Print the number of Products listed on the page
	        List<WebElement> productList = driver.findElements(By.xpath("//h4[@class='title']/a[contains(@class,'text-ellipsis')]"));
	        System.out.println("Number of Products listed: " + productList.size());

	        // Fetch the Product name and price
	        List<String> productDetails = new ArrayList<>();
	        for (WebElement product : productList) {
	        	//Get the product name:
	            String name = product.getText();
	            //Get the product name with their price:
	            String price = product.findElement(By.xpath("(//h4[@class='title']/a[text()='" + name + "'])/following::span[@class='price-new']")).getText();
	            price = price.replace(",", "");
	            productDetails.add(name + " - " + price);

	        }
	 
	        // Print Product name and price
	        System.out.println("Product Details:");
	        for (String details : productDetails) {
	            System.out.println(details);
	        }

	        // Sort the Product with its price from low to high and print it
	        Collections.sort(productDetails, (a, b) -> {
	            String[] priceA = a.split(" - ")[1].split(" ")[0].split("\\$");
	            String[] priceB = b.split(" - ")[1].split(" ")[0].split("\\$");
	            double price1 = priceA.length>1 ? Double.parseDouble(priceA[1]):0;
	            double price2 = priceB.length>1 ? Double.parseDouble(priceB[1]):0;
	            return Double.compare(price1, price2);
	        });
	        
	        System.out.println("######################################################");
	        System.out.println("Product Details after sorting by price(Low to high):");
	        for (String details : productDetails) {
	            System.out.println(details);
	        }
	    }
	 
//Another way:
//	@Test
//	public void VerifyProductListingTest() {
//		moveToElement("//a[normalize-space()='Mega Menu']", "//a[normalize-space()='Apple']");
//		waitForSpecificElementVisible("//a[normalize-space()='iPod Touch']");
//		getAllProductNames();
//		sortProductListByPrice();
//
//	}
//
//	public void moveToElement(String target, String destination) {
//		WebElement targetElement = new WebDriverWait(driver, Duration.ofSeconds(10))
//				.until(ExpectedConditions.elementToBeClickable(By.xpath(target)));
//		WebElement destinationElement = driver.findElement(By.xpath(destination));
//		new Actions(driver).moveToElement(targetElement).pause(Duration.ofSeconds(4)).click(destinationElement)
//				.perform();
//
//	}
//
//	public void waitForSpecificElementVisible(String xpath) {
//		new WebDriverWait(driver, Duration.ofSeconds(5))
//				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
//	}
//
//	public void getAllProductNames() {
//		List<WebElement> productNames = driver
//				.findElements(By.xpath("//h4[@class='title']/a[contains(@class,'text-ellipsis')]"));
//		System.out.println("Total no. of products:" + productNames.size());
//		System.out.println("List before Unsorted from low to high");
//		for (WebElement productName : productNames) {
//			productList = productName.getText();
//			if (productList != null && !productList.isEmpty()) {
//				price = driver.findElement(By.xpath(
//						"(//h4[@class='title']//a[text()='" + productList + "'])/following::span[@class='price-new']"))
//						.getText();
//			}
//			
//			
//			System.out.println("Product name is: " + productList + "and price is " + price);
//			Map<String, String> productDetails = new HashMap<>();
//			productDetails.put("ProductName", productList);
//			productDetails.put("ProductPrice", price);
//			productListSort.add(productDetails);
//		}
//	}
//
//	public void sortProductListByPrice() {
//		Collections.sort(productListSort, new Comparator<Map<String, String>>() {
//
//			@Override
//			public int compare(Map<String, String> o1, Map<String, String> o2) {
//				double price1 = Double.parseDouble(o1.get("ProductPrice").replace(",", "").replace("$", ""));
//				double price2 = Double.parseDouble(o2.get("ProductPrice").replace(",", "").replace("$", ""));
//				return Double.compare(price1, price2);
//			}
//		});
//		
//	
//		// Print sorted product list
//		System.out.println("####################################");
//		System.out.println("Sorted product list:");
//		for (Map<String, String> product : productListSort) {
//		    System.out.println("Product: " + product.get("ProductName") + ", Price: " + product.get("ProductPrice"));
//		}
//
//	}

}
