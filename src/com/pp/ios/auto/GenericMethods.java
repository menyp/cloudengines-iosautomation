package com.pp.ios.auto;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.google.common.base.Function;

/**
 * 
 * @author meny peled Native iOS Sanity related tests
 * @version 1.0.0 14-January-2014
 * 
 */

public class GenericMethods {

	IOSDriver driver;
//
//	public IOSDriver loginNativeAndroid(GenericMethods genMeth) throws ParserConfigurationException, SAXException, IOException,InterruptedException {
//		
//		androidElementData elData = new androidElementData();
//		// Login with an existing account
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("appium-version", "1.2");
//		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("platformVersion", "4.4");
//		capabilities.setCapability("deviceName", "Nexus 5");
//		capabilities.setCapability("app","/Users/qa/Desktop/Pogoplug-5.9.0.9-20140722_093521.apk");
//		capabilities.setCapability("appPackage", "com.pogoplug.android");
//		capabilities.setCapability("appWaitActivity","com.pogoplug.android.login.ui.IntroPage");
//		capabilities.setCapability("appActivity","com.pogoplug.android.login.Splash");
//
//		try {
//
////			driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
//			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
//			
//		}
//
//		catch (Exception e) {
//			driver.quit();
//		}
//
//		genMeth.clickId(driver,  genMeth, elData.BTNalreadyHaveAnAccount_id);
//		genMeth.sendId(driver, genMeth, elData.TEXTFIELDemail_id,"meny@cloudengines.com");
//		genMeth.sendId(driver, genMeth, elData.TEXTFIELDpassword_id, "1");
//		genMeth.clickId(driver, genMeth, elData.BTNlogin_id);
//
//		// Make sure that the intro display (that way the swipe will be done at the right time)
//		By by = By.id("com.pogoplug.android:id/protect_computer");
//		String text = "Never Lose a Photo";
//		genMeth.isTextPresentNative(driver, text, by);
//
//		// Navigate through the intro
//		driver.swipe(1031, 1150, 53, 1150, 500);
//		genMeth.clickId(driver, genMeth, elData.BTNfinishTour_id);
//		genMeth.clickId(driver, genMeth, elData.BTNcontinue_id);
//
//		// Make sure that the Login was successful By verifying that the "CATEGORIES" display in the *LSM (Left Side Menu)
//		By byCat = By.xpath("//android.widget.ListView[1]/android.widget.TextView[1]");
//		String cat = "CATEGORIES";
//		genMeth.isTextPresentNative(driver, cat, byCat);
//		return driver;
//
//	}
//
	
	public void killAppIos(IOSDriver driver)throws InterruptedException, IOException {
		//GenericMethods genMeth = new GenericMethods();
		driver.removeApp("com.cloudengines.pogoplug");
		
		try {
			driver.quit();
		} catch (Exception x) {
			// swallow exception
		}
		//driver = genMeth.setCapabilitiesIos();
	
	}
	

	public void signOutFromStartupIphone5(IOSDriver driver, WebElementsIos iosData) throws InterruptedException, IOException {
		GenericMethods genMeth = new GenericMethods();
		genMeth.clickName(genMeth, iosData.Settings_Name);
		driver.scrollToExact(iosData.BTNsignOut_Name);
//		genMeth.scroll(driver, iosData.scrollDown);
//		genMeth.scroll(driver, iosData.scrollDown);
		genMeth.clickName(genMeth, iosData.BTNsignOut_Name);
	}
	
	public void loginIos(GenericMethods genMeth, WebElementsIos iosData, String user)throws InterruptedException, IOException,ParserConfigurationException, SAXException {

		genMeth.clickName(genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, user);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(genMeth, iosData.BTNsignin_Name);

		// check if the tour display (will be display only if it is first login)
		boolean isFirstLogin = genMeth.checkIsElementVisibleNative(driver,By.name(iosData.NeverLoseAPhoto_Name));
		if (isFirstLogin == true) {
			// Make sure that the tour Never lose a photo display properly with full text
			genMeth.isElementVisible(driver,By.name(iosData.NeverLoseAPhoto_Name));
			genMeth.isElementVisible(driver,By.name(iosData.NeverLoseaPhotoFullText_Name));

			driver.swipe(270, 265, 55, 265, 1000);
			// Make sure that the tour Transfer phone simply is displayed properly with full text
			genMeth.isElementVisible(driver,By.name(iosData.TransferPhonesSimply_Name));
			genMeth.isElementVisible(driver,By.name(iosData.TransferPhonesSimplyFullText_Name));
			driver.swipe(270, 265, 55, 265, 1000);
			

			// check if this is a Limited or Unlimited account
			boolean isUnlimitedAccount = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.UnlimitedProtection_Name));

