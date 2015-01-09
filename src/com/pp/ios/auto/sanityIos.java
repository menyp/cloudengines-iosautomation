package com.pp.ios.auto;

import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

public class SanityIos {

	public IOSDriver driver;
	GenericMethods genMeth = new GenericMethods();
	
	String	currentDateFolder;
	String	webElementXmlLang;
	String	webElementXmlPath;
	String	StartServerPath;
	String 	StopServerPath;
	WebElementsIos iosData;

	
	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) throws Exception,Throwable {		
		
		try {
			driver.removeApp("com.cloudengines.pogoplug");
			driver.quit();
		} catch (Exception e) {
			// swallow if fails
		}
		//Set the tests configuration
		StartServerPath = genMeth.getValueFromPropFile("StartServerPath");
		StopServerPath = genMeth.getValueFromPropFile("StopServerPath");
		webElementXmlPath = genMeth.getValueFromPropFile("webElementXmlPath");
		webElementXmlLang = genMeth.getValueFromPropFile("webElementXmlLang");
		//platform = genMeth.getValueFromPropFile("platform");
		
		iosData= new WebElementsIos(webElementXmlLang, webElementXmlPath);
		driver = genMeth.setCapabilitiesIos(genMeth);

		genMeth.cleanLoginIos(driver, genMeth, iosData, iosData.userUnlimited_name);
		Thread.sleep(1000);

	}

	@BeforeMethod(alwaysRun = true)
	public void checkHomeScreen() throws Exception, Throwable {

		// Check if the client still logged in & in start testing screen (*StartUp screen) before each test
		if (driver == null) {
			
			driver = genMeth.setCapabilitiesIos(genMeth);
			genMeth.cleanLoginIos(driver, genMeth, iosData, iosData.userUnlimited_name );
		}

		else {
			boolean StartUpScreenDisplay = genMeth.checkIsElementVisibleNative( driver , By.name(iosData.Settings_Name));

			if (StartUpScreenDisplay != true) {

				try {
					driver.removeApp("com.cloudengines.pogoplug");
					driver.quit();
				} catch (Exception e) {
					// swallow if fails
				}
				
				driver = genMeth.setCapabilitiesIos(genMeth);
				genMeth.cleanLoginIos(driver, genMeth, iosData, iosData.userUnlimited_name);
				
			}

		}

	}

	@Test(enabled = true, description = "Test the Create folders",
			groups = { "Sanity iOS" }) //dependsOnMethods={"testLogin"})
																																																
	public void testCreatefolder() throws Exception, Throwable {

		String currentDateFolder = genMeth.currentTime();

		// Create & dismiss a new folder
		genMeth.clickName(driver,genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver,genMeth, iosData.BTNcreateOn_Name);
		
		// Dismiss the create folder window
		genMeth.clickName(driver,genMeth, iosData.BTNcancel_Name);
		
		
		

		// make sure that the folder wasn't created

		// Create a new folder
		genMeth.clickName(driver,genMeth, iosData.BTNcreateOn_Name);
		genMeth.sendXpth(driver, genMeth, iosData.TEXTFIELDcreateNewFolder_Xpth, currentDateFolder);
		genMeth.clickName(driver,genMeth, iosData.BTNcreate_Name);

		// Check if the folder was created successfully
		genMeth.isElementVisible(driver, By.name(currentDateFolder));

		// Create a duplicate folder
		genMeth.clickName(driver,genMeth, iosData.BTNback_Name);
		genMeth.scroll(driver, iosData.scrollDown);
		genMeth.clickName(driver,genMeth, iosData.BTNcreateOn_Name);
		genMeth.sendXpth(driver, genMeth, iosData.TEXTFIELDcreateNewFolder_Xpth,currentDateFolder);
		genMeth.clickName(driver,genMeth, iosData.BTNcreate_Name);

		// verify that the create duplicate has failed & a proper error popup is displayed
		genMeth.isElementVisible(driver, By.name(iosData.DuplicateFolder_Name));
		genMeth.clickName(driver,genMeth, iosData.BTNok_Name);

		// Cancel the delete & make sure that the folder wasn't deleted
		genMeth.clickName(driver,genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver,genMeth, currentDateFolder);
		genMeth.clickName(driver,genMeth, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver,genMeth, iosData.BTNcancel_Name);

		// Make sure that the folder wasn't deleted
		genMeth.isElementVisible(driver, By.name(currentDateFolder));

		// now delete the folder & make sure it was deleted properly
		genMeth.clickName(driver,genMeth, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver,genMeth, iosData.BTNdelete_Name);
		genMeth.isElementInvisible(driver, By.name(currentDateFolder));

		// go StartUp screen (LSM - left side menu)
		genMeth.clickName(driver,genMeth, iosData.BTNdone_Name);
		genMeth.clickName(driver,genMeth, iosData.BTNleft_Name);

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Test the upload Existing photos or videos, delete the image",
			groups = { "Regression iOS " })
	public void uploadExistingPotos() throws Exception, Throwable {

		// create a folder for the images
		String currentDateFolder = genMeth.currentTime();
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, "upload from existing test");
		
		//Method that will clean all files in a list
		genMeth.deletList(genMeth, iosData);
		
		
		genMeth.clickName(driver, genMeth, iosData.BTNcreateOn_Name);
		genMeth.sendXpth(driver, genMeth,"//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]",currentDateFolder);
		genMeth.clickName(driver, genMeth, iosData.BTNcreate_Name);

		// Choose a photo & upload it
		genMeth.isElementVisible(driver, By.name(iosData.EmptyFolder_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcameraOn_Name);
		Thread.sleep(1000);
		genMeth.clickName(driver, genMeth, iosData.BTNexistingPhotosorVideos_Name);
		genMeth.isElementVisible(driver, By.name(iosData.PhotoAlbums_Name));

		genMeth.clickName(driver, genMeth, iosData.BTNcameraRoll_Name);
		genMeth.clickXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[4]");
		genMeth.clickXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[5]");
		
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);

		// check if the current location popup is displayed
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);

		// wait for the upload to finish (wait until the "camera on" will display)
		int count = 0;

		while (count < 5) {
			boolean isDisplay = genMeth.checkIsElementVisibleNative(driver,
					By.name(iosData.BTNcameraOn_Name));
			if (isDisplay == false) {
				// check if the upload fail was prompt- if true resume upload
				boolean uploadFails = genMeth.checkIsElementVisibleNative(
						driver, By.name(iosData.UploadError_Name));
				if (uploadFails == true) {
					genMeth.clickName(driver, genMeth, iosData.BTNdismiss_Name);
					genMeth.clickName(driver, genMeth,iosData.BTNnoSpaceOn_Name);
					genMeth.clickName(driver, genMeth,iosData.BTNresumeUpload_Name);
					count++;

				}

				else {

					count++;
				}

			}

			else {
				count = 5;
			}

		}
		
		// Scroll down for refresh
		genMeth.scroll(driver, iosData.scrollUp);

		// check if the image has created in the list
		Thread.sleep(1000);

		// Make sure that both video & image were uploaded to the list with the correct size
		genMeth.isElementVisible(driver, By.name(iosData.UploadExistingImage_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UploadExistingVideo_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "positive_uploadExistingPotos_ListThumbnail");
		
		// Open the Image & verify it displays
		genMeth.clickName(driver, genMeth, iosData.UploadExistingImage_Name);

		// Make sure that the "Image not available" text doesn't displayed
		genMeth.isElementInvisibleTextNative(driver, By.name(iosData.ImageNotAvailable_Name),iosData.ImageNotAvailable_Name);

		// Make sure that the image title displayed
		genMeth.isElementVisible(driver, By.name(iosData.UploadExistingImage_Name));
		
		//Take Screenshot verifying that the image UI is fine
		genMeth.takeScreenShotPositive(driver, genMeth, "positive_uploadExistingPotos_uploaded_IMG_0004");
		
		genMeth.isElementInvisible(driver, By.name(iosData.UploadExistingImage_Name));
		genMeth.clickXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]");
		driver.findElementByName(iosData.BTNback_Name).click();

		// Open the video & verify that the title displayed
		genMeth.clickName(driver, genMeth, iosData.UploadExistingVideo_Name);
		genMeth.isElementVisible(driver, By.name(iosData.UploadExistingVideo_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "positive_uploadExistingPotos_IMG_01188.MOV");
		genMeth.clickXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAImage[1]");
		driver.findElementByName(iosData.BTNback_Name).click();
		
		// Cancel the Delete image
		genMeth.clickName(driver, genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver, genMeth, iosData.UploadExistingImage_Name);
		genMeth.clickName(driver, genMeth, iosData.UploadExistingVideo_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);

		// Make sure that the image wasn't deleted
		genMeth.isElementVisible(driver, By.name(iosData.UploadExistingImage_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UploadExistingVideo_Name));

		// Now delete the image for real
		genMeth.clickName(driver, genMeth, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdelete_Name);
		Thread.sleep(1000);

		// Make sure that the image was deleted
//		genMeth.isElementInvisibleNative(driver, By.name(iosData.UploadExistingImage_Name));
//		genMeth.isElementInvisibleNative(driver, By.name(iosData.UploadExistingVideo_Name));
		
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		// delete the folder
		Thread.sleep(1000);
		genMeth.clickName(driver, genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver, genMeth, currentDateFolder );
		genMeth.clickName(driver, genMeth, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdelete_Name);
		genMeth.isElementInvisible(driver, By.name(currentDateFolder ));
		
		// go to startup screen
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);

	}

	
	@Test(enabled = true, testName = "Sanity Tests", description = "Test TOUR for New accounts and for upgrade accounts",
			groups = { "Sanity iOS" })
	public void testTour() throws Exception, Throwable {

		
		 // =============================================================== 
		 //        Tour for Free Account
		 //===============================================================
		 
		// ====== SKIP for Go Unlimited screen ===== - Login with Free/Limited account & check the tour display & text
		
		
		genMeth.killAppIos(driver);
		driver = genMeth.setCapabilitiesIos(genMeth);
		genMeth.clickName(driver, genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, iosData.userLimited_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, genMeth, iosData.BTNsignin_Name);
		
		// Make sure that the tour Never lose a photo display properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseAPhoto_Name));
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseaPhotoFullText_Name));

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimply_Name));
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimplyFullText_Name));
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisible(driver, By.name(iosData.UnlimitedProtection_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UpgradeTour_Name));
		
		// Skip- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
		genMeth.clickName(driver, genMeth, iosData.BTNskip_Name);

		// Verify that the backup tour text is displayed
		genMeth.isElementVisible (driver, By.name(iosData.Backup_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BackupTourText_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcontinue_Name);
		
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));
		Thread.sleep(1000);

		// ===== X BUTTON for Go Unlimited screen ====== - Login with Free/Limited account & check the tour display & text
		
		
		genMeth.killAppIos(driver);
		
		// Login with an existing account & check the tour
		driver = genMeth.setCapabilitiesIos(genMeth);
		genMeth.clickName(driver, genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, iosData.userLimited_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, genMeth, iosData.BTNsignin_Name);
		

		// Make sure that the tour Never lose a photo display properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseAPhoto_Name));
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseaPhotoFullText_Name));

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimply_Name));
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimplyFullText_Name));
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisible(driver, By.name(iosData.UnlimitedProtection_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UpgradeTour_Name));

		// X BUTTON- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
		genMeth.clickName(driver, genMeth, iosData.BTNxInTour_Name);

		// Verify that the backup tour text is displayed
		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BackupTourText_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));
		Thread.sleep(1000);

		// ====== GO UNLIMITED BUTTON ====- Login with Free/Limited account & check the tour display & text
		
		genMeth.killAppIos(driver);	
		driver = genMeth.setCapabilitiesIos(genMeth);
		genMeth.clickName(driver, genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, iosData.userLimited_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, genMeth, iosData.BTNsignin_Name);
		
		// Make sure that the tour "Never lose a photo" display properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseAPhoto_Name));
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseaPhotoFullText_Name));

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimply_Name));
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimplyFullText_Name));
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisible(driver, By.name(iosData.UnlimitedProtection_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UpgradeTour_Name));

		// Go Unlimited button- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
		genMeth.clickName(driver, genMeth, iosData.BTNgoUnlimited_Name);
		genMeth.isElementVisible(driver, By.name(iosData.UpgradeAccount_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UpgardeAccountText_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BTNupgrade_Name));

		genMeth.clickName(driver, genMeth, iosData.BTNdismiss_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNskip_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		// Open the Upgrade Account screen from LSM
		genMeth.clickName(driver, genMeth, iosData.BTNupgrade_Name);
		genMeth.isElementVisible(driver, By.name(iosData.UpgradeAccount_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UpgardeAccountText_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BTNupgrade_Name));
		
		//open the Terms an conditions
		genMeth.clickName(driver, genMeth, iosData.iconTermsAndCondition_Name);
		
		//Make sure that the terms of service page is open successfully
		genMeth.isElementVisible(driver, By.name(iosData.TermsOfService_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdismiss_Name);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));
		
		
	// =====================================================
	//     Tour for Unlimited Account
	// =====================================================
		 
		genMeth.killAppIos(driver);
		driver = genMeth.setCapabilitiesIos(genMeth);
		genMeth.clickName(driver, genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, iosData.userUnlimited_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, genMeth, iosData.BTNsignin_Name);

		// Make sure that the tour "Never lose a photo" display properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseAPhoto_Name));
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseaPhotoFullText_Name));

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimply_Name));
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimplyFullText_Name));
		driver.swipe(270, 265, 55, 265, 1000);
		Thread.sleep(1000);

		// Verify that the backup tour text is displayed
		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BackupTourText_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Sign up- Create new user (Negetive positive test), Privacy Policy, TRUSTe",
			groups = { "Sanity iOS" })
	public void createNewUser() throws Exception, Throwable {

		String currentDateFolder = genMeth.currentTime();
		genMeth.signOutFromStartupIphone5( driver , iosData);

		// Create a new user & Login
		genMeth.signUp(genMeth, iosData);
		
		// Make sure that the "Anything that is backed up..." screen display when no files were uploaded yet to the cloud
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.isElementVisible(driver, By.name(iosData.CloudEmptyFilesScreen_Name));

		// Attempt to Create new user with *bad format (* incorrect email format)
		genMeth.killAppIos(driver);
		driver = genMeth.setCapabilitiesIos(genMeth);
		genMeth.clickName(driver, genMeth, iosData.BTNsignUp_Name);
		// check that the Back button works properly
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, "meny");
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, genMeth, iosData.BTNsignUpForFree_Name);
		genMeth.takeScreenShotPositive(driver, genMeth, "positive_createNewUser_badEmailFormat");

		// privacy policy
		genMeth.clickName(driver, genMeth, iosData.LinkPrivacyPolicy_Name);
		genMeth.isElementVisible(driver, By.name(iosData.PrivacyPolicyUrl_Name));
		
		genMeth.killAppIos(driver);
		
		// TRUSe link
		driver = genMeth.setCapabilitiesIos(genMeth);
		genMeth.clickName(driver, genMeth, iosData.BTNsignUp_Name);
		genMeth.clickName(driver, genMeth, iosData.LinkTRUSTe_Name);
		genMeth.isElementVisible(driver, By.id(iosData.TrusteUrl_Name));
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "login with bad/missing credentials , forgot password (negative & positive)",
			groups = { "Sanity iOS" })
	public void badCredentials() throws Exception, Throwable {

		
		String randomName = genMeth.randomString();
		genMeth.signOutFromStartupIphone5(driver , iosData);

		// Login with bad credentials
		genMeth.clickName(driver, genMeth, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, iosData.userUnlimited_name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.badPassword);
		genMeth.clickName(driver, genMeth, iosData.BTNsignin_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BadCredentialsPopup));
		genMeth.clickName(driver, genMeth, iosData.BTNok_Name);

		// Forgot your password Negative (attempt to restore password with a non existing email)
		genMeth.clickName(driver, genMeth, iosData.LinkForgotYourPassword_Name);
		genMeth.clickName(driver, genMeth, iosData.iconClearText_Name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, randomName + "@a.com" );
		genMeth.clickName(driver, genMeth, iosData.BTNsubmit_Name);
		genMeth.isElementVisible(driver, By.name(iosData.ForgotPasswordErrorPopup_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNdismiss_Name);
		genMeth.clickId(driver, genMeth, randomName + "@a.com");
		genMeth.clickName(driver, genMeth, iosData.BTNclearTextIcon_Name);
		
		// Forgot your password Positive (attempt to restore password with an existing email)
		genMeth.sendId(driver, genMeth,iosData.TEXTFIELDemail_Id,iosData.userUnlimited_name);
		genMeth.clickName(driver, genMeth, iosData.BTNsubmit_Name);
		genMeth.isElementVisible(driver, By.name(iosData.ResetEmailPopup_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNok_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNsignin_Name));

	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Search functionality & filter",
			groups = { "Sanity iOS" })
	public void search() throws Exception, Throwable {
		
		String Random = genMeth.randomString();	

		//Search in ROOT for a folder
		WebElement search = genMeth.returnId(driver, genMeth, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchFolderFromRoot_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchFolderFromRoot_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);

		// Search in ROOT for an image
		search.click();
		search.sendKeys(iosData.SearchImageFromRoot_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchImageFromRoot_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);

		// Search in ROOT for empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoFilesFound_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		
		// Search in FOLDER for a folder
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.SearchMainFolder_Name);
		search = genMeth.returnId(driver, genMeth, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchFolderFromFolder_Name);
		genMeth.isElementVisible(driver, By.name(iosData.tabCurrentFolder_Name));
		genMeth.isElementVisible(driver, By.name(iosData.SearchFolderFromFolder_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);

		// Search in FOLDER for an image
		search.click();
		search.sendKeys(iosData.SearchImageFromFolder_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchImageFromFolder_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);

		// Search in FOLDER for empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoFilesFound_Name));
		genMeth.clickName(driver, genMeth, iosData.iconClearText_Name);
		
		// Search in POGOPLUG for a folder
		genMeth.clickName(driver, genMeth, iosData.tabPogoplugCloud_Name);
		search.click();
		search.sendKeys(iosData.SearchFolderFromRoot_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchFolderFromRoot_Name));
		genMeth.clickName(driver, genMeth, iosData.tabCurrentFolder_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.SearchFolderFromRoot_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		
		// Search in POGOPLUG for an image
		search.click();
		search.sendKeys(iosData.SearchImageFromRoot_Name);
		genMeth.clickName(driver, genMeth, iosData.tabPogoplugCloud_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchImageFromRoot_Name));
		genMeth.clickName(driver, genMeth, iosData.iconClearText_Name);
		
		// Search in POGOPLUG for empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoFilesFound_Name));
		genMeth.clickName(driver, genMeth, iosData.iconClearText_Name);
		
		//   Music Player  
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNmusicPlayer_Name);
		
		//Search in Songs A song	
		search = genMeth.returnId(driver, genMeth, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInSong_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchSongInSong_Name));
		driver.tap(1, 285, 40, 2);

		//Search in songs empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoResults_Name));
		driver.tap(1, 285, 40, 2);

		//Search in Artists song
		genMeth.clickName(driver, genMeth, iosData.iconArtists_Name);
		search = genMeth.returnId(driver, genMeth, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInArtists_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchSongInArtists_Name));
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisible(driver, By.name(iosData.NoResults_Name));
		
		//Search in Artists empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoResults_Name));
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisible(driver, By.name(iosData.NoResults_Name));
		
		//Search in Albums song
		genMeth.clickName(driver, genMeth, iosData.iconAlbums_Name);
		search = genMeth.returnId(driver, genMeth, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInAlbums_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchSongInAlbums_Name));
		driver.tap(1, 285, 40, 2);
		
		//Search in Albums empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoResults_Name));
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisible(driver, By.name(iosData.NoResults_Name));
		
		//Search in Genres song 
		genMeth.clickName(driver, genMeth, iosData.iconGenres_Name);
		search = genMeth.returnId(driver, genMeth, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInGenres_Name);
		genMeth.isElementVisible(driver, By.name(iosData.SearchSongInGenres_Name));
		driver.tap(1, 285, 40, 2);
		
		//Search in Genres empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisible(driver, By.name(iosData.NoResults_Name));
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisible(driver, By.name(iosData.NoResults_Name));
		
		//search in add users screen
		genMeth.clickName(driver, genMeth,iosData.BTNleft_Name);
		
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNshareOn_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNaddUsers_Name);
		
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
				
		genMeth.isElementVisible(driver, By.name(iosData.BTNaddUsers_Name));
		
		//search for a contact
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDsearch_Id, iosData.userUnlimited_name );
		genMeth.isElementVisible(driver, By.name(iosData.userUnlimited_name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.userUnlimited_name));
		
		//no results search using clear & cancel 
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDsearch_Id, Random );
		genMeth.isElementVisible(driver, By.name(iosData.NoResults_Name));
		
		//Back to start up screen
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Passcode",
			groups = { "Sanity iOS" })
	public void settingsPasscodeSanity() throws Exception, Throwable {

		// Cancel the Passcode screen
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNpassCodeLock_Name);
		genMeth.isElementVisible(driver, By.name(iosData.PasscodeLock_Name));
		genMeth.clickXpth(driver,genMeth, iosData.TOGGLEpasscodeLock_Xpth);
		genMeth.isElementVisible(driver, By.name(iosData.EnterPasscode_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);

		// Attempt to create wrong Passcode
		genMeth.isElementVisible(driver, By.name(iosData.PasscodeLock_Name));
		genMeth.clickXpth(driver,genMeth, iosData.TOGGLEpasscodeLock_Xpth);
		genMeth.isElementVisible(driver, By.name(iosData.EnterPasscode_Name));		
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisible(driver, By.name(iosData.PinCodeReEnter_Name));
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton2_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);

		genMeth.clickName(driver, genMeth, iosData.PinCodeNotMatch_Name);

		// Create a correct Passcode
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisible(driver, By.name(iosData.PinCodeReEnter_Name));
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisible(driver, By.name(iosData.BTNchangePasscode_Name));
		genMeth.clickXpth(driver,genMeth, iosData.TOGGLErequireImmediately_Xpth);

		// make sure that the passcode initiated properly
		driver.lockScreen(2);
		driver.swipe(30, 300, 250, 30, 500);
		genMeth.isElementVisible(driver, By.name(iosData.EnterPasscode_Name));
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver,genMeth, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisible(driver, By.name(iosData.BTNchangePasscode_Name));

		// Disable the passcode
		genMeth.clickXpth(driver,genMeth, iosData.TOGGLEpasscodeLock_Xpth);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNchangePasscode_Name));

		// make sure that the passcode isn't initiated
		driver.lockScreen(2);
		driver.swipe(30, 300, 250, 30, 500);
		genMeth.isElementVisible(driver, By.xpath(iosData.TOGGLEpasscodeLock_Xpth));
		// Go to startup screen
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));
		


	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Save Login",
			groups = { "Sanity iOS" })
	public void settingsSaveLoginSanity() throws Exception, Throwable {

		// Save Login = true
		driver.closeApp();
		driver.launchApp();
		genMeth.clickId(driver,genMeth, iosData.Settings_Name);
		genMeth.isElementVisible(driver, By.name(iosData.TOGGLEsaveLogin_Name));
		
		// Save Login = false
		genMeth.clickName(driver, genMeth, iosData.TOGGLEsaveLogin_Name);
		driver.closeApp();
		driver.launchApp();
		genMeth.isElementVisible(driver, By.name(iosData.BTNalreadyHaveAnAccount_name));

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Backup Enable/disable without upload in the background",
			groups = { "Regression iOS" })
	public void settingsBackupEnableDisable() throws Exception, Throwable {

		// Disable the Backup
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNon_Name);
		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		genMeth.clickName(driver, genMeth, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Disabled_Name));

		// "Enable" the backup form LSM (Left Side Menu) & cancel it
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.isElementVisible(driver, By.name(iosData.ProtectYourPhotos_Name));
		genMeth.isElementVisible(driver, By.name(iosData.AutomaticallyBackUpPhotosAndVideosToYourAccount_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));

		// Enable the Backup form LSM (Left Side Menu)
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));

		// Enable the backup from settings (first enable it & then disable it from backup screen)
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNon_Name);
		genMeth.clickName(driver, genMeth, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Disabled_Name));

		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.clickXpth(driver,genMeth, "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[2]");
		genMeth.clickName(driver, genMeth, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));

	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Backup Enable/disable *with upload in the background",
			groups = { "Sanity iOS" })// , dependsOnMethods={"successTest"})
	public void settingsBackupEnableDisableDuringUpload() throws Exception,Throwable {

		// login with new account & enable/disable the backup from Tour/Settings/LSM/Photo Gallery
		//iosData = genMeth.iOSelementInit(langEng);
		String randomName = genMeth.randomString();
		String currentDateFolder = genMeth.currentTime();
		
		genMeth.signOutFromStartupIphone5(driver, iosData);
		
		genMeth.clickName(driver, genMeth, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDemail_Id, "meny@" + randomName + ".com");
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, genMeth, iosData.BTNsignUpForFree_Name);
		
		genMeth.isElementVisible(driver, By.name(iosData.NeverLoseAPhoto_Name));
		driver.swipe(270, 265, 55, 265, 1000);
		genMeth.isElementVisible(driver, By.name(iosData.TransferPhonesSimply_Name));
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisible(driver, By.name(iosData.UnlimitedProtection_Name));
		genMeth.isElementVisible(driver, By.name(iosData.UpgradeTour_Name));
		genMeth.clickName(driver, genMeth, "UIAccessoryButtonX");

		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		
		//Disable the backup from TOUR
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		// verify that the backup is Disabled in LSM
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
				
		//Enable backup form LSM
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		
		// verify that the backup is running
		genMeth.isElementVisible(driver, By.name(iosData.iconInProgress_Name));
		
		//Disable the backup from SETTINGS
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNon_Name);
		driver.findElementByName(iosData.TOGGLEcameraRoll_Name).click();
