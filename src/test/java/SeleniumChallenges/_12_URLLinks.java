package SeleniumChallenges;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class _12_URLLinks {

	public static WebDriver driver;
	Map<String, Integer> pageTitleLinkCountMap = new HashMap<>();
	String[] pageURLs = {
			"https://demo.guru99.com/",
			"https://naveenautomationlabs.com/opencart/",
			"https://www.ministryoftesting.com/articles/websites-to-practice-testing",
			"https://www.lambdatest.com/blog/selenium-best-practices-for-web-testing/"
	};
	
	@BeforeClass
	public void setUp() {
		ChromeOptions opts = new ChromeOptions();
		opts.addArguments("-headless");
		driver = new ChromeDriver(opts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void linkVerificationTest() {
		driver.get("https://www.ministryoftesting.com/articles/websites-to-practice-testing");
		for(String pageURL : pageURLs) {
			System.out.println("Navigating to url:" + pageURL);
			driver.get(pageURL);
			String pageTitle = driver.getTitle();
			List<WebElement> links = driver.findElements(By.tagName("a"));
			int linkCount = links.size();
			System.out.println("Page title:" + pageTitle + " Number of links " + linkCount);
			pageTitleLinkCountMap.put(pageTitle, linkCount);
			
			
		}
		
		//find page title with maxi link count
		int maxLinkCount = 0;
			String maxLinkPageTitle = null;
			for(Map.Entry<String, Integer> entry : pageTitleLinkCountMap.entrySet()) {
				if(entry.getValue() >= maxLinkCount) {
					maxLinkCount = entry.getValue();
					maxLinkPageTitle = entry.getKey();
					}
			}
			
				System.out.println("==============================================");
				//Print the page title with maxi link count
				System.out.println("Page with max links: " + maxLinkPageTitle + " - " + maxLinkCount + " links");
	}
	
	/*@Test
	 * public void testURLLinks() {
        // URLs to visit
        String[] urls = {"https://demo.guru99.com/",
    			"https://naveenautomationlabs.com/opencart/",
    			"https://www.ministryoftesting.com/articles/websites-to-practice-testing",
    			"https://www.lambdatest.com/blog/selenium-best-practices-for-web-testing/"};
        
        // Visit each URL and store the status
        for (String url : urls) {
            driver.get(url);
            String status = "Valid";
            try {
                driver.findElement(By.tagName("a")); // Check if page is loaded
            } catch (Exception e) {
                status = "Invalid";
            }
            urlMap.put(url, status);
        }
        
        // Print the results
        Set<Map.Entry<String, String>> entrySet = urlMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println("Page with URL: " + entry.getKey() + " is " + entry.getValue());
        }
    }
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