			if (isUnlimitedAccount == true) {
				// Verify that the go unlimited tour text is displayed
				genMeth.isElementVisible(driver,By.name(iosData.UnlimitedProtection_Name));
				genMeth.isElementVisible(driver,By.name(iosData.UpgradeTour_Name));

				// Skip- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
				genMeth.clickName(genMeth, iosData.BTNskip_Name);

				// Verify that the backup tour text is displayed
				genMeth.isElementVisible(driver,By.name(iosData.Backup_Name));
				genMeth.isElementVisible(driver,By.name(iosData.BackupTourText_Name));
				genMeth.clickName(genMeth, iosData.BTNcontinue_Name);

				genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);

				// verify that the home screen is open with the LSM (left side menu)
				genMeth.isElementVisible(driver,By.name(iosData.Settings_Name));
			}
			
			else {
				genMeth.isElementVisible(driver,By.name(iosData.Backup_Name));
				genMeth.clickName(genMeth, iosData.BTNcontinue_Name);
				genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
			}

			// verify that the home screen is open with the LSM (left side menu)
			genMeth.isElementVisible(driver,By.name(iosData.Settings_Name));

		}

	}
  
 
	public IOSDriver setCapabilitiesIos(GenericMethods genMeth)
			throws IOException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName",genMeth.getValueFromPropFile("deviceName"));
		capabilities.setCapability("device",genMeth.getValueFromPropFile("device"));
		capabilities.setCapability("udid", genMeth.getValueFromPropFile("udid"));
		capabilities.setCapability(CapabilityType.VERSION,genMeth.getValueFromPropFile("CapabilityType.VERSION"));
		//capabilities.setCapability(CapabilityType.PLATFORM,genMeth.getValueFromPropFile("CapabilityType.PLATFORM"));
		//capabilities.setCapability("platformName",genMeth.getValueFromPropFile("platformName"));
		capabilities.setCapability("app",genMeth.getValueFromPropFile("pogoplugPath"));

		try {

			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

		}

		catch (MalformedURLException e) {

			genMeth.takeScreenShot(driver, genMeth,"Faliled to open Appium driver");
			org.testng.Assert.fail("WebElement"+ " Faliled to open Appium driver");
		}
		return driver;
	}
	
	
	public WebDriver setCapabilitiesSafari(GenericMethods genMeth)
			throws IOException {
		WebDriver driver1;
		 DesiredCapabilities cap = new DesiredCapabilities();
	     cap.setCapability("app", "safari");
			cap.setCapability("device",genMeth.getValueFromPropFile("device"));
			cap.setCapability("udid", genMeth.getValueFromPropFile("udid"));
			cap.setCapability(CapabilityType.VERSION,genMeth.getValueFromPropFile("CapabilityType.VERSION"));

			cap.setCapability("deviceName",genMeth.getValueFromPropFile("deviceName"));
			cap.setCapability("platformName",genMeth.getValueFromPropFile("platformName"));



		
		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("deviceName",genMeth.getValueFromPropFile("deviceName"));
//		capabilities.setCapability("udid", genMeth.getValueFromPropFile("udid"));
//		capabilities.setCapability(CapabilityType.VERSION,genMeth.getValueFromPropFile("CapabilityType.VERSION"));

	        driver1 = new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), cap);

		return driver1;
	   

	}
	

    

	public IOSDriver cleanLoginIos(GenericMethods genMeth, WebElementsIos iosData, String user) throws InterruptedException, IOException,ParserConfigurationException, SAXException {

		// Login with an existing account
	//	genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		genMeth.clickName(genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, user);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(genMeth, iosData.BTNsignin_Name);
		
		genMeth.isElementVisible(driver,By.name(iosData.NeverLoseAPhoto_Name));

		driver.swipe(270, 265, 55, 265, 500);
		genMeth.isElementVisible(driver,By.name(iosData.TransferPhonesSimply_Name));
		driver.swipe(270, 265, 55, 265, 500);

		// check if this is a Limited or Unlimited account
		boolean isUnlimitedAccount = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.UnlimitedProtection_Name));
		if (isUnlimitedAccount == true) {
			// Verify that the go unlimited tour text is displayed
			genMeth.isElementVisible(driver,By.name(iosData.UnlimitedProtection_Name));
			genMeth.isElementVisible(driver,By.name(iosData.UpgradeTour_Name));

			// Skip- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
			genMeth.clickName(genMeth, iosData.BTNskip_Name);

			// Verify that the backup tour text is displayed
			genMeth.isElementVisible(driver,By.name(iosData.Backup_Name));
			genMeth.isElementVisible(driver,By.name(iosData.BackupTourText_Name));
			genMeth.clickName(genMeth, iosData.BTNcontinue_Name);

			//genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);

			// verify that the home screen is open with the LSM (left side menu)
			genMeth.isElementVisible(driver,By.name(iosData.Settings_Name));
		}

		else {
			genMeth.isElementVisible(driver,By.name(iosData.Backup_Name));
			genMeth.clickName(genMeth, iosData.BTNcontinue_Name);
			genMeth.handleAccessPhotosContactsLocationNotifications(genMeth,  iosData);
		}

		// verify that the home screen is open with the LSM (left side menu)
		//genMeth.handleAccessPhotosContactsLocationNotifications(genMeth,  iosData);
		genMeth.isElementVisible(driver,By.name(iosData.Settings_Name));
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisible(driver,By.name(iosData.Settings_Name));

		return driver;
		
	}
	
    public void scroll(IOSDriver driver , String direction){       
        JavascriptExecutor js= (JavascriptExecutor) driver;
        Map<String, String>scrollMap =new HashMap<String, String>();
        scrollMap.put("direction", direction);  
        js.executeScript("mobile: scroll", scrollMap);  
}
    
    public void scrollUp(IOSDriver driver){       
        JavascriptExecutor js= (JavascriptExecutor) driver;
        Map<String, String>scrollMap =new HashMap<String, String>();
        scrollMap.put("direction", "up");  
        js.executeScript("mobile: scroll", scrollMap);  
}
    
    public void scrollDown(IOSDriver driver){       
        JavascriptExecutor js= (JavascriptExecutor) driver;
        Map<String, String>scrollMap =new HashMap<String, String>();
        scrollMap.put("direction", "down");  
        js.executeScript("mobile: scroll", scrollMap);  
}

	public void signUp(GenericMethods genMeth, WebElementsIos iosData) throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		
		String randomName =  genMeth.randomString();
		String currentDateFolder = genMeth.currentTime();
		
		genMeth.clickName(genMeth, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, "meny@" + randomName + ".com");
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(genMeth, iosData.BTNsignUpForFree_Name);
		
		genMeth.isElementVisible(driver,By.name(iosData.NeverLoseAPhoto_Name));
		driver.swipe(270, 265, 55, 265, 1000);
		genMeth.isElementVisible(driver,By.name(iosData.TransferPhonesSimply_Name));
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisible(driver,By.name(iosData.UnlimitedProtection_Name));
		genMeth.isElementVisible(driver,By.name(iosData.UpgradeTour_Name));
		genMeth.clickName(genMeth, "UIAccessoryButtonX");

		genMeth.isElementVisible(driver,By.name(iosData.Backup_Name));
		
		//Disable the backup from TOUR
		genMeth.clickName(genMeth, iosData.BTNcontinue_Name);
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth,  iosData);
		genMeth.isElementVisible(driver,By.name(iosData.Settings_Name));

	}
	
	
	public String getValueFromPropFile(String key) {
		Properties properties = new Properties();
		String value = "";
		try {
			
			properties.load(getClass().getResourceAsStream("/resources/config.properties"));
			//properties.load(getClass().getResourceAsStream("/resources/webui.properties"));
			{
				value = properties.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}
	

	public void takeScreenShotPositive(IOSDriver driver, GenericMethods genMeth, String imageName) throws IOException {

		File scrFile = (driver.getScreenshotAs(OutputType.FILE));
		String currentTime = genMeth.currentTime();
		String screenshotPath = genMeth.getValueFromPropFile("screenshotPathPositive");
		// Now you can do whatever you need to do with it, for example copy somewhere
		String imagePath = screenshotPath  + currentTime + "_" + imageName + ".JPG";
		FileUtils.copyFile(scrFile, new File(imagePath));

	}
	
	public void takeScreenShot(IOSDriver driver, GenericMethods genMeth, String imageName) throws IOException {

		File scrFile = (driver.getScreenshotAs(OutputType.FILE));
		String currentTime = genMeth.currentTime();
		String screenshotPath = genMeth.getValueFromPropFile("screenshotPath");
		// Now you can do whatever you need to do with it, for example copy somewhere
		String imagePath = screenshotPath  + currentTime + "_" + imageName + ".JPG";
		FileUtils.copyFile(scrFile, new File(imagePath));

	}


	/*
	 * ***************************************************
	 * Web Element Handling *
	 * ***************************************************
	 */

	// ==================== RETURN ELEMENT

		public WebElement returnCss(IOSDriver driver, String cssSelector)
			throws InterruptedException {

		GenericMethods genMeth = new GenericMethods();
		try {

			genMeth.fluentwait(driver, By.cssSelector(cssSelector));

		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement 'by css' can't be located");

		}

		WebElement myElement = genMeth.fluentwait(driver,
				By.cssSelector(cssSelector));
		return myElement;
	}

	public WebElement returnId(IOSDriver driver,GenericMethods genMeth, String id)
			throws InterruptedException {


		try {

			genMeth.fluentwait(driver, By.id(id));

		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver, By.id(id));
		return myElement;

	}

	public WebElement returnClassName(IOSDriver driver, GenericMethods genMeth,  String className)
			throws InterruptedException {


		try {

			genMeth.fluentwait(driver, By.className(className));
		}

		catch (Exception e) {

			org.testng.Assert.fail(className + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver,
				By.className(className));
		return myElement;
	}

	public WebElement returnXpth(IOSDriver driver, GenericMethods genMeth, String xpth)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.xpath(xpth));

		}

		catch (Exception e) {

			org.testng.Assert.fail(xpth + " didn't display");
		}

		WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpth));
		return myElement;

	}

	public WebElement returnName(IOSDriver driver, GenericMethods genMeth, String name)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.name(name));

		}

		catch (Exception e) {

			org.testng.Assert.fail(name + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver, By.name(name));
		return myElement;

	}

	// ========= CLICK an ELEMENT
	// =========================================================================

	public void clickBy(IOSDriver driver, GenericMethods genMeth, By by) throws InterruptedException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			//driver.tap(1, myElement,1000);
			myElement.click();
		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement can't be located");

		}

	}
	
	public void tapBy(IOSDriver driver, GenericMethods genMeth, By by) throws InterruptedException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			driver.tap(1, myElement,1000);
		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement can't be located");

		}

	}

	public void clickCss(IOSDriver driver, GenericMethods genMeth, String cssSelector)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,By.cssSelector(cssSelector));
			driver.tap(1, myElement, 1000);
			//myElement.click();

		}

		catch (Exception e) {

			org.testng.Assert.fail(cssSelector + " didn't display");

		}

	}

	public void clickId(IOSDriver driver, GenericMethods genMeth, String id)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
