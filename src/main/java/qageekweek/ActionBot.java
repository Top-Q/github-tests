package qageekweek;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

public class ActionBot {

	protected ReportDispatcher report = ReportManager.getInstance();

	private final WebDriver driver;

	private WebDriverWait wait;

	public ActionBot(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
	}

	public ActionBot clickOn(By by) {
		report("click", by);
		driver.findElement(by).click();
		return this;
	}

	public ActionBot typeTo(By by, String keys) {
		report("type to " + keys, by);
		driver.findElement(by).sendKeys(keys);
		return this;
	}

	public ActionBot typeTo(By by, Keys keys) {
		report("type to " + keys, by);
		driver.findElement(by).sendKeys(keys);
		return this;
	}

	public int count(By by) {
		report("count", by);
		return driver.findElements(By.cssSelector("div[aria-label='Issues'] div.js-issue-row")).size();
	}

	public ActionBot waitForVisible(By by) {
		report("wait for visibility", by);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return this;
	}

	public ActionBot takeScreenshot(String description) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		report.addImage(scrFile, description);
		return this;
	}

	public ActionBot waitForVisible(By by, int timeoutInSec) {
		return this;
	}

	private void report(String action, By by) {
		if (by instanceof DescriptiveBy) {
			report.log("About to " + action + " on " + ((DescriptiveBy) by).getDescription());
		}
	}
}
