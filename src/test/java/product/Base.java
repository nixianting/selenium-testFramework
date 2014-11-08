package product;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Base {

	protected static Selenium selenium;
	private static Properties prop = null;
	protected WebDriver driver = null;
	protected String baseUrl;

	static {
		InputStream in = Base.class.getResourceAsStream("/selenium.properties");
		try {
			prop = new Properties();
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void before() throws Exception {

		String seleniumDriver = prop.getProperty("seleniumDriver");

		if ("webdriver".equals(seleniumDriver)) {

			String type = prop.getProperty("type");
			baseUrl = Base.prop.getProperty("baseUrl");
			if ("chrome".equals(type)) {

				System.setProperty("webdriver.chrome.driver",
						prop.getProperty("selenium.webDriver.File"));
				driver = new ChromeDriver();
			} else if ("firefox".equals(type)) {

				System.setProperty("webdriver.firefox.bin",
						prop.getProperty("selenium.webDriver.File"));
				driver = new FirefoxDriver();
			} else if ("iexplore".equals(type)) {

				System.setProperty("webdriver.ie.driver",
						prop.getProperty("selenium.webDriver.File"));

				driver = new InternetExplorerDriver();

			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} else if ("seleniumRC".equals(seleniumDriver)) {
			selenium = new DefaultSelenium(prop.getProperty("selenium.rc.url"),
					Integer.valueOf(prop.getProperty("selenium.rc.port")), "*"
							+ prop.getProperty("selenium.browser"),
					prop.getProperty("selenium.test.url"));
			selenium.start();
			selenium.windowMaximize();

		}
	}

	@After
	public void after() {
		String seleniumDriver = prop.getProperty("seleniumDriver");
		if ("webdriver".equals(seleniumDriver)) {
			driver.close();
			driver.quit();
		} else if ("seleniumRC".equals(seleniumDriver)) {
			selenium.stop();
		}

	}

	protected String getDataName(String putName, String name, String tip) {
		int i = 0;
		do {
			i++;
			selenium.type("name=" + putName, name + i);
			selenium.click("css=button[type=\"submit\"]");
			selenium.click("name=" + putName);
		} while (tip.equals(selenium.getText("css=div.tip-inner.tip-bg-image")));
		return name + i;
	}

}