//			driver.tap(1, myElement, 1000);
			myElement.click();
		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}
	}

	public void tapId(IOSDriver driver, GenericMethods genMeth, String id)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			driver.tap(1, myElement, 1000);
		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}
	}
	public void clickClassName(IOSDriver driver, GenericMethods genMeth, String className)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.className(className));
			driver.tap(1, myElement, 1000);


		}

		catch (Exception e) {

			org.testng.Assert.fail(className + " didn't display");

		}

	}

	public void clickXpth(IOSDriver driver, GenericMethods genMeth, String xpth)
			throws InterruptedException, IOException {

		By by = By.xpath(xpth);
		

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
//			driver.tap(1, myElement, 1000);
			myElement.click();

		}

		catch (Exception e) {
			genMeth.takeScreenShot(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + " didn't display");

		}

	}
	
	public void tapXpth(IOSDriver driver, GenericMethods genMeth, String xpth)
			throws InterruptedException, IOException {

		By by = By.xpath(xpth);
		

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			driver.tap(1, myElement, 1000);

		}

		catch (Exception e) {
			genMeth.takeScreenShot(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + " didn't display");

		}

	}


	public void clickName(GenericMethods genMeth, String name)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.name(name));
//			driver.tap(1, myElement, 1000);
			myElement.click();

		}

		catch (Exception e) {
			
			genMeth.takeScreenShot(driver, genMeth, name);
			org.testng.Assert.fail(name + " didn't display");

		}

	}
	
	
	public void tapName(IOSDriver driver,GenericMethods genMeth, String name)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.name(name));
			driver.tap(1, myElement, 1000);

		}

		catch (Exception e) {
			
			genMeth.takeScreenShot(driver, genMeth, name);
			org.testng.Assert.fail(name + " didn't display");

		}

	}


	// ======================== SEND ELEMENT
	// =========================================================================

	public void sendBy(IOSDriver driver, GenericMethods genMeth, By by, String send)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, send);
			org.testng.Assert.fail("WebElement'send by' can't be located");

		}

	}

	public void sendCss(IOSDriver driver, GenericMethods genMeth, String cssSelector, String send)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.cssSelector(cssSelector));
			myElement.sendKeys(send);
		}

		catch (Exception e) {

			org.testng.Assert.fail("Css selector " + cssSelector
					+ " can't be located");

		}

	}

	public void sendId(IOSDriver driver, GenericMethods genMeth, String id, String send)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, send);
			org.testng.Assert.fail(id + "didn't displayed");

		}

	}

	public void sendClassName(IOSDriver driver, GenericMethods genMeth, String className, String send)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.className(className)).sendKeys(send);

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + "didn't displayed");

		}

	}

	public void sendXpth(IOSDriver driver, GenericMethods genMeth, String xpth, String send)
			throws IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpth));
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + "didn't displayed");

		}

	}

	public void sendName(IOSDriver driver, GenericMethods genMeth, String name, String send)
			throws IOException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(name));
			myElement.sendKeys(send);
		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, name);
			org.testng.Assert.fail(name + "didn't displayed");

		}

	}

	// =========================Clear WebElements========================================

	public void clearXpth(IOSDriver driver, GenericMethods genMeth, String xpath)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpath));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(xpath + "didn't displayed");

		}

	}

	public void clearClassName(IOSDriver driver, GenericMethods genMeth, String className)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.className(className));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + "didn't displayed");

		}

	}

	public void clearId(IOSDriver driver, GenericMethods genMeth, String id)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(id + "didn't displayed");

		}

	}

	public void clearCss(IOSDriver driver, GenericMethods genMeth, String cssSelector)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.cssSelector(cssSelector));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(cssSelector + " can't be located");
		}

	}

	/*
	 * ******************************************************************************
	 * Avoid the Element not found exception *
	 * ***********************************
	 * *******************************************
	 */

	// Look for an element in a few tries (with counter)
	public void waitForElementToBeInvisible(IOSDriver driver, By byType,
			int numAttempts) throws IOException, ParserConfigurationException,SAXException {

		int count = 0;
		Boolean isInvisible = false;
		while (count < numAttempts) {

			try {
				isInvisible = new FluentWait<IOSDriver>(driver)
						.withTimeout(60, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class)
						.until(ExpectedConditions
								.invisibilityOfElementLocated(byType));

				if (isInvisible == true) {

					count = numAttempts;

				}

			}

			catch (Exception e) {
				count++;

			}

		}

		if (isInvisible == false) {
			GenericMethods genMeth = new GenericMethods();
			// str = new genData();
			String imageName = "Element isn't Invisible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not Invisible");
		}

	}

	public void waitForElementToBeVisible(IOSDriver driver, By byType,
			int numAttempts) throws IOException, ParserConfigurationException,
			SAXException {

		int count = 0;
		WebElement elementToBeVisible = null;
		while (count < numAttempts) {
			try {
				elementToBeVisible = new FluentWait<IOSDriver>(driver)
						.withTimeout(60, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class)
						.until(ExpectedConditions.elementToBeClickable(byType));

				if (elementToBeVisible != null) {

					count = numAttempts;

				}

			}

			catch (Exception e) {
				count++;

			}

		}

		if (elementToBeVisible == null) {
			GenericMethods genMeth = new GenericMethods();
			String imageName = "Element isn't Visible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not Visible");
		}

	}

	public WebElement fluentwait(IOSDriver driver, final By byType) {
		Wait<IOSDriver> wait = new FluentWait<IOSDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<IOSDriver, WebElement>() {
			public WebElement apply(IOSDriver driver) {
				return driver.findElement(byType);
			}
		});

		wait.until(ExpectedConditions.elementToBeClickable(byType));

		return foo;
	}

	public void isTextPresentNative(IOSDriver driver, String text, By by)
			throws IOException, ParserConfigurationException, SAXException,
			InterruptedException {

		// boolean isStartUpPageOpenIOS = false;

		try {
			new FluentWait<IOSDriver>(driver)
					.withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.textToBePresentInElementLocated(
							by, text));
		}

		catch (Exception e) {

			GenericMethods genMeth = new GenericMethods();
			// genData str = new genData();
			String imageName = text + " is invisible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail(text + " isn't visible");
		}

		// return isStartUpPageOpenIOS;

	}

	public boolean checkIsTextPresentNative(IOSDriver driver, String text,
			By by) throws IOException, ParserConfigurationException,SAXException, InterruptedException {

		boolean isTextPresent = false;

		try {
			isTextPresent = new FluentWait<IOSDriver>(driver)
					.withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
		}

		catch (Exception e) {

			// nothing to do here
		}

		return isTextPresent;

	}

	// This method checks if a given element is invisible on the screen

	public void isElementInvisible(IOSDriver driver, By By)
			throws ParserConfigurationException, SAXException, IOException {

		try {

			(new WebDriverWait(driver, 45)).until(ExpectedConditions.invisibilityOfElementLocated(By));

		}

		catch (Exception e) {

			GenericMethods genMeth = new GenericMethods();
			String imageName = " Element is visible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " still visible");

		}

	}
	
	public void isElementInvisibleTextNative(IOSDriver driver, By by,
			String text) throws ParserConfigurationException, SAXException,
			IOException {

		try {

			(new WebDriverWait(driver, 45)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));

		}

		catch (Exception e) {

			GenericMethods genMeth = new GenericMethods();
			String imageName = text + " still visible ";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail(text + " still visible");

		}

	}

	public void isElementVisible(IOSDriver driver, By By)
			throws ParserConfigurationException, SAXException, IOException {

		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			new FluentWait<IOSDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By));

		}

		catch (Exception e) {
			GenericMethods genMeth = new GenericMethods();
			String imageName = "Element is invisible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not visible");

		}

	}

	public boolean checkIsElementVisibleNative(IOSDriver driver, By by)
			throws ParserConfigurationException, SAXException, IOException {

		boolean isWebElementVisible = false;
		WebElement element = null;
		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			element = new FluentWait<IOSDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));

		}

		catch (Exception e) {

			// GenericMethods genMeth = new GenericMethods();
			// genData str = new genData();
			// String imageName = str.screenShotPath + " Element is invisible "
			// + genMeth.currentTime() + ".png";
			// genMeth.takeScreenShotNative(driver, imageName );
			// org.testng.Assert.fail("WebElement" + " is not visible");

		}
		if (element != null) {
			return isWebElementVisible = true;
		}

		else {
			return isWebElementVisible;

		}

	}

	
	public final class SessionIdentifierGenerator {
		private SecureRandom random = new SecureRandom();

		public String nextSessionId() {

			return new BigInteger(130, random).toString(32);

		}

	};

	// Creates a Random string
	public String randomString() {

		String text;
		SessionIdentifierGenerator x = new SessionIdentifierGenerator();
		text = x.nextSessionId();
		return text;
	}

	// Create a string with current date
	public String currentDate() {

		String curDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		return curDate;
	}

	public String currentTime() {

		// String curDate = new
		// SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		return curDate;
	}


	public void backgroundToForeground(IOSDriver driver, int numOfTimes) {

		for (int count = 0; count < numOfTimes; count++) {

			driver.runAppInBackground(2);

		}

	}

	public void lockUnlock(IOSDriver driver, int numOfTimes) {

		for (int count = 0; count < numOfTimes; count++) {

			driver.lockScreen(2);

		}

	}

	public void handleAccessPhotosContactsLocationNotifications(GenericMethods genMeth, WebElementsIos iosData)
			throws IOException, ParserConfigurationException, SAXException,InterruptedException {
		
		// check if the "“Pogoplug” Would Like to Access Your Contacts" popup is displayed
		boolean isContactsAccessPopupDisplay = genMeth.checkIsTextPresentNative(driver,iosData.AccessContactsWarning_Name,
						By.name(iosData.AccessContactsWarning_Name));

		if (isContactsAccessPopupDisplay == true) {

			genMeth.clickName(genMeth, iosData.BTNok_Name);
		}

		// check if the "“Pogoplug” Would Like to Access Your Photos" popup is displayed
		boolean isPhotosAccessPopupDisplay = genMeth.checkIsTextPresentNative(driver,iosData.AccessToPhotos,
				By.name(iosData.AccessToPhotos));

		if (isPhotosAccessPopupDisplay == true) {

			genMeth.clickName(genMeth, iosData.BTNok_Name);
		}
		
		// check if the current location popup is displayed
		boolean isLocationPopupDisplay = genMeth.checkIsTextPresentNative(
				driver, iosData.AccessLocationServicesWarning_Name,
				By.name(iosData.AccessLocationServicesWarning_Name));

		if (isLocationPopupDisplay == true) {

			genMeth.clickName(genMeth, iosData.BTNok_Name);

		}
		
		// check if the current Notification popup is displayed
				boolean isNotificationPopupDisplay = genMeth.checkIsTextPresentNative(
						driver, "“Pogoplug” Would Like to Send You Notifications",
						By.name("“Pogoplug” Would Like to Send You Notifications"));

				if (isNotificationPopupDisplay == true) {

					genMeth.clickName(genMeth, iosData.BTNok_Name);

				}
		
		
		


	}
	
	public void deletList(GenericMethods genMeth, WebElementsIos iosData) 
			throws ParserConfigurationException, SAXException, IOException,
			InterruptedException {
		boolean isListEmpty = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.EmptyFolder_Name));
		while (isListEmpty == false) {

			// delete the first row
			genMeth.clickName( genMeth, iosData.BTNedit_Name);
			genMeth.clickXpth(driver, genMeth,
					"//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]");
			genMeth.clickName( genMeth, iosData.BTNdeleteOn_Name);
			genMeth.clickName( genMeth, iosData.BTNdelete_Name);
			isListEmpty = genMeth.checkIsElementVisibleNative(driver,
					By.name(iosData.EmptyFolder_Name));
			if (isListEmpty == false) {

				genMeth.clickName( genMeth, iosData.BTNdone_Name);

			}

		}
	}
	
	public void refreshIphone5(){
		
		driver.swipe(150, 150, 150, 450, 500);

	}
	
	
}


