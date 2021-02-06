package variousConcepts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LearnTestNG {

	WebDriver driver;
	String browser = null;
	String url = null;
	
	@BeforeClass
	public void readConfg(){
		
		Properties prop = new Properties();
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("browser used : " + browser);
			url = prop.getProperty("url");
			System.out.println("Used url : " + url);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
		
	@BeforeMethod
	public void init() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			// Setting up the property
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			// Creating web driver instance
			driver = new ChromeDriver();
			
		}else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver =  new FirefoxDriver();

		}

		// Maximizing Browser
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// Get to the site
		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test
	public void loginTestA() {
		
		WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));
		
		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_FIELD_ELEMENT.sendKeys("abc123");
		SIGNIN_BUTTON_ELEMENT.click();
		
		WebElement DASHBOARD_BUTTON_ELEMENT = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]"));
		
		System.out.println("-----------------" + DASHBOARD_BUTTON_ELEMENT.getText());
		Assert.assertEquals("Dashboard",DASHBOARD_BUTTON_ELEMENT.getText(),"Wrong Page!!!");
		
	}
	
	
}
