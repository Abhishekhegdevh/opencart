package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; 
	public Properties P;
	public Logger logger;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// Load properties
		try (FileInputStream fl = new FileInputStream("./src/test/resources/config.properties")) {
			P = new Properties();
			P.load(fl);
		}

		logger = LogManager.getLogger(this.getClass());

		if (P.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			String hubURL = "http://192.168.0.102:4444/wd/hub"; // Verify this IP is correct!
			logger.info("Connecting to Remote Grid at: " + hubURL);

			// Selenium 4 uses Options, not DesiredCapabilities
			if (br.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.setPlatformName(getPlatformName(os)); 
				driver = new RemoteWebDriver(new URL(hubURL), options);

			} else if (br.equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
				options.setPlatformName(getPlatformName(os));
				driver = new RemoteWebDriver(new URL(hubURL), options);

			} else {
				throw new IllegalArgumentException("Browser not supported for remote execution: " + br);
			}

		} else if (P.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			logger.info("Launching local browser: " + br);
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("Browser not supported: " + br);
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(P.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	private String getPlatformName(String os) {
		if (os.equalsIgnoreCase("windows")) return "Windows 11"; // Adjust strictly for Grid matching
		if (os.equalsIgnoreCase("mac")) return "Mac";
		if (os.equalsIgnoreCase("linux")) return "Linux";
		return os;
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void logout() {
		if (driver!= null) {
			driver.quit();
		}
	}

	public String randomstring() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomnumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphanumber() {
		return randomstring() + "*" + randomnumber();
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}