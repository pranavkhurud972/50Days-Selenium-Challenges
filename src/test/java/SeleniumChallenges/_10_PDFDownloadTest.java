package SeleniumChallenges;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _10_PDFDownloadTest {
	public static WebDriver driver;
	public static String downloadFilePath = "E:\\AUTOMATION TESTING";
	
	@BeforeClass
	public void setup() {
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadFilePath);
		prefs.put("browser.download.manager.showWhenStarting", false);
		//Disable save address popup
		//pdf download
		prefs.put("plugins.plugins_disabled", new String[] {"Chrome pdf viewer"});
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("plugins.always_open_pdf_externally", true);
		
		//set up ChromeOptions
		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("prefs", prefs);
		
		//set up webdriver
		driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void downloadPdfTest() throws IOException {
		
		driver.get("https://demo.automationtesting.in/FileDownload.html");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement download = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@type='button']")));
		
		//Need to scroll down as click is intercepted due to advertise banner
		JavascriptExecutor js  = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", download);
		download.click();
		
		//Wait for file to download
		wait.until((WebDriver wd) -> {
			File downloadedFile = new File(downloadFilePath+ "\\sampleFile.pdf");
			return downloadedFile.exists() && downloadedFile.length()>0;
		});
		
		PDDocument doc = Loader.loadPDF(new File(downloadFilePath + "\\sampleFile.pdf"));
		PDFTextStripper stripper = new PDFTextStripper();
		String pdfText = stripper.getText(doc);
		String searchText = "Get Tickets";
		if(pdfText.contains(searchText)) {
			System.out.println("Text is found in the Pdf");
		}
		else {
			System.out.println("Test is not found in the pdf");
		}
		

	}
}
