package com.pp.ios.auto;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
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

	AppiumDriver driver;

	public AppiumDriver loginNativeAndroid(GenericMethods genMeth) throws ParserConfigurationException, SAXException, IOException,InterruptedException {
		
		androidElementData elData = new androidElementData();
		// Login with an existing account
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "Nexus 5");
		capabilities.setCapability("app","/Users/qa/Desktop/Pogoplug-5.9.0.9-20140722_093521.apk");
		capabilities.setCapability("appPackage", "com.pogoplug.android");
		capabilities.setCapability("appWaitActivity","com.pogoplug.android.login.ui.IntroPage");
		capabilities.setCapability("appActivity","com.pogoplug.android.login.Splash");

		try {

			driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		}

		catch (Exception e) {
			driver.quit();
		}

		genMeth.clickId(driver,  genMeth, elData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, elData.TEXTFIELDemail_id,"meny@cloudengines.com");
		genMeth.sendId(driver, genMeth, elData.TEXTFIELDpassword_id, "1");
		genMeth.clickId(driver, genMeth, elData.BTNlogin_id);

		// Make sure that the intro display (that way the swipe will be done at the right time)
		By by = By.id("com.pogoplug.android:id/protect_computer");
		String text = "Never Lose a Photo";
		genMeth.isTextPresentNative(driver, text, by);

		// Navigate through the intro
		driver.swipe(1031, 1150, 53, 1150, 500);
		genMeth.clickId(driver, genMeth, elData.BTNfinishTour_id);
		genMeth.clickId(driver, genMeth, elData.BTNcontinue_id);

		// Make sure that the Login was successful By verifying that the "CATEGORIES" display in the *LSM (Left Side Menu)
		By byCat = By.xpath("//android.widget.ListView[1]/android.widget.TextView[1]");
		String cat = "CATEGORIES";
		genMeth.isTextPresentNative(driver, cat, byCat);
		return driver;

	}

	
	public AppiumDriver killAppIos(AppiumDriver driver)throws InterruptedException, IOException {
		//GenericMethods genMeth = new GenericMethods();
		driver.removeApp("com.cloudengines.pogoplug");
		try {
			driver.quit();
		} catch (Exception x) {
			// swallow exception
		}
		//driver = genMeth.setCapabilitiesIos();
		return driver;
	}
	

	public void signOutFromStartupIphone5(AppiumDriver driver, webElementsIos iosData) throws InterruptedException, IOException {
		GenericMethods genMeth = new GenericMethods();
		genMeth.clickName(driver,genMeth, iosData.Settings_Name);
		driver.swipe(170, 350, 170, 150, 500);
		Thread.sleep(1000);
		driver.swipe(170, 450, 170, 250, 500);
		driver.swipe(170, 550, 170, 350, 500);
		genMeth.clickName(driver,genMeth, iosData.BTNsignOut_Name);
	}
	
	public void loginIos(GenericMethods genMeth, webElementsIos iosData, String user)throws InterruptedException, IOException,ParserConfigurationException, SAXException {

		genMeth.clickName(driver,genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, user);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver,genMeth, iosData.BTNsignin_Name);

		// check if the tour display (will be display only if it is first login)
		boolean isFirstLogin = genMeth.checkIsElementVisibleNative(driver,By.name(iosData.NeverLoseAPhoto_Name));
		if (isFirstLogin == true) {
			// Make sure that the tour Never lose a photo display properly with full text
			genMeth.isElementVisibleNative(driver,By.name(iosData.NeverLoseAPhoto_Name));
			genMeth.isElementVisibleNative(driver,By.name(iosData.NeverLoseaPhotoFullText_Name));

			driver.swipe(270, 265, 55, 265, 1000);
			// Make sure that the tour Transfer phone simply is displayed properly with full text
			genMeth.isElementVisibleNative(driver,By.name(iosData.TransferPhonesSimply_Name));
			genMeth.isElementVisibleNative(driver,By.name(iosData.TransferPhonesSimplyFullText_Name));
			driver.swipe(270, 265, 55, 265, 1000);

			// check if this is a Limited or Unlimited account
			boolean isUnlimitedAccount = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.UnlimitedProtection_Name));

			if (isUnlimitedAccount == true) {
				// Verify that the go unlimited tour text is displayed
				genMeth.isElementVisibleNative(driver,By.name(iosData.UnlimitedProtection_Name));
				genMeth.isElementVisibleNative(driver,By.name(iosData.UpgradeTour_Name));

				// Skip- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
				genMeth.clickName(driver,genMeth, iosData.BTNskip_Name);

				// Verify that the backup tour text is displayed
				genMeth.isElementVisibleNative(driver,By.name(iosData.Backup_Name));
				genMeth.isElementVisibleNative(driver,By.name(iosData.BackupTourText_Name));
				genMeth.clickName(driver,genMeth, iosData.BTNcontinue_Name);

				genMeth.handleAccessPhotosContactsLocation(genMeth, iosData);

				// verify that the home screen is open with the LSM (left side menu)
				genMeth.isElementVisibleNative(driver,By.name(iosData.Settings_Name));
			}
			
			else {
				genMeth.isElementVisibleNative(driver,By.name(iosData.Backup_Name));
				genMeth.clickName(driver,genMeth, iosData.BTNcontinue_Name);
				genMeth.handleAccessPhotosContactsLocation(genMeth, iosData);
			}

			// verify that the home screen is open with the LSM (left side menu)
			genMeth.isElementVisibleNative(driver,By.name(iosData.Settings_Name));

		}

	}

	public AppiumDriver setCapabilitiesIos(GenericMethods genMeth, webElementsIos iosData, String platform) throws IOException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (platform == iosData.platformIphone) {
			capabilities.setCapability("deviceName", "Automation test2");
			capabilities.setCapability("device", "iPhone 5");
			// capabilities.setCapability("deviceName", "iPhone Simulator");
			capabilities.setCapability("udid","dc32ad3627707abcf57c9844d4ed95e4c212e5a9");
			capabilities.setCapability(CapabilityType.VERSION, "8.1");
			capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("app","/Users/qa/Desktop/Appium/Pogoplug.app");
		}
		else if (platform == iosData.platforSimulatorIphone) {
			capabilities.setCapability("deviceName", "Automation test2");
			capabilities.setCapability("device", "iPhone 5");
			capabilities.setCapability("deviceName", "iPhone Simulator");
			//capabilities.setCapability("udid","dc32ad3627707abcf57c9844d4ed95e4c212e5a9");
			capabilities.setCapability(CapabilityType.VERSION, "8.1");
			capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
			capabilities.setCapability("platformName", "iOS");
			// capabilities.setCapability("app","/Users/qa/Desktop/Appium/Pogoplug.app");
			capabilities.setCapability("app","/Users/pogoplug/Appium/Pogoplug.app");

		}
		
		try {

			driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);
		}

		catch (MalformedURLException e) {

			genMeth.takeScreenShotNative(driver, genMeth,"Faliled to open Appium driver");
			org.testng.Assert.fail("WebElement" + " Faliled to open Appium driver");
		}
		return driver;
	}


	public AppiumDriver cleanLoginIos(AppiumDriver driver, GenericMethods genMeth, webElementsIos iosData, String user) throws InterruptedException, IOException,ParserConfigurationException, SAXException {

		/*
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Automation test2");
		capabilities.setCapability("device", "iPhone 5");
		capabilities.setCapability("udid","dc32ad3627707abcf57c9844d4ed95e4c212e5a9");

		capabilities.setCapability(CapabilityType.VERSION, "8.1");
		capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("app","/Users/qa/Desktop/Appium/Pogoplug.app");
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		*/
		
		//driver = genMeth.setCapabilitiesIos();

		// Login with an existing account
		genMeth.clickName(driver,genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, user);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver,genMeth, iosData.BTNsignin_Name);

		// Make sure that the intro display (that way the swipe will be done at the right time)
		genMeth.isElementVisibleNative(driver,By.name(iosData.NeverLoseAPhoto_Name));
		driver.swipe(270, 265, 55, 265, 500);
		genMeth.isElementVisibleNative(driver,By.name(iosData.TransferPhonesSimply_Name));
		driver.swipe(270, 265, 55, 265, 500);

		// check if this is a Limited or Unlimited account
		boolean isUnlimitedAccount = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.UnlimitedProtection_Name));
		if (isUnlimitedAccount == true) {
			// Verify that the go unlimited tour text is displayed
			genMeth.isElementVisibleNative(driver,By.name(iosData.UnlimitedProtection_Name));
			genMeth.isElementVisibleNative(driver,By.name(iosData.UpgradeTour_Name));

			// Skip- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
			genMeth.clickName(driver,genMeth, iosData.BTNskip_Name);

			// Verify that the backup tour text is displayed
			genMeth.isElementVisibleNative(driver,By.name(iosData.Backup_Name));
			genMeth.isElementVisibleNative(driver,By.name(iosData.BackupTourText_Name));
			genMeth.clickName(driver,genMeth, iosData.BTNcontinue_Name);

			genMeth.handleAccessPhotosContactsLocation(genMeth, iosData);

			// verify that the home screen is open with the LSM (left side menu)
			genMeth.isElementVisibleNative(driver,By.name(iosData.Settings_Name));
		}

		else {
			genMeth.isElementVisibleNative(driver,By.name(iosData.Backup_Name));
			genMeth.clickName(driver,genMeth, iosData.BTNcontinue_Name);
			genMeth.handleAccessPhotosContactsLocation(genMeth,  iosData);
		}

		// verify that the home screen is open with the LSM (left side menu)
		genMeth.handleAccessPhotosContactsLocation(genMeth,  iosData);
		genMeth.isElementVisibleNative(driver,By.name(iosData.Settings_Name));
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisibleNative(driver,By.name(iosData.Settings_Name));

		return driver;
		
	}

	public void signUp(GenericMethods genMeth, webElementsIos iosData) throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		
		String randomName =  genMeth.randomString();
		String currentDateFolder = genMeth.currentTime();
		
		genMeth.clickName(driver,genMeth, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, "meny@" + randomName + ".com");
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver,genMeth, iosData.BTNsignUpForFree_Name);
		
		genMeth.isElementVisibleNative(driver,By.name(iosData.NeverLoseAPhoto_Name));
		driver.swipe(270, 265, 55, 265, 1000);
		genMeth.isElementVisibleNative(driver,By.name(iosData.TransferPhonesSimply_Name));
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative(driver,By.name(iosData.UnlimitedProtection_Name));
		genMeth.isElementVisibleNative(driver,By.name(iosData.UpgradeTour_Name));
		genMeth.clickName(driver,genMeth, "UIAccessoryButtonX");

		genMeth.isElementVisibleNative(driver,By.name(iosData.Backup_Name));
		
		//Disable the backup from TOUR
		genMeth.clickName(driver,genMeth, iosData.BTNcontinue_Name);
		genMeth.handleAccessPhotosContactsLocation(genMeth,  iosData);
		genMeth.isElementVisibleNative(driver,By.name(iosData.Settings_Name));

	}
	
	/*
	public String getValueFromPropFile(String key) {
		Properties properties = new Properties();
		String value = "";
		try {
			properties.load(getClass().getResourceAsStream("/resources/webui.properties"));
			{
				value = properties.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}
	*/

	public void takeScreenShotNative(AppiumDriver driver, GenericMethods genMeth, String imageName) throws IOException {

		File scrFile = (driver.getScreenshotAs(OutputType.FILE));
		String currentTime = genMeth.currentTime();

		// Now you can do whatever you need to do with it, for example copy somewhere
		String imagePath = "/Users/qa/cloudengines-iosautomation/test-output/screenshots/" + currentTime + "_" + imageName + ".JPG";
		FileUtils.copyFile(scrFile, new File(imagePath));

	}


	public elementData elementInit() throws ParserConfigurationException,SAXException, IOException {
		elementData element = new elementData();
		return element;
	}

	public webElementsIos iOSelementInit(String lang, String xmlPath) throws Exception,
			Throwable {
		// According "lang" will determine for which language it will be initialized

		// if ()
		webElementsIos element = new webElementsIos(lang, xmlPath);
		return element;

	}

	public androidElementData androidElementInit() throws ParserConfigurationException, SAXException, IOException {
		androidElementData element = new androidElementData();
		return element;
	}

	public genData genericDataInit() throws ParserConfigurationException,SAXException, IOException {
		genData genD = new genData();
		return genD;
	}

	/*
	 * ***************************************************
	 * Web Element Handling *
	 * ***************************************************
	 */

	// ==================== RETURN ELEMENT

		public WebElement returnCss(AppiumDriver driver, String cssSelector)
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

	public WebElement returnId(AppiumDriver driver,GenericMethods genMeth, String id)
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

	public WebElement returnClassName(AppiumDriver driver, GenericMethods genMeth,  String className)
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

	public WebElement returnXpth(AppiumDriver driver, GenericMethods genMeth, String xpth)
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

	public WebElement returnName(AppiumDriver driver, GenericMethods genMeth, String name)
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

	public void clickBy(AppiumDriver driver, GenericMethods genMeth, By by) throws InterruptedException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.click();
		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement can't be located");

		}

	}

	public void clickCss(AppiumDriver driver, GenericMethods genMeth, String cssSelector)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.cssSelector(cssSelector));
			myElement.click();

		}

		catch (Exception e) {

			org.testng.Assert.fail(cssSelector + " didn't display");

		}

	}

	public void clickId(AppiumDriver driver, GenericMethods genMeth, String id)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.click();
		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}
	}

	public void clickClassName(AppiumDriver driver, GenericMethods genMeth, String className)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.className(className)).click();

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + " didn't display");

		}

	}

	public void clickXpth(AppiumDriver driver, GenericMethods genMeth, String xpth)
			throws InterruptedException, IOException {

		By by = By.xpath(xpth);

		// (new WebDriverWait(driver,
		// 50000)).until(ExpectedConditions.visibilityOfElementLocated(by));

		// WebElement my1Element = genMeth.fluentwait(driver, By.xpath(xpth));

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.click();

		}

		catch (Exception e) {
			genMeth.takeScreenShotNative(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + " didn't display");

		}

	}

	public void clickName(AppiumDriver driver,GenericMethods genMeth, String name)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.name(name));
			myElement.click();

		}

		catch (Exception e) {
			// String testName = new
			// Object(){}.getClass().getEnclosingMethod().getName();
			genMeth.takeScreenShotNative(driver, genMeth, name);
			org.testng.Assert.fail(name + " didn't display");

		}

	}

	// ======================== SEND ELEMENT
	// =========================================================================

	public void sendBy(AppiumDriver driver, GenericMethods genMeth, By by, String send)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShotNative(driver, genMeth, send);
			org.testng.Assert.fail("WebElement'send by' can't be located");

		}

	}

	public void sendCss(AppiumDriver driver, GenericMethods genMeth, String cssSelector, String send)
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

	public void sendId(AppiumDriver driver, GenericMethods genMeth, String id, String send)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShotNative(driver, genMeth, send);
			org.testng.Assert.fail(id + "didn't displayed");

		}

	}

	public void sendClassName(AppiumDriver driver, GenericMethods genMeth, String className, String send)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.className(className)).sendKeys(send);

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + "didn't displayed");

		}

	}

	public void sendXpth(AppiumDriver driver, GenericMethods genMeth, String xpth, String send)
			throws IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpth));
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShotNative(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + "didn't displayed");

		}

	}

	public void sendName(AppiumDriver driver, GenericMethods genMeth, String name, String send)
			throws IOException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(name));
			myElement.sendKeys(send);
		}

		catch (Exception e) {

			genMeth.takeScreenShotNative(driver, genMeth, name);
			org.testng.Assert.fail(name + "didn't displayed");

		}

	}

	// =========================Clear
	// WebElements=====================================================================

	public void clearXpth(AppiumDriver driver, GenericMethods genMeth, String xpath)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpath));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(xpath + "didn't displayed");

		}

	}

	public void clearClassName(AppiumDriver driver, GenericMethods genMeth, String className)
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

	public void clearId(AppiumDriver driver, GenericMethods genMeth, String id)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(id + "didn't displayed");

		}

	}

	public void clearCss(AppiumDriver driver, GenericMethods genMeth, String cssSelector)
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
	public void waitForElementToBeInvisible(AppiumDriver driver, By byType,
			int numAttempts) throws IOException, ParserConfigurationException,SAXException {

		int count = 0;
		Boolean isInvisible = false;
		while (count < numAttempts) {

			try {
				isInvisible = new FluentWait<AppiumDriver>(driver)
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
			genMeth.takeScreenShotNative(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not Invisible");
		}

	}

	public void waitForElementToBeVisible(AppiumDriver driver, By byType,
			int numAttempts) throws IOException, ParserConfigurationException,
			SAXException {

		int count = 0;
		WebElement elementToBeVisible = null;
		while (count < numAttempts) {
			try {
				elementToBeVisible = new FluentWait<AppiumDriver>(driver)
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
			genMeth.takeScreenShotNative(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not Visible");
		}

	}

	public WebElement fluentwait(AppiumDriver driver, final By byType) {
		Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)
				.withTimeout(45, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<AppiumDriver, WebElement>() {
			public WebElement apply(AppiumDriver driver) {
				return driver.findElement(byType);
			}
		});

		wait.until(ExpectedConditions.elementToBeClickable(byType));

		return foo;
	}

	public void isTextPresentNative(AppiumDriver driver, String text, By by)
			throws IOException, ParserConfigurationException, SAXException,
			InterruptedException {

		// boolean isStartUpPageOpenIOS = false;

		try {
			new FluentWait<AppiumDriver>(driver)
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
			genMeth.takeScreenShotNative(driver, genMeth, imageName);
			org.testng.Assert.fail(text + " isn't visible");
		}

		// return isStartUpPageOpenIOS;

	}

	public boolean checkIsTextPresentNative(AppiumDriver driver, String text,
			By by) throws IOException, ParserConfigurationException,SAXException, InterruptedException {

		boolean isTextPresent = false;

		try {
			isTextPresent = new FluentWait<AppiumDriver>(driver)
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

	public void isElementInvisibleNative(AppiumDriver driver, By by)
			throws ParserConfigurationException, SAXException, IOException {

		try {

			(new WebDriverWait(driver, 45)).until(ExpectedConditions
					.invisibilityOfElementLocated(by));

		}

		catch (Exception e) {

			GenericMethods genMeth = new GenericMethods();
			String imageName = " Element is visible";
			genMeth.takeScreenShotNative(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " still visible");

		}

	}

	public void isElementVisibleNative(AppiumDriver driver, By by)
			throws ParserConfigurationException, SAXException, IOException {

		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			new FluentWait<AppiumDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));

		}

		catch (Exception e) {
			GenericMethods genMeth = new GenericMethods();
			String imageName = "Element is invisible";
			genMeth.takeScreenShotNative(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not visible");

		}

	}

	public boolean checkIsElementVisibleNative(AppiumDriver driver, By by)
			throws ParserConfigurationException, SAXException, IOException {

		boolean isWebElementVisible = false;
		WebElement element = null;
		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			element = new FluentWait<AppiumDriver>(driver)
					.withTimeout(5, TimeUnit.SECONDS)
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

	public void isElementInvisibleTextNative(AppiumDriver driver, By by, String text) throws ParserConfigurationException,
			SAXException, IOException {

		try {

			(new WebDriverWait(driver, 45)).until(ExpectedConditions
					.invisibilityOfElementWithText(by, text));

		}

		catch (Exception e) {

			GenericMethods genMeth = new GenericMethods();
			genData str = new genData();
			String imageName = str.screenShotPath + text + " still visible "
					+ genMeth.currentTime() + ".png";
			genMeth.takeScreenShotNative(driver, genMeth, imageName);
			org.testng.Assert.fail(text + " still visible");

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


	public void backgroundToForeground(AppiumDriver driver, int numOfTimes) {

		for (int count = 0; count < numOfTimes; count++) {

			driver.runAppInBackground(2);

		}

	}

	public void lockUnlock(AppiumDriver driver, int numOfTimes) {

		for (int count = 0; count < numOfTimes; count++) {

			driver.lockScreen(2);

		}

	}

	public void handleAccessPhotosContactsLocation(GenericMethods genMeth, webElementsIos iosData)
			throws IOException, ParserConfigurationException, SAXException,InterruptedException {
		
		// check if the "“Pogoplug” Would Like to Access Your Contacts" popup is displayed
		boolean isContactsAccessPopupDisplay = genMeth.checkIsTextPresentNative(driver,iosData.AccessContactsWarning_Name,
						By.name(iosData.AccessContactsWarning_Name));

		if (isContactsAccessPopupDisplay == true) {

			genMeth.clickName(driver,genMeth, iosData.BTNok_Name);
		}

		// check if the "“Pogoplug” Would Like to Access Your Photos" popup is displayed
		boolean isPhotosAccessPopupDisplay = genMeth.checkIsTextPresentNative(driver,iosData.AccessToPhotos,
				By.name(iosData.AccessToPhotos));

		if (isPhotosAccessPopupDisplay == true) {

			genMeth.clickName(driver,genMeth, iosData.BTNok_Name);
		}
		
		// check if the current location popup is displayed
		boolean isLocationPopupDisplay = genMeth.checkIsTextPresentNative(
				driver, iosData.AccessLocationServicesWarning_Name,
				By.name(iosData.AccessLocationServicesWarning_Name));

		if (isLocationPopupDisplay == true) {

			genMeth.clickName(driver,genMeth, iosData.BTNok_Name);

		}

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

