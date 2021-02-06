package variousConcepts;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomeWorkWithNewConcept {
	
	WebDriver driver;
	
	@BeforeTest
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void AddCustomerWithChanges() throws InterruptedException {
		
		By USERNAME_FIELD_ELEMENT = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD_ELEMENT = By.xpath("//input[@id='password']");
		By SIGNIN_BUTTON_ELEMENT = By.xpath("//button[@name='login']");
		By DASHBOARD_BUTTON_ELEMENT = By.xpath("//h2[contains(text(),' Dashboard ')]");
		By CUSTOMER_BUTTON_ELEMENT = By.xpath("//span[contains(text(),'Customers')]");
		By ADD_CUSTOMER_BUTTON_ELEMENT = By.xpath("//a[contains(text(),'Add Customer')]");
		By FULLNAME_FIELD_ELEMENT = By.xpath("//input[@id='account']");
		By EMAIL_FIELD_ELEMENT = By.xpath("//input[@id='email']");
		By PHONE_FIELD_ELEMENT = By.xpath("//input[@id='phone']");
		
		
		//Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";
		
		//Test data
		String fullName = "Tech Fios";
		String companyName = "TechFios";
		String emailAddress = "admin@gmail.com";
		String phoneNumber = "3456782908";
		
		
		driver.findElement(USERNAME_FIELD_ELEMENT).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD_ELEMENT).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_ELEMENT).click();
		//driver.findElement(DASHBOARD_BUTTON_ELEMENT).getText();
		System.out.println(driver.findElement(DASHBOARD_BUTTON_ELEMENT).getText());
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CUSTOMER_BUTTON_ELEMENT));
		
		driver.findElement(CUSTOMER_BUTTON_ELEMENT).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CUSTOMER_BUTTON_ELEMENT));
		driver.findElement(ADD_CUSTOMER_BUTTON_ELEMENT).click();
		
		
		//Generate Random Number
		Random rnd = new Random();
		int generatedNo = rnd.nextInt(999);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(FULLNAME_FIELD_ELEMENT));
		driver.findElement(FULLNAME_FIELD_ELEMENT).sendKeys(fullName + generatedNo);
		driver.findElement(EMAIL_FIELD_ELEMENT).sendKeys(generatedNo + emailAddress);
		
		
		
		
		
	}

}
