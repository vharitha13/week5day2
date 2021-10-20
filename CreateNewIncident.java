package week5day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateNewIncident extends ServiceNowBaseClass {

	@Test(dataProvider = "fetchData")
	public void runCreateIncident(String userID, String fName, String lName, String shortDesc) throws InterruptedException {
		WebElement frame2 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']//span")).click();
		driver.switchTo().defaultContent();
		Set<String> winHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(winHandles);
		driver.switchTo().window(list.get(1));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		driver.findElement(By.id("sys_user.user_name")).sendKeys(userID);
		driver.findElement(By.id("sys_user.first_name")).sendKeys(fName);
		driver.findElement(By.id("sys_user.last_name")).sendKeys(lName);
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		driver.switchTo().window(list.get(0));
		Thread.sleep(1000);
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//div//input[@id='incident.short_description']")).sendKeys(shortDesc);
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
		driver.findElement(By.xpath("//div[@role='search']//input")).sendKeys(fName, Keys.ENTER);
		Thread.sleep(2000);
		String incNumber = driver.findElement(By.xpath("(//td[@class='vt']//a)[1]")).getText();
		System.out.println("The incident number is:" + incNumber);
		String firstRow = driver.findElement(By.xpath("(//tr[contains(@class,'list_row')])[1]")).getText();
		Thread.sleep(2000);
		if (firstRow.contains(fName)) {
			System.out.println("The incident is created");
		} else
			System.out.println("The incident is not created");
	}

	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
		return ReadExcel.readExcel("CreateIncident");

	}

}
