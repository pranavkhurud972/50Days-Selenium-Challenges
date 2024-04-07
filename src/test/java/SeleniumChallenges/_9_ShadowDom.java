package SeleniumChallenges;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class _9_ShadowDom {

	public static WebDriver driver;

	@BeforeTest
	public void setup() {
		ChromeOptions options = new ChromeOptions();
       // options.setAcceptInsecureCerts(true);
        options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void shadowDomTest() throws UnsupportedFlavorException, IOException {

		driver.get("https://uitestingplayground.com/shadowdom");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		/*
		 * // Assuming 'driver' is an instance of WebDriver WebElement shadowHost =
		 * driver.findElement(By.cssSelector("your-shadow-host-selector")); WebElement
		 * shadowRoot = (WebElement)
		 * ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot",
		 * shadowHost); WebElement shadowElement =
		 * shadowRoot.findElement(By.cssSelector("your-shadow-element-selector"));
		 * 
		 */
		SearchContext shodowDomContext = driver.findElement(By.tagName("guid-generator")).getShadowRoot();
		shodowDomContext.findElement(By.cssSelector(".button-generate")).click();
		WebElement element = shodowDomContext.findElement(By.cssSelector(".edit-field"));

		String inputTextString = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",
				element);
		shodowDomContext.findElement(By.cssSelector("#buttonCopy")).click();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Object clipdata = clipboard.getData(DataFlavor.stringFlavor);

		if (clipdata.equals(inputTextString)) {
			System.out.println("Test is passed..");
		} else {
			System.out.println("Test is failed..");
		}
	}

}
