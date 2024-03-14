package SeleniumChallenges;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class _8_DragAndDrop {

	@Test
	public void dragAndDrop() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://qaplayground.dev/apps/sortable-list/");
		
		List<WebElement> items = driver.findElements(By.cssSelector("#draggable-list li"));
		
		String[] expNames = {
				"Jeff Bezos",
                "Bill Gates",
                "Warren Buffett",
                "Bernard Arnault",
                "Carlos Slim Helu",
                "Amancio Ortega",
                "Larry Ellison",
                "Mark Zuckerberg",
                "Michael Bloomberg"
	};
		Actions act = new Actions(driver);
		for(int i=0;i < items.size();i++) {
			WebElement drag = items.get(i);
			WebElement tar = items.get(i);
			act.dragAndDrop(drag, tar).build().perform();					
		}
		
		for (int i = 0; i < expNames.length; i++) {
			String actNames = items.get(i).getText();
			if (actNames.equals(expNames[i])) {
				System.out.println("Position: " + (i+1) + ", Name: " + actNames + " - Correct");
			}
			else {
				System.out.println("Position: " + (i+1) + ", Name: " + actNames + " - Incorrect");
			}
		}
		
		//driver.quit();
	}
}