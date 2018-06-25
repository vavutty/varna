package example.org.MavenProj1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPageObject;
import utility.ExcelUtility;

public class BookTest {
	  private WebDriver driver;
  @Test(priority=1 ,dataProvider="logindata")
  public void login(String txtusername,String txtpassword) {
	  LoginPageObject.uname.sendKeys(txtusername);
	  LoginPageObject.password.sendKeys(txtpassword);
	  LoginPageObject.login_button.click();
	 //driver.findElement(By.name("userName")).sendKeys("invalidUN");
	 //driver.findElement(By.name("password")).sendKeys("invalidPW");
	// driver.findElement(By.name("login")).click();
	  Assert.assertEquals("Find a Flight: Mercury Tours: ",driver.getTitle());
  }
  @Test(priority=2)
  public void findFlight()
  {
	  Select passengers= new Select(driver.findElement(By.name("passCount")));
	  passengers.selectByIndex(1);
	  
	  Select Depart= new Select(driver.findElement(By.name("fromPort")));
	  Depart.selectByValue("Frankfurt");
	  
	  Select date= new Select(driver.findElement(By.name("fromMonth")));
	  date.selectByValue("4");
	  
	  Select day= new Select(driver.findElement(By.name("fromDay")));
	  day.selectByValue("4");
	  
	  Select date1= new Select(driver.findElement(By.name("toMonth")));
	  date1.selectByValue("4");
	  
	  Select day1= new Select(driver.findElement(By.name("toDay")));
	  day1.selectByValue("7");
	  
	  driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/font/input[1]"));
  
	  Select airline= new Select(driver.findElement(By.name("airline")));
	  airline.selectByVisibleText("Blue Skies Airlines");
	  
	  driver.findElement(By.name("findFlights")).click();
  }
  @DataProvider(name="logindata")
  public String[][] login_data()throws Exception
  {
	  ExcelUtility.setExcelPath("Sheet1", "C:\\Users\\A06438_P5.Training\\Desktop\\SeleniumTestData.xlsx");
	  String username=ExcelUtility.getCellData(1, 1);
	  String password=ExcelUtility.getCellData(1, 2);
	  return new String[][] {
	  new String[] {username,password},
	  };
  }
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.gecko.driver","C:\\Users\\A06438_P5.Training\\Downloads\\geckodriver-v0.20.1-win64\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  driver.get("http://newtours.demoaut.com/");
	  PageFactory.initElements(driver, LoginPageObject.class);
  }

  @AfterTest
  public void afterTest() {
  }

}
