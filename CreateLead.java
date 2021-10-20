package week5day2;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass {
	
	@Test(dataProvider = "fetchData")
	public void createLead(String company, String fName, String lName, String phNo) {
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fName);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lName);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phNo);
		driver.findElement(By.name("submitButton")).click();
	}

	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
		return ReadExcel.readExcel("CreateLead");

		/*
		 * String[][] data = new String[2][4]; data[0][0] = "Amazon"; data[0][1] =
		 * "Haritha"; data[0][2] = "V"; data[0][3] = "987"; data[1][0] = "Amazon";
		 * data[1][1] = "Anne"; data[1][2] = "C"; data[1][3] = "876"; return data;
		 */

	}
}