//		genMeth.clickName(driver, genMeth, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		
		//verify that the backup was disabled in LSM
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Disabled_Name));
		
		//Enable backup from TIMELINE 
		genMeth.clickName(driver, genMeth, iosData.BTNphotoGallery_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Disabled_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUpload_TimelineBackupDisable");
//		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.tapName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNwifiAndCellular_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		
		// check that the backup disabled notification isn't displayed
		genMeth.isElementVisible(driver, By.name(iosData.iconInProgress_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));
		
		// Disable the Backup form settings
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNon_Name);
//		genMeth.clickName(driver, genMeth, iosData.TOGGLEcameraRoll_Name);
		driver.findElementByName(iosData.TOGGLEcameraRoll_Name).click();
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		
		//Enable backup from VIDEOS 
//		genMeth.clickName(driver, genMeth, iosData.BTNphotoGallery_Name);
		driver.findElementByName(iosData.BTNphotoGallery_Name).click();
		genMeth.clickName(driver, genMeth, iosData.BTNvideos_Name);
		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Disabled_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUpload_VideoBackupDisable");
//		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.tapName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.isElementVisible(driver, By.name(iosData.BTNwifiAndCellular_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);

		// check that the backup disabled notification isn't displayed
		genMeth.isElementVisible(driver, By.name(iosData.iconInProgress_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));
		
		// Disable the Backup form settings
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNon_Name);
//		genMeth.clickName(driver, genMeth, iosData.TOGGLEcameraRoll_Name);
		driver.findElementByName(iosData.TOGGLEcameraRoll_Name).click();
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		
		// Enable backup from ALBUMS
