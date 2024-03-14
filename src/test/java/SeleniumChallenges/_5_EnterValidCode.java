package SeleniumChallenges;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class _5_EnterValidCode {

	@Test
	public void validCode() throws AWTException{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://qaplayground.dev/apps/verify-account/");
		
		List<WebElement> el = driver.findElements(By.xpath("//input[@type='number']"));
		
		
		Robot robo = new Robot();
		for(WebElement e : el) {
			e.click();
			robo.keyPress(KeyEvent.VK_9);
			robo.keyRelease(KeyEvent.VK_9);
			
		}
		if(driver.findElement(By.xpath("//small[contains(text(),'Success')]")).isDisplayed()){
			System.out.println("Passed");
		}
		else {
			System.out.println("Failed");
		}
		
		driver.quit();
	}
}
