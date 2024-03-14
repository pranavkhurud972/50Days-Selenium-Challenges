package SeleniumChallenges;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class _3_readRating {
	@Test
	public void rating() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get("https://play1.automationcamp.ir/advanced.html");
//	     JavascriptExecutor js = (JavascriptExecutor) driver;
//	        String text = js.executeScript("return window.getComputedStyle(document.querySelector('.star-rating'),'::after').getPropertyValue('content')")
//	                .toString();
//	        String textTobeTyped=text.replaceAll("\"","");
//	        driver.findElement(By.id("txt_rating")).sendKeys(textTobeTyped);
//	        driver.findElement(By.id("check_rating")).click();
//	        Thread.sleep(50000);
		WebElement getRating = driver.findElement(By.xpath("//label[@class='star-rating']"));
		String rating = getRating.getText();
		WebElement enterRating = driver.findElement(By.xpath("//input[@id='txt_rating']"));
		enterRating.sendKeys(rating);
		
}
}
