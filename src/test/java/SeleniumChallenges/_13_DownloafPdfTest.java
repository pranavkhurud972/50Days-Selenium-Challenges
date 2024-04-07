package SeleniumChallenges;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.Set;

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

public class _13_DownloafPdfTest {
	public static WebDriver driver;
	public static String pdfUrl= null;
	public static String outputFilePath = "E:\\AUTOMATION TESTING";
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void animationButtonClickTest() {
		driver.get("https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/");
		WebElement downloadLink = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
		new Actions(driver).moveToElement(downloadLink).perform();
		String mainWindowHandle = driver.getWindowHandle();
		
		//Wait until the download link clickable
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(downloadLink));
		downloadLink.click();
		
		//Wait until new window handle is available
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		
		//Switch to the new window
		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println(allWindowHandles.size());
		for(String handle : allWindowHandles) {
			if(!handle.equals(mainWindowHandle)) {
				driver.switchTo().window(handle);
				wait.until(driver ->{
					pdfUrl=driver.getCurrentUrl();
							return pdfUrl!=null;
				});
				break;
			}
		}
		
		//Download pdf
		if(!pdfUrl.isEmpty()) {
			downloadPdf(pdfUrl, outputFilePath, "pdfTest.pdf");
		}
		else {
			System.out.println("PDF URL not found.");
		}
		
	}
	
	private static void downloadPdf(String pdfUrl, String outputFilePath, String pdfFileName) {
		try {
			URLConnection connection  = new URL(pdfUrl).openConnection();
			try (InputStream inputStream = connection.getInputStream();
					FileOutputStream outputStream = new FileOutputStream(outputFilePath + File.separator + pdfFileName)){
				byte[] buffer = new byte[1024];
				int bytesRead;
				while((bytesRead = inputStream.read(buffer))!=-1) {
					outputStream.write(buffer,0,bytesRead);
				}
				System.out.println("PDF downloaded successfully to: " + outputFilePath);
 			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
