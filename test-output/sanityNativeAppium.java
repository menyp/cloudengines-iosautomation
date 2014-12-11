

/*
package com.pp.ios.auto;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class sanityNativeAppium {

public AppiumDriver driver;
GenericMethods genMeth = new GenericMethods();
 

@BeforeSuite(alwaysRun = true)
public void setupBeforeSuite(ITestContext context) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

	driver = genMeth.loginNativeAndroid(genMeth);
	Thread.sleep(1000);
}

@BeforeMethod (alwaysRun = false)
public void checkHomeScreen() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
// Check if the client still logged in before each test (if not login to the client)
	
	By byCat = By.xpath("//android.widget.ListView[1]/android.widget.TextView[1]");
	String cat = "CATEGORIES";
	boolean loggedin= genMeth.checkIsTextPresentNative(driver ,cat, byCat);
	if (loggedin != true){
		driver = genMeth.loginNativeAndroid(genMeth);
		
	}
}

@Test (enabled = false , testName="Sanity Tests",  description = "Test the Login with Android" , groups= {"Sanity Native Android"})	
public void testLogin() throws ParserConfigurationException, SAXException, IOException, InterruptedException{	
	
	androidElementData elData = genMeth.androidElementInit();
//	  	genData strInf = genMeth.genericDataInit();
	genMeth.clickId(driver, genMeth, elData.BTNalreadyHaveAnAccount_id);
	//genMeth.findByIdAndSend(driver, "com.pogoplug.android:id/email", "meny@cloudengines.com");
	genMeth.sendId(driver, genMeth, elData.TEXTFIELDemail_id, "meny@cloudengines.com");
   // genMeth.findByIdAndSend(driver, "com.pogoplug.android:id/password", "1");
	genMeth.sendId(driver, genMeth, elData.TEXTFIELDpassword_id, "1");
    //genMeth.idAndClick(driver, "com.pogoplug.android:id/loginBtn");
	genMeth.clickId(driver, genMeth, elData.BTNlogin_id);


//	genMeth.isTextPresent("Never Lose a Photo", By.xpath("//android.support.v4.view.ViewPager[1]/android.widget.TextView[1]"));
// Make sure that the intro display (that way the swipe will be done at the right time)
	By by = By.id("com.pogoplug.android:id/protect_computer");
	String text = "Never Lose a Photo";
	genMeth.isTextPresentNative(driver , text, by);
	
	
// Navigate through the intro
	driver.swipe(1031, 1150, 53, 1150, 500);
	//driver.executeScript("mobile: swipe", new HashMap<String, Double>() {{ put("touchCount", (double) 1); put("startX", (double) 1031); put("startY", (double) 1150); put("endX", (double) 53); put("endY", (double) 1159); put("duration", 0.5); }});
	genMeth.clickId(driver, genMeth, elData.BTNfinishTour_id);
	genMeth.clickId(driver, genMeth, elData.BTNcontinue_id);
  
// Make sure that the Login was successful By verifying that the "CATEGORIES" display in the *LSM (Left Side Menu)
    By by1 = By.xpath("//android.widget.ListView[1]/android.widget.TextView[1]");
	String text1 = "CATEGORIES";
	genMeth.isTextPresentNative(driver ,text1, by1);

// Close the left side menu	
	genMeth.clickName(driver, genMeth, "Files, Navigate up");
		
}
*/

