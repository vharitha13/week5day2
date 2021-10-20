package week5day2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpdateIncident extends ServiceNowBaseClass {

	@Test(dataProvider = "fetchData")
	public void runUpdateIncident(String fName) throws InterruptedException {
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
		WebElement urgency = driver.findElement(By.id("incident.urgency"));
		Select urgencyDrop = new Select(urgency);
		urgencyDrop.selectByValue("1");
		WebElement state = driver.findElement(By.id("incident.state"));
		Select stateDrop = new Select(state);
		stateDrop.selectByValue("2");
		driver.findElement(By.xpath("(//div//button[text()='Update'])[2]")).click();
		Thread.sleep(2000);
		String urgencyValue = driver.findElement(By.xpath("//tr[@record_class='incident']//td[7]")).getText();
		System.out.println(urgencyValue);
		String stateValue = driver.findElement(By.xpath("//tr[@record_class='incident']//td[8]")).getText();
		System.out.println(stateValue);
		if (urgencyValue.contains("Moderate") && stateValue.contains("Progress")) {
			System.out.println("The state and urgency are chosen correctly. " + "\n State is:" + stateValue
					+ "\n Urgency is: " + urgencyValue);
		} else
			System.out.println("The state and urgency are incorrect");
	}

	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
		return ReadExcel.readExcel("UpdateIncident");

	}

}
