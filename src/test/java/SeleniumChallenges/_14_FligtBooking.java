package SeleniumChallenges;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class _14_FligtBooking {
	public static WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().window().maximize();
	}
	
	@Test(priority = 1)
	public void FlightResultTest() {
		driver.get("https://www.jetblue.com/");
		try {
			driver.switchTo().frame(1);
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Accept All Cookies']")));
			driver.findElement(By.xpath("//a[normalize-space()='Accept All Cookies']")).click();
			driver.switchTo().defaultContent();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'jb-autocomplete')])[1]")));
		driver.findElement(By.xpath("//input[contains(@id,'jb-autocomplete')])[1]")).click();
		driver.findElement(By.xpath("//input[contains(@id,'jb-autocomplete')])[1]")).sendKeys("Mumbai");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[contains(text(),'Mumbai')]")));
		driver.findElement(By.xpath("//strong[contains(text(),'Mumbai')]")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'jb-autocomplete')])[2]")));
		driver.findElement(By.xpath("//input[contains(@id,'jb-autocomplete')])[2]")).click();
		driver.findElement(By.xpath("//input[contains(@id,'jb-autocomplete')])[1]")).sendKeys("London-Heathrow, UK (LHR)");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//strong[contains(text(),'London')]")));
		driver.findElement(By.xpath("//strong[contains(text(),'London')]")).click();
		
		driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).click();
	    driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).sendKeys(setCurrentDate());

	    driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).click();
	    driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).sendKeys(setCurrentDatePlusTwo());

	    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span//span[normalize-space()='Search flights']")).click();

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
            driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span[normalize-space()='Continue to flight results']")).click();


        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//jb-icon[@name='loading']")));
        String confirmation_msg  = driver.findElement(By.xpath("//h2[contains(normalize-space(),'No flights have been found for your search criteria')]")).getText();
        if(confirmation_msg.contains("No flights have been found"))
        {
            System.out.println("Test is passed!");
        }
        else{
            System.out.println("Test is failed!");
        }

    }

    @Test(priority = 2)
    public void FlightValidationTest()
    {
        // Navigate to the webpage
        driver.get("https://www.jetblue.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")));
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[1]")).sendKeys("Mumbai");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Mumbai')]")));
        driver.findElement(By.xpath("//strong[contains(text(),'Mumbai')]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")));
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'jb-autocomplete')])[2]")).sendKeys("London-Heathrow, UK (LHR)");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'London')]")));
        driver.findElement(By.xpath("//strong[contains(text(),'London')]")).click();

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[1]")).sendKeys("01/01/2024");

        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).click();
        driver.findElement(By.xpath("(//input[contains(@id,'date-picker')])[2]")).sendKeys("01/02/2024");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span//span[normalize-space()='Search flights']")));
        driver.findElement(By.xpath("//button//span//span[normalize-space()='Search flights']")).click();

      WebElement error =   new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//jb-error[@id]")));
        if(error.isDisplayed())
        {
            System.out.println("Error Validation is passed!");
        }
        else{
            System.out.println("Error Validation is failed!");
        }

    
	}
	@Test(priority = 2)
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
	public String setCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedCurrentDate = currentDate.format(formatter);
		return formattedCurrentDate;
	}
	
	public String setCurrentDatePlusTwo() {
		LocalDate currentDate = LocalDate.now().plusDays(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedCurrentDate = currentDate.format(formatter);
		return formattedCurrentDate;
		
	}
}
