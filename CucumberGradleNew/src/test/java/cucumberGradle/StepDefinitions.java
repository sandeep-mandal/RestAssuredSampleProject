package cucumberGradle;
 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;

import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.eviware.soapui.support.SoapUIException;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions { 
   public static WebDriver driver = null; 
   public static final String USERNAME = "sandeepmandal";
   public static final String ACCESS_KEY = "c0319381-47d5-4a52-a5fe-95dd77f8342e";
   public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
   //@Before 
   public void setUpSauceLabs() throws Exception{ 	   	 
	   System.out.println(URL);
		String chromedriverlocation = "D://Selenium//Webdrivers//chromedriver.exe";		   	  
		System.setProperty("webdriver.chrome.driver",chromedriverlocation);
		
		ChromeOptions options = new ChromeOptions();// open window as maximized
		options.addArguments("--start-maximized");
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions");
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("version", "latest");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("JENKINS_BUILD_NUMBER"));
		
		driver = new RemoteWebDriver(new URL(URL), capabilities);
		driver.manage().deleteAllCookies();
		this.printSessionId();
	   } 
   
   private void printSessionId() {	   
	    String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
	    (((RemoteWebDriver) driver).getSessionId()).toString(), System.getenv("JOB_NAME") + "__" + System.getenv("JENKINS_BUILD_NUMBER"));
	    System.out.println(message);
	} 
   
   @Before
   public void setUpLocal(){ 
		String chromedriverlocation = "D://Selenium//Webdrivers//chromedriver.exe";		   	  
		System.setProperty("webdriver.chrome.driver",chromedriverlocation);
		
		ChromeOptions options = new ChromeOptions();// open window as maximized
		options.addArguments("--start-maximized");
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions");
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(capabilities);
		driver.manage().deleteAllCookies();
	   } 
   
   @After 
   public void cleanUp(){ 
	      driver.close(); 
	   } 
   
   @Given("^user navigates to Facebook$")
   public void user_navigates_to_Facebook(){			
	   System.out.println("Launching Facebook"); 
	   driver.navigate().to("https://www.facebook.com/");	
   }   
	
   @When("^I enter Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$") 
   public void I_enter_Username_as_and_Password_as(String arg1, String arg2) {
	   System.out.println("Entering Username and Password");
	   driver.findElement(By.id("email")).sendKeys(arg1);
	   driver.findElement(By.id("pass")).sendKeys(arg2);
	   driver.findElement(By.id("loginbutton")).click();		  
   } 
   
	@When("^make a call to soapui$")
	public void make_a_call_to_soapui() {
		System.out.println("Calling SoapUI");		
	} 
	
   @Then("^login should be unsuccessful$") 
   public void validateRelogin() throws XmlException, IOException, SoapUIException { 
	  System.out.println("Call SoapUI and Log Out"); 
	  SoapProjects soap = new SoapProjects();
	  
	  soap.runSoapUI("src/test/resources/calculator-soapui-project.xml","CalculatorSoap12 TestSuite","Add TestCase","ExpectedResult=9!InputValOne=5!InputValTwo=4");
	  //soap.runSoapUI_MethodOne("src/test/resources/calculator-soapui-project.xml","CalculatorSoap12 TestSuite","Subtract TestCase","ExpectedResult=2");
	  //soap.runSoapUI_MethodTwo();
	  //soap.executeSoapTestCase("src/test/resources/calculator-soapui-project.xml", "CalculatorSoap12 TestSuite","Add TestCase");
	  
	  /*if(driver.getCurrentUrl().equalsIgnoreCase(
	     "https://www.facebook.com/login.php?login_attempt=1&lwv=110")){ 
		  System.out.println("Test Pass"); 
	  } else { 
	     System.out.println("Test Failed"); 
	  } */
	   
   }   
}