package example.org.MavenProj1;

import org.testng.annotations.Test;

import pages.LoginPageObject;
import utility.ExcelUtility;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

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
