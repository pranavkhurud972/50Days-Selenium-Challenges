package SeleniumChallenges;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
/*
 * 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐟𝐞𝐭𝐜𝐡 𝐚𝐧𝐝 𝐯𝐞𝐫𝐢𝐟𝐲 - 𝐭𝐡𝐞 𝐭𝐞𝐱𝐭 𝐟𝐫𝐨𝐦 𝐐𝐑 𝐂𝐨𝐝𝐞.

𝐒𝐭𝐞𝐩𝐬:
1)Navigate to: https://lnkd.in/d2RbkjbV
2)Enter the text "I am an Automation QA" in the input field.
3)Click on the "Generate QR Code" button.
4)Now you can try to fetch the embedded text from the QR Code generated. 
5)Verify that your fetched-embedded text from QR is the same as you have given the input text.

✅𝐇𝐢𝐧𝐭: 𝐔𝐬𝐞 𝐙𝐗𝐢𝐧𝐠 𝐀𝐏𝐈 (𝐁𝐚𝐫𝐜𝐨𝐝𝐞 𝐒𝐜𝐚𝐧𝐧𝐢𝐧𝐠 𝐀𝐏𝐈) 	
 */
public class _20_ReadTextFromQR {
	
	public static WebDriver driver;
	
  @Test
  public void readTextFromQRTest() throws IOException, NotFoundException, ChecksumException, FormatException {
	  driver.findElement(By.cssSelector("input[placeholder='Enter text or URL']")).sendKeys("I am an Automation QA");
	  driver.findElement(By.cssSelector("div[class='form'] button")).click();
	  String qrCodeImageUrl = driver.findElement(By.xpath("//img[@alt='qr-code']")).getAttribute("src");
      System.out.println("QR Code Image URL: " + qrCodeImageUrl);

      if (qrCodeImageUrl != null) {
          // Fetch the embedded text from the QR code
          String embeddedText = getEmbeddedTextFromQRCode(qrCodeImageUrl);
          // Verify that the fetched-embedded text from QR is the same as the input text
          Assert.assertEquals(embeddedText, "I am an Automation QA", "Embedded text from QR code does not match input text.");
      } else {
          // Log an error message if the "src" attribute is null
          System.out.println("QR code image URL is null.");
      }
      // Fetch the embedded text from the QR code
     // String embeddedText = getEmbeddedTextFromQRCode(qrCodeImageUrl);

      
      // Verify that the fetched-embedded text from QR is the same as the input text
    //  Assert.assertEquals(embeddedText, "I am an Automation QA", "Embedded text from QR code does not match input text.");
  }
  
  // Method to fetch the embedded text from the QR code using ZXing API
  private String getEmbeddedTextFromQRCode(String qrCodeImageUrl) throws IOException, NotFoundException, ChecksumException, FormatException {
      BufferedImage qrCodeImage = ImageIO.read(new URL(qrCodeImageUrl));
      BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(qrCodeImage)));
      Result result = new com.google.zxing.qrcode.QRCodeReader().decode(binaryBitmap);
      return result.getText();
  }
  @BeforeClass
  public void setUp() {
	  driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://qaplayground.dev/apps/qr-code-generator/");
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
/*
 *  @Test
  void daily20() throws IOException, NotFoundException {
	  driver.findElement(By.cssSelector("input[placeholder='Enter text or URL']")).sendKeys("I am an Automation QA");
	  driver.findElement(By.cssSelector("div[class='form'] button")).click();
	 // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='qr-code']")));
	  String qrCodeImageUrl = driver.findElement(By.xpath("//img[@alt='qr-code']")).getAttribute("src");

	  BufferedImage qrCodeImage = ImageIO.read( new URL(qrCodeImageUrl));
	  
	  BinaryBitmap binaryBitmap = new BinaryBitmap( new HybridBinarizer( new BufferedImageLuminanceSource(qrCodeImage)));
	  Result grCodeResult = new MultiFormatReader( ).decode(binaryBitmap);
	  String decodedText = grCodeResult.getText();
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(decodedText, "I am an Automation QA");
	  softAssert.assertAll();
	  
	  }
  // Method to fetch the embedded text from the QR code using ZXing API
  private String getEmbeddedTextFromQRCode(String qrCodeImageUrl) throws IOException, NotFoundException, ChecksumException, FormatException {
      BufferedImage qrCodeImage = ImageIO.read(new URL(qrCodeImageUrl));
      BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(qrCodeImage)));
      Result result = new com.google.zxing.qrcode.QRCodeReader().decode(binaryBitmap);
      return result.getText();
  }
*/
