package week5day2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeleteIncident extends ServiceNowBaseClass {

	@Test(dataProvider="fetchData")
	public void runDeleteIncident(String fName) throws InterruptedException {
		WebElement frame2 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame2);
		WebElement drop1 = driver.findElement(By.xpath("//select[@role='listbox']"));
		Select dropdown = new Select(drop1);
		dropdown.selectByValue("caller_id");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role='search']//input")).sendKeys(fName, Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='vt']//a)[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Delete']")).click();
		driver.findElement(By.xpath("//div//button[@id='ok_button']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@role='search']//input")).sendKeys(fName, Keys.ENTER);
		String records = driver.findElement(By.xpath("//tbody[@class='list2_body']//td")).getText();
		if (records.contains("No")) {
			System.out.println("The record is deleted successfully");
		} else
			System.out.println("Record is still available");
	}
	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
		return ReadExcel.readExcel("DeleteIncident");

	}

}
