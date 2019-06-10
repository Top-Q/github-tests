package qageekweek;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
	
	public static enum DriverType {
		CHROME,FIREFOX,EDGE,SAFARI
	}
	
	public static WebDriver buildDriver(DriverType type) {
		WebDriver driver = null;
		switch (type) {
		case EDGE:
		case FIREFOX:
		case SAFARI:
		case CHROME:
			driver =  new ChromeDriver();
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
}
