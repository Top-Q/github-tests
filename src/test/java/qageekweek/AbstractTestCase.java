package qageekweek;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import qageekweek.po.IntroPage;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public class AbstractTestCase {

	private static final String URL = "http://www.github.com";
	protected ReportDispatcher report = ReportManager.getInstance();	
	protected IntroPage introPage;
	private WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = WebDriverFactory.buildDriver(WebDriverFactory.DriverType.CHROME);
		driver.get(URL);
		introPage = new IntroPage(driver);
	}
	
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	protected void step(String description) {
		report.endLevel();
		report.startLevel(description);
	}
	
	public String getProp(String key) throws IOException {
		File propFile = new File("config.properties");
		if (!propFile.exists()) {
			propFile.createNewFile();
			throw new IOException("Configuration file is empty");
		}
		try (InputStream input = new FileInputStream(propFile)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            return prop.getProperty(key, "");

        } catch (IOException ex) {
            throw ex;
        }

	}

	
	protected void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}
	
}
