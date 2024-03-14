package SeleniumChallenges;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class _4_GrowingClickable {
	
	@Test
	public void growingElement() {
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://testpages.eviltester.com/styled/challenges/growing-clickable.html");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='growbutton']")));
		 driver.findElement(By.xpath("//button[@id='growbutton']")).click();
		String actStatus = driver.findElement(By.id("growbuttonstatus")).getText();
		String expStatus = "Event Triggered";
		//Assert.assertEquals(actStatus.equals(expStatus),"Status found incorrect");
		Assert.assertEquals(actStatus, expStatus);
		driver.quit();
	}
}
