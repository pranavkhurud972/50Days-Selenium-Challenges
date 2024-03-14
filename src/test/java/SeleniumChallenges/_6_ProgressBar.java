package SeleniumChallenges;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class _6_ProgressBar {

	private static WebDriver driver;
	@Test
	public void progressBarClick() {
	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://uitestingplayground.com/progressbar");
		
		WebElement startBtn = driver.findElement(By.id("startButton"));
		startBtn.click();
		
		//Poll the progress of the progress bar until it reaches 75%
		WebElement progressbar = driver.findElement(By.id("progressBar"));
		while(getProgressBarWidth(progressbar)<75) {
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		WebElement stopBtn = driver.findElement(By.id("stopButton"));
		stopBtn.click();

        driver.quit();
	}
	
	//Method to get the width of an progress bar
	private static int getProgressBarWidth(WebElement progressBar) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String width = (String) js.executeScript("return arguments[0].style.width" , progressBar);
		//Extract the numerical value from the width string
		return Integer.parseInt(width.replaceAll("[^0-9]", ""));
	}
}
