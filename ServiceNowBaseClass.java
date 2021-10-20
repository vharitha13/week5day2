package week5day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowBaseClass {
	
	public ChromeDriver driver;
	
	@Parameters({"username","password"})
	@BeforeMethod
	public void preCondition(String uName, String pWord) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://dev81069.service-now.com/");
		WebElement frame1 = driver.findElement(By.name("gsft_main"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("user_name")).sendKeys(uName);
		driver.findElement(By.id("user_password")).sendKeys(pWord);
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		WebElement search = driver.findElement(By.id("filter"));
		search.click();
		search.sendKeys("incident");
		driver.findElement(By.xpath("(//a//div//div[text()='All'])[2]")).click();

	}
	
	@AfterMethod
	public void postCondition() {
		driver.close();
	}
	

}
