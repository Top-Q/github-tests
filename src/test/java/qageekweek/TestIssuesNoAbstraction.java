package qageekweek;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestIssuesNoAbstraction {
	private static final String REPO_NAME = "delme";
	private WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = WebDriverFactory.buildDriver(WebDriverFactory.DriverType.CHROME);
		driver.get("http://www.github.com");
	}
	
	@Test
	public void testCreateNewIssue() throws InterruptedException {
		// Login page
		driver.findElement(By.cssSelector("a[href='/login']")).click();
		driver.findElement(By.id("login_field")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");;
		driver.findElement(By.name("commit")).click();
		
		// Go the the repository
		driver.findElement(By.id("dashboard-repos-filter-left")).sendKeys(REPO_NAME);
		
		// Select the issues
		driver.findElement(By.cssSelector("a[href='/itaiag/delme']")).click();
		driver.findElement(By.cssSelector("a[data-selected-links^='repo_issues']")).click();
		
		// Click on new issue
		driver.findElement(By.partialLinkText("New issue")).click();
		
		
		
		final String issueTitle = "issueTitle" + System.currentTimeMillis();
		driver.findElement(By.id("issue_title")).sendKeys(issueTitle);
		driver.findElement(By.id("issue_body")).sendKeys("This is the issue body");
		// Sumiting the new issue
		driver.findElement(By.cssSelector("div.timeline-comment button.btn-primary")).click();
		
		// Returning to the issues page.
		driver.findElement(By.cssSelector("a[data-selected-links^='repo_issues']")).click();
		
		// Search for our issue
		driver.findElement(By.id("js-issues-search")).sendKeys(issueTitle);
		driver.findElement(By.id("js-issues-search")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		// get the number of issues
		int numOfIssues = driver.findElements(By.cssSelector("div[aria-label='Issues'] div.js-issue-row")).size();
		Assert.assertEquals(numOfIssues, 1);
		
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
}