//@Test (enabled = true , description = "Test the Create folder with Android" , groups= {"Sanity Native Android"}  /*dependsOnMethods={"testLogin"}*/)	
/*
public void testCreatefolder() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
	
	Thread.sleep(2000);
	
	androidElementData elData = genMeth.androidElementInit();
	//genMeth.idAndClick(driver, elData.BTNalreadyHaveAnAccount_id);
	String currentDate = genMeth.currentTime();
// open pogoplug cloud & press the PP cloud folder
	//Thread.sleep(2000);
	genMeth.clickXpth(driver, genMeth, "");
//	genMeth.clickXpth(driver, genMeth, "//android.widget.ListView[1]/android.widget.LinearLayout[3]");

	Thread.sleep(2000);
	genMeth.clickXpth(driver, genMeth, elData.LIST1_xpth);
	By byNewFolder = By.xpath(elData.LIST5_xpth);
	
//	Create a new folder 
	genMeth.clickName(driver, genMeth, elData.BTNnewFolder_name);
	
// 	Dismiss the create folder window
	genMeth.sendXpth(driver, genMeth, "//android.widget.EditText[1]", currentDate);
	genMeth.clickId(driver, genMeth, elData.BTNdismissNewFolder_id);

// 	make sure that the folder wasn't created
	genMeth.isElementInvisibleNative(driver, byNewFolder);
	
//	Now Press the create folder
	genMeth.clickName(driver, genMeth, elData.BTNnewFolder_name);
	genMeth.sendXpth(driver, genMeth, "//android.widget.EditText[1]", currentDate);
	genMeth.clickId(driver, genMeth, elData.BTNcreateNewFolder_id);
	
//	Check if the folder was created successfully 
	genMeth.isElementVisibleNative(driver, byNewFolder);
	Thread.sleep(2000);
	
// sort the list in order to place the image in the first position
	genMeth.clickName(driver, genMeth,elData.BTNsort_name);
	genMeth.clickName(driver, genMeth, elData.OPTIONsortOldestFirst_name);
	Thread.sleep(2000);

// long press the folder (choosing the folder for deletion by swipe long duration- need to figure out how to do it by proper long press code)
	Thread.sleep(1000);
	//add long press instead of the swip
	driver.swipe(280, 900, 320, 900, 2000);
	genMeth.clickName(driver, genMeth, elData.BTNdelete_name);
	
// Cancel the delete & make sure that the folder wasn't deleted
	genMeth.clickId(driver, genMeth, elData.BTNcancelDelete_id);
	genMeth.isElementVisibleNative(driver, byNewFolder);
	
// now delete the folder & make sure it was deleted properly
	driver.swipe(280, 900, 320, 900, 2000);
	genMeth.clickName(driver, genMeth, elData.BTNdelete_name);;
	genMeth.clickId(driver, genMeth, elData.BTNdeleteConfirm_id);
	genMeth.isElementInvisibleNative(driver, byNewFolder);
	
// go Start test start screen (LSM left side menu)
	genMeth.clickXpth(driver, genMeth, elData.BTNopenLSM_xpth);
	
	  }

@Test (enabled = true ,testName="Sanity Tests", description = "Test the Upload utility with Android" , groups= {"Sanity Native Android"})	
public void testUploadImage() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
	
	androidElementData elData = genMeth.androidElementInit();
// open pogoplug cloud & press
	genMeth.clickXpth(driver, genMeth, "//android.widget.ListView[1]/android.widget.LinearLayout[3]");
	Thread.sleep(1000);
	genMeth.clickXpth(driver, genMeth, elData.LIST1_xpth);
	
// Capture an image
	genMeth.clickName(driver, genMeth, elData.BTNupload_name);
	genMeth.clickName(driver, genMeth, elData.OPTIONcaptureNewPhoto_name);
	Thread.sleep(2000);
	genMeth.clickId(driver, genMeth, elData.BTNcapturePhoto_id);
	genMeth.clickId(driver, genMeth, elData.BTNcaptureDone_id);
	Thread.sleep(1000);
	genMeth.clickName(driver, genMeth, elData.BTNrefresh_name);
	
// sort the list in order to place the image in the first position
	genMeth.clickName(driver, genMeth,elData.BTNsort_name);
	genMeth.clickName(driver, genMeth, elData.OPTIONsortOldestFirst_name);
	genMeth.clickName(driver, genMeth, elData.BTNrefresh_name);
	
// Check if the image display in the list
	genMeth.isElementVisibleNative(driver, By.xpath(elData.LIST5_xpth));
	
	WebElement uploadedImage = genMeth.returnXpth(driver, genMeth, "//android.view.View[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]/android.widget.TextView[2]");
	Thread.sleep(1000);
	String lastUpload = uploadedImage.getText();
	String currentUpload= "None";
	Thread.sleep(1000);
	
// Add an if that will verify that the Upload has finished (compare the KB - once not changing it probably has finished or stuck)	
	if (lastUpload != currentUpload ){
		genMeth.clickName(driver, genMeth, elData.BTNrefresh_name);
		lastUpload = uploadedImage.getText();
		Thread.sleep(5000);
		genMeth.clickName(driver, genMeth, elData.BTNrefresh_name);
	//	lastUpload = currentUpload;
		currentUpload = uploadedImage.getText();
		
	}
	
// Open the image & make sure that it displays 
	genMeth.clickXpth(driver, genMeth, elData.LIST5_xpth);
	
// Make sure that the "Image not available" text doesn't displayed
	String imageNotAvailable = "Image not available";
	By by4 = By.id("com.pogoplug.android:id/thumbnail_not_found_text_view");
	genMeth.isElementInvisibleTextNative(driver, by4, imageNotAvailable);
	Thread.sleep(1000);	
	genMeth.clickXpth(driver, genMeth, "//android.view.View[1]");
// Delete the image
	driver.swipe(300, 900, 300, 900, 2000);
	Thread.sleep(5000);
	genMeth.clickName(driver, genMeth, elData.BTNdelete_name);
	genMeth.clickId(driver, genMeth, elData.BTNdeleteConfirm_id);

// Check that the image was deleted
	genMeth.isElementInvisibleNative(driver, By.xpath(elData.LIST5_xpth));
	
	}

@AfterSuite (alwaysRun=true)

	public void sendMail() throws Exception {
		driver.quit();	
		SendResults sr = new SendResults("elicherni444@gmail.com", "meny@cloudengines.com", "TestNG results", "Test Results");
		sr.sendTestNGResult();
	
	}



@AfterMethod
public void tearDown() {
	//count ++;

}

}
*/
