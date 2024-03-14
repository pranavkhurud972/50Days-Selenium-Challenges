package SeleniumChallenges;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class _2_DisabledTextBox {

	@Test
	public void disabledTextBox() {
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get("https://seleniumpractise.blogspot.com/2016/09/how-to-work-with-disable-textbox-or.html");
		driver.findElement(By.id("fname")).sendKeys("Tom");
		
		//How to enable disabled text box:
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.querySelector(\"#pass\").disabled=false");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//Or
		/*
		 * ((JavascriptExecutor)driver). executiveScript("document.getElementById('value of the Id attribute').value ='value which you want to send';");
 Or 

((JavascriptExecutor)driver). executiveScript("arguments[0].value ='value which you want to send';", WebElement);
		 */
		
		//Now click on show me button and wait for 15 sec to enable the text box:
		driver.findElement(By.xpath("//input[@type='button']")).click();
		
		WebElement disabled15Seconds = driver.findElement(By.cssSelector("input[id='passnew']"));
		wait.until(ExpectedConditions.elementToBeClickable(disabled15Seconds));
		
		driver.findElement(By.id("passnew")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		WebElement gettext = driver.findElement(By.xpath("//div[@class='status-msg-body']"));
		wait.until(ExpectedConditions.visibilityOf(gettext));
		
		System.out.println(gettext.getText());
		

	}
}