//		genMeth.clickName(driver, genMeth, iosData.BTNphotoGallery_Name);
		driver.findElementByName(iosData.BTNphotoGallery_Name).click();
		genMeth.clickName(driver, genMeth, iosData.BTNalbums_Name);
		genMeth.isElementVisible(driver, By.name(iosData.Backup_Name));
		genMeth.isElementVisible(driver, By.name(iosData.Disabled_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BTNenable_Name));
//		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.tapName(driver, genMeth, iosData.BTNenable_Name);
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUpload_AlbumsBackupDisable");
		genMeth.isElementVisible(driver, By.name(iosData.BTNwifiAndCellular_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNenable_Name);

		// check that the backup disabled notification isn't displayed
		genMeth.isElementVisible(driver, By.name(iosData.iconInProgress_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));

		// Check that the backup has finished at ALBUMS,VIDEOS,PHOTO GALLERY & LSM
		genMeth.waitForElementToBeInvisible(driver, By.name(iosData.iconInProgress_Name), 5);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUpload_AlbumsThumbnailsDisplay");
		
		genMeth.clickName(driver, genMeth, iosData.BTNvideos_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUpload_VideosThumbnailsDisplay");

		genMeth.clickName(driver, genMeth, iosData.BTNtimeline_Name_name);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNenable_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.Disabled_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUpload_TimelineThumbnailsDisplay");

		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.isElementVisible(driver, By.name(iosData.Completed_Name));
		
		// verify that the images were uploaded to the cloud
//		genMeth.clickName(driver, genMeth, iosData.BTNphotoGallery_Name);	
		driver.findElementByName(iosData.BTNphotoGallery_Name).click();
		genMeth.clickXpth(driver,genMeth, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[2]");
//		genMeth.clickXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAImage[1]");
		
		// Make sure that the "Image not available" text doesn't displayed
		genMeth.isElementInvisibleTextNative(driver, By.name(iosData.ImageNotAvailable_Name),iosData.ImageNotAvailable_Name);
		Thread.sleep(2000);
		genMeth.takeScreenShotPositive(driver, genMeth, "Positive_settingsBackupEnableDisableDuringUploadImagePreview");
		
	}

	@Test(enabled = true, testName = "Sanity Tests", description = " Backup running in background -> make sure process keeps alive and completes its queue even in background AND if taking new shots they are automatically backed up", groups = { "Sanity iOS" })
	public void backupInBackground() throws Exception, Throwable {

		// webElementsIos iosData = genMeth.iOSelementInit(langEng);

		// Login with new account (*backup will initiate)
		genMeth.signOutFromStartupIphone5(driver, iosData);

		genMeth.signUp(genMeth, iosData);

		// verify that the backup is Enable & running
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.isElementVisible(driver, By.name(iosData.iconInProgress_Name));

		// Go to background/Sleep & wait 60 seconds
		driver.lockScreen(60);

		// Bring App back to foreground & make sure that backup has finished
		// successfully
		genMeth.waitForElementToBeInvisible(driver,By.name(iosData.iconInProgress_Name), 8);

		// Verify that the backup is completed
		genMeth.isElementVisible(driver, By.name(iosData.Completed_Name));

		// Make sure that a random image is open successfully
		genMeth.clickName(driver, genMeth, iosData.BTNphotoGallery_Name);
		genMeth.clickXpth(driver, genMeth,"//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]");
		driver.tap(1, 60, 120, 1);

		// Make sure that the "Image not available" text doesn't displayed
		Thread.sleep(2000);
		genMeth.isElementInvisibleTextNative(driver,By.name(iosData.ImageNotAvailable_Name),iosData.ImageNotAvailable_Name);
		genMeth.takeScreenShotPositive(driver, genMeth,"positive_backupInBackground_imagePreview");

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Switching from Foreground to Background and vice versa use cases", groups = { "Sanity iOS" })
	public void foregroundBackgroundSwith() throws Exception, Throwable {

		// Take the app to background & foreground x times
		genMeth.backgroundToForeground(driver, 10);
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));

		// Take the app to sleep/lock x times
		genMeth.lockUnlock(driver, 10);
		genMeth.isElementVisible(driver, By.name(iosData.Settings_Name));

	}

	@Test(enabled = true, testName = "Sanity Tests", description = " Add remove files from favorites", groups = { "Sanity iOS" })
	public void Favorites() throws InterruptedException, IOException,
			ParserConfigurationException, SAXException {
		// open favorites & make sure that it is empty
		genMeth.clickName(driver, genMeth, iosData.BTNfavorites_Name);
		genMeth.isElementVisible(driver, By.name(iosData.EmptyFavorites_Name));

		// positive empty favorites screenshot
		genMeth.takeScreenShotPositive(driver, genMeth,
				"Positive_Favorites_FavoritesEmptyScreen");
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesTitle_Name);
		Thread.sleep(1000);

		// Add image/video/song to favorites
		genMeth.clickName(driver, genMeth, iosData.FavoritesMp3_Name);
		genMeth.clickName(driver, genMeth, iosData.iconAddToFavorites_Name);
		genMeth.isElementVisible(driver,
				By.name(iosData.iconRemoveFromFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);

		genMeth.clickName(driver, genMeth, iosData.FavoritesMov_Name);
		genMeth.clickName(driver, genMeth, iosData.iconAddToFavorites_Name);
		genMeth.isElementVisible(driver,By.name(iosData.iconRemoveFromFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);		

		genMeth.clickName(driver, genMeth, iosData.FavoritesPng_Name);
		driver.findElementByName(iosData.iconAddToFavorites_Name).click();
		genMeth.isElementVisible(driver,By.name(iosData.iconRemoveFromFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);		

		// Make sure that it is displayed in favorites
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfavorites_Name);
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesMov_Name));
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesMp3_Name));
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesPng_Name));

		// remove the files from favorites & make sure that the empty screen
		// display
		genMeth.clickName(driver, genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesMp3_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesMov_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesPng_Name);

		genMeth.clickName(driver, genMeth, iosData.BTNremoveFavorites_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesMov_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesMp3_Name));
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesPng_Name));
		genMeth.isElementVisible(driver, By.name(iosData.EmptyFavorites_Name));

		// Now remove from favorites from preview Toolbar

		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesTitle_Name);
		Thread.sleep(1000);

		// Add image/video/song to favorites
		genMeth.clickName(driver, genMeth, iosData.FavoritesMp3_Name);
		genMeth.clickName(driver, genMeth, iosData.iconAddToFavorites_Name);
		genMeth.isElementVisible(driver,By.name(iosData.iconRemoveFromFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);

		genMeth.clickName(driver, genMeth, iosData.FavoritesMov_Name);
		genMeth.clickName(driver, genMeth, iosData.iconAddToFavorites_Name);
		genMeth.isElementVisible(driver,By.name(iosData.iconRemoveFromFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesPng_Name);
		driver.findElementByName(iosData.iconAddToFavorites_Name).click();
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);

		// Make sure that it is displayed in favorites
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfavorites_Name);
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesMov_Name));
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesMp3_Name));
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesPng_Name));

		// Now remove favorites from preview Toolbar
		genMeth.clickName(driver, genMeth, iosData.FavoritesMp3_Name);
		genMeth.clickName(driver, genMeth, iosData.iconRemoveFromFavorites_Name);
		genMeth.isElementVisible(driver,By.name(iosData.iconAddToFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNback_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesMp3_Name));

		genMeth.clickName(driver, genMeth, iosData.FavoritesMov_Name);
		genMeth.clickName(driver, genMeth,iosData.iconRemoveFromFavorites_Name);
		genMeth.isElementVisible(driver,By.name(iosData.iconAddToFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesMov_Name));

		genMeth.clickName(driver, genMeth, iosData.FavoritesPng_Name);
		genMeth.clickName(driver, genMeth,iosData.iconRemoveFromFavorites_Name);
		genMeth.isElementVisible(driver,By.name(iosData.iconAddToFavorites_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesPng_Name));

		// go back to startup page
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Adding & removing team folders",
			groups = { "Sanity iOS" })
	public void addRemoveTeamFolders() throws Exception, Throwable {
		
		//webElementsIos iosData = genMeth.iOSelementInit(langEng);

		//Share user with team folder 
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesTitle_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNshareOn_Name);
		boolean isShared = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.BTNremoveAllUsers_Name));
		if (isShared == true) {

			genMeth.clickName(driver, genMeth, iosData.BTNremoveAllUsers_Name);
			genMeth.clickName(driver, genMeth, iosData.BTNshareOn_Name);
		}
		genMeth.clickName(driver, genMeth, iosData.BTNaddUsers_Name);
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		genMeth.isElementVisible(driver, By.name(iosData.BTNaddUsers_Name));
		
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDsearch_Id, iosData.userAutomation2_Name );
		genMeth.isElementVisible(driver, By.name(iosData.userAutomation2_Name));
		genMeth.clickName(driver, genMeth, iosData.userAutomation2_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementVisible(driver, By.id("1"));
		genMeth.takeScreenShotPositive(driver, genMeth, "positive_addRemoveTeamFolders_teamFolderSharedUsersNumber");
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		
		// login with the SHARED user & make sure that the team folder was added & can be open
		genMeth.signOutFromStartupIphone5(driver, iosData);
		genMeth.loginIos(genMeth, iosData, iosData.userAutomation2_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNteamFolders_Name);
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesTitle_Name));
		genMeth.isElementVisible(driver, By.name(iosData.BTNteamFolders_Name));
		
		//Cancel remove share
		genMeth.clickName(driver, genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesTitle_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNremoveShare_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.isElementVisible(driver, By.name(iosData.FavoritesTitle_Name));
		
		//Remove share folder from the SHARED user
		genMeth.clickName(driver, genMeth, iosData.BTNremoveShare_Name);
		//genMeth.clickName(driver, iosData.BTNremoveShare_Name);
		genMeth.clickXpth(driver,genMeth, "//UIAApplication[1]/UIAWindow[4]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]");
		
		//Make sure that the shared folder was removed
		genMeth.isElementInvisible(driver, By.name(iosData.FavoritesTitle_Name));
		genMeth.isElementVisible(driver, By.name(iosData.EmptyFolder_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.signOutFromStartupIphone5(driver, iosData);
		
		// login with the SHARING user & make sure that the folder isn't shared anymore
		genMeth.loginIos(genMeth, iosData, iosData.userUnlimited_name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNteamFolders_Name));
				
		//Remove shared folder from the SHARING user
		genMeth.clickName(driver, genMeth, iosData.BTNedit_Name);
		genMeth.clickName(driver, genMeth, iosData.FavoritesTitle_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNshareOn_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNaddUsers_Name);
		genMeth.handleAccessPhotosContactsLocationNotifications(genMeth, iosData);
		genMeth.isElementVisible(driver, By.name(iosData.BTNaddUsers_Name));
		
		//Share user with team folder & then remove share
		genMeth.sendId(driver, genMeth, iosData.TEXTFIELDsearch_Id, iosData.userAutomation2_Name );
		genMeth.isElementVisible(driver, By.name(iosData.userAutomation2_Name));
		genMeth.clickName(driver, genMeth, iosData.userAutomation2_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNdone_Name);
		genMeth.isElementVisible(driver, By.id("1"));
		genMeth.clickName(driver, genMeth, iosData.BTNshareOn_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNremoveAllUsers_Name);
		Thread.sleep(2000);
		genMeth.takeScreenShotPositive(driver, genMeth, "positive_addRemoveTeamFolders_teamFolderSharedUsersRemoveNumber");
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.clickName(driver, genMeth, iosData.Settings_Name);
		genMeth.signOutFromStartupIphone5(driver, iosData);
		
		//Make sure that the folder isn't shared under the SHARED account
		genMeth.loginIos(genMeth, iosData, iosData.userAutomation2_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNfileExplorer_Name);
		genMeth.isElementInvisible(driver, By.name(iosData.BTNteamFolders_Name));
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		genMeth.signOutFromStartupIphone5(driver, iosData);
		genMeth.loginIos(genMeth, iosData, iosData.userUnlimited_name);
		genMeth.clickName(driver, genMeth, iosData.BTNleft_Name);
		
	}
	
	
	@Test(enabled = false, testName = "Sanity Tests", description = "verify that the delete from favorites/Albums/ use cases", groups = { "Sanity Native iOS" })
	public void deleteFrom () throws Exception, Throwable {

		// ??????????????????
		//String currentDateFolder = genMeth.currentTime();
		//webElementiOS iosData = genMeth.iOSelementInit(langEng);
		
	}
	
	@Test(enabled = false, testName = "Sanity Tests", description = "Create/Delete Albums",
			groups={"Sanity iOS"})
	public void Albums() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		String album = genMeth.currentDate();
		//Make sure that there are no albums
		
		//Create album & add image/video to that album (empty screen verification)
		genMeth.clickName(driver, genMeth, iosData.BTNphotoGallery_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNalbums_Name);
		genMeth.clickName(driver, genMeth, iosData.BTNcreateOn_Name);
		genMeth.isElementVisible(driver, By.name("Create New Album"));
		genMeth.sendXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]", album);
		genMeth.clickName(driver, genMeth, iosData.BTNcancel_Name);
		genMeth.isElementInvisible(driver, By.name(album));
		
		genMeth.clickName(driver, genMeth, iosData.BTNcreateOn_Name);
		genMeth.isElementVisible(driver, By.name("Create New Album"));
		genMeth.sendXpth(driver, genMeth, "//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]", album);
		genMeth.clickName(driver, genMeth, iosData.BTNcreate_Name);

		//Add images to an existing album from timeline & videos tabs 
		
		//Rename Album
		
		//Delete images from the album & make sure it was removed from the album only & not the source
		
		//Delete the album
		
		
	}
	
	@Test(enabled = false,  testName = "Sanity Tests", description = "flatview testing",
			groups = {"Sanity iOS"})
	public void FlatView (){
		
		
		
		
	}
	
	@Test(enabled = false, testName = "Sanity Tests", description = "like in Facebook",
			groups={"Regression iOS"})
	public void likeOnFacebook (){
		
		
	}
	
	@Test(enabled = false, testName = "Sanity Tests", description = "like in Facebook",
			groups={"Regression iOS"})
	public void settingsMore (){
		
		
		
	}
	
	//Empty screen validation (with screenshots?)
	
			/*
			 * Favorites (handled in favorites test)
			 * Photo Gallery (timeline, Videos, Albums)
			 * Music player
			 * Default destination
			 * 
			 * 
			 *
			 * 
			 * 
			 */
		
			
			//create restore snapshot
	

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		
		try {
		driver.removeApp("com.cloudengines.pogoplug");
			driver.quit();
		} catch (Exception x) {
			// For iPhone4
		}

		/*
		SendResults sr = new SendResults("elicherni444@gmail.com","meny@cloudengines.com", "TestNG results", "Test Results");
		sr.sendTestNGResult();
		sr.sendRegularEmail();
		*/
	}

}