/*
 * 
 * ==============================================================================
 * ==================================================
 * 
 * 
 * 
 * 
 * //Switch to iframe & clicking an web element public void swithchToIframe
 * (WebDriver driver, String iframe , String ById) throws InterruptedException{
 * GenericMethods genMeth = new GenericMethods();
 * driver.switchTo().frame(driver.findElement(By.id(iframe)));
 * genMeth.classNameAndClick(driver, ById);
 * 
 * driver.switchTo().defaultContent(); };
 * 
 * public void switchToIfmare1(By by){
 * 
 * driver.switchTo().frame(driver.findElement(by));
 * 
 * }
 * 
 * public void switchBackToDefaultContent(WebDriver driver){
 * 
 * driver.switchTo().defaultContent();
 * 
 * }
 * 
 * public void moveMouseToelement(WebDriver driver , By by){ Actions act = new
 * Actions(driver); WebElement mainMenu = driver.findElement(by);
 * act.moveToElement(mainMenu).build().perform(); }
 */

/*
private String getFileName(String nameTest) throws IOException {
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
	Date date = new Date();
	return dateFormat.format(date) + "_" + nameTest + ".png";
}

private String getPath(String nameTest) throws IOException {
	File directory = new File(".");
	File dir = new File("/test-output/reports/screenshots/");
	if (!dir.exists()) {
		dir.mkdir();
	}
	String newFileNamePath = directory.getCanonicalPath() + "/test-output/reports/screenshots/" + getFileName(nameTest);
	return newFileNamePath;
}
*/

