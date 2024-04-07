package SeleniumChallenges;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _11_TestTags {
	public static WebDriver driver;
	
	@BeforeClass
	public void setup() {		
		//set up webdriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void tags() {
		
	}
	
//	public int getTagCount() {
//		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='content']//ul//li"));
//		elements.get(getTagCount());
//		return Count;
//		
//	}
}
