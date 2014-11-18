package com.pp.ios.auto;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

public class sanityNativeIosAppium {

	public AppiumDriver driver;
	GenericMethods genMeth = new GenericMethods();
	String langEng = "iOStestDataENG.xml";
	String currentDateFolder;
	webElementiOS iosData;
	DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) throws Exception,Throwable {

		iosData = genMeth.iOSelementInit(langEng);
		driver = genMeth.cleanLoginIos(iosData, iosData.userUnlimited_name);
		Thread.sleep(1000);

	}

	@BeforeMethod(alwaysRun = true)
	public void checkHomeScreen() throws Exception, Throwable {

		// Check if the client still logged in & in start testing screen (*StartUp screen) before each test
		if (driver == null) {

			driver = genMeth.cleanLoginIos(iosData,iosData.userUnlimited_name );
		}

		else {
			boolean StartUpScreenDisplay = genMeth.checkIsElementVisibleNative( driver , By.name(iosData.Settings_Name));

			if (StartUpScreenDisplay != true) {

				// genMeth.killAppIos(driver);
				driver.removeApp("com.cloudengines.pogoplug");
				try{
					driver.quit();
				}
				catch(Exception e){
					Thread.sleep(1000);
				}
				 driver = genMeth.cleanLoginIos(iosData, iosData.userUnlimited_name);
				
			}

		}

	}

	@Test(enabled = true, description = "Test the Create folders",
			groups = { "Sanity Native iOS" }) //dependsOnMethods={"testLogin"})
																																																
	public void testCreatefolder() throws Exception, Throwable {

		String currentDateFolder = genMeth.currentTime();

		// Create & dismiss a new folder
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.clickName(driver, iosData.BTNcreateOn_Name);
		
		// Dismiss the create folder window
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// make sure that the folder wasn't created

		// Create a new folder
		genMeth.clickName(driver, iosData.BTNcreateOn_Name);
		genMeth.sendXpth(driver, iosData.TEXTFIELDcreateNewFolder_Xpth, currentDateFolder);
		genMeth.clickName(driver, iosData.BTNcreate_Name);

		// Check if the folder was created successfully
		genMeth.isElementVisibleNative(By.name(currentDateFolder), driver);

		// Create a duplicate folder
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNcreateOn_Name);
		genMeth.sendXpth(driver, iosData.TEXTFIELDcreateNewFolder_Xpth,currentDateFolder);
		genMeth.clickName(driver, iosData.BTNcreate_Name);

		// verify that the create duplicate has failed & a proper error popup is displayed
		genMeth.isElementVisibleNative(By.name(iosData.DuplicateFolder_Name),driver);
		genMeth.clickName(driver, iosData.BTNok_Name);

		// Cancel the delete & make sure that the folder wasn't deleted
		genMeth.clickName(driver, iosData.BTNedit_Name);
		genMeth.clickName(driver, currentDateFolder);
		genMeth.clickName(driver, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Make sure that the folder wasn't deleted
		genMeth.isElementVisibleNative(By.name(currentDateFolder),	driver);

		// now delete the folder & make sure it was deleted properly
		genMeth.clickName(driver, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, iosData.BTNdelete_Name);
		genMeth.isElementInvisibleNative(By.name(currentDateFolder), driver);

		// go StartUp screen (LSM - left side menu)
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.clickName(driver, iosData.BTNleft_Name);

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Test the upload Existing photos or videos, delete the image",
			groups = { "Sanity Native iOS" })
	public void uploadExistingPotos() throws Exception, Throwable {

		// create a folder for the images
		String currentDateFolder = genMeth.currentTime();
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, iosData.BTNcreateOn_Name);
		genMeth.sendXpth(driver,"//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]",currentDateFolder);
		genMeth.clickName(driver, iosData.BTNcreate_Name);

		// Choose a photo & upload it
		genMeth.isElementVisibleNative(By.name(iosData.EmptyFolder_Name), driver);
		genMeth.clickName(driver, iosData.BTNcameraOn_Name);
		Thread.sleep(1000);
		genMeth.clickName(driver, iosData.BTNexistingPhotosorVideos_Name);
		genMeth.isElementVisibleNative(By.name(iosData.PhotoAlbums_Name),driver);

		genMeth.clickName(driver, iosData.BTNcameraRoll_Name);
		genMeth.clickXpth(driver, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[3]");
		genMeth.clickXpth(driver, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]");
		
		genMeth.clickName(driver, iosData.BTNdone_Name);

		// check if the current location popup is displayed
		String locationPopup = "“Pogoplug” Would Like to Use Your Current Location";
		By byXpathLocation = By.xpath("	//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[1]");
		boolean isLocationPopupDisplay = genMeth.checkIsTextPresentNative(driver, locationPopup, byXpathLocation);
		
		if (isLocationPopupDisplay == true) {

			genMeth.clickName(driver, iosData.BTNok_Name);

		}

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
					genMeth.clickName(driver, iosData.BTNdismiss_Name);
					genMeth.clickName(driver, iosData.BTNnoSpaceOn_Name);
					genMeth.clickName(driver, iosData.BTNresumeUpload_Name);
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
		// swipe down for refresh
		driver.swipe(168, 122, 168, 380, 500);

		// check if the image has created in the list
		Thread.sleep(1000);

		// Make sure that both video & image were uploaded to the list with the correct size
		genMeth.isElementVisibleNative(By.name(iosData.UploadExistingImage_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.UploadExistingVideo_Name), driver);

		// Open the Image & verify it displays
		genMeth.clickName(driver, iosData.UploadExistingImage_Name);

		// Make sure that the "Image not available" text doesn't displayed
		genMeth.isElementInvisibleTextNative(By.name(iosData.ImageNotAvailable_Name),iosData.ImageNotAvailable_Name, driver);

		// Make sure that the image title displayed
		genMeth.isElementVisibleNative(By.name(iosData.UploadExistingImage_Name), driver);
		
		//Take Screenshot verifying that the image UI is fine
		genMeth.takeScreenShotNative(driver, "positive_uploaded_IMG_0004");
		
		genMeth.isElementInvisibleNative(By.name(iosData.UploadExistingImage_Name), driver);
		genMeth.clickXpth(driver,"//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]");
		driver.findElementByName(iosData.BTNback_Name).click();

		// Open the video & verify that the title displayed
		genMeth.clickName(driver, iosData.UploadExistingVideo_Name);
		genMeth.isElementVisibleNative(By.name(iosData.UploadExistingVideo_Name), driver);
		genMeth.takeScreenShotNative(driver, "positive_IMG_01188.MOV");
		genMeth.clickXpth(driver, "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAImage[1]");
		driver.findElementByName(iosData.BTNback_Name).click();
		
		// Cancel the Delete image
		genMeth.clickName(driver, iosData.BTNedit_Name);
		genMeth.clickName(driver, iosData.UploadExistingImage_Name);
		genMeth.clickName(driver, iosData.UploadExistingVideo_Name);
		genMeth.clickName(driver, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Make sure that the image wasn't deleted
		genMeth.isElementVisibleNative(By.name(iosData.UploadExistingImage_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.UploadExistingVideo_Name), driver);

		// Now delete the image for real
		genMeth.clickName(driver, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, iosData.BTNdelete_Name);
		Thread.sleep(1000);

		// Make sure that the image was deleted
		genMeth.isElementInvisibleNative(By.name(iosData.UploadExistingImage_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.UploadExistingVideo_Name), driver);
		
		genMeth.clickName(driver, iosData.BTNback_Name);
		// delete the folder
		Thread.sleep(1000);
		genMeth.clickName(driver, iosData.BTNedit_Name);
		genMeth.clickName(driver, currentDateFolder );
		genMeth.clickName(driver, iosData.BTNdeleteOn_Name);
		genMeth.clickName(driver, iosData.BTNdelete_Name);
		genMeth.isElementInvisibleNative(By.name(currentDateFolder ), driver);
		
		// go to startup screen
		genMeth.clickName(driver, iosData.BTNleft_Name);

	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Test TOUR for New accounts and for upgrade accounts",
			groups = { "Sanity Native iOS" })
	public void testTour() throws Exception, Throwable {

		
		 // =============================================================== 
		 //        Tour for Free Account
		 //===============================================================
		 
		// ====== SKIP for Go Unlimited screen ===== - Login with Free/Limited account & check the tour display & text
		
		
		driver = genMeth.killAppIos(driver);
		genMeth.clickName(driver, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, iosData.userLimited_name);
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignin_Name);
		
		// Make sure that the tour Never lose a photo display properly with full text
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseAPhoto_Name) , driver);
		genMeth.isElementVisibleNative( By.name(iosData.NeverLoseaPhotoFullText_Name) , driver);

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimply_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimplyFullText_Name),driver);
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative( By.name(iosData.UnlimitedProtection_Name) , driver);
		genMeth.isElementVisibleNative( By.name(iosData.UpgradeTour_Name) , driver);
		
		// Skip- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
		genMeth.clickName(driver, iosData.BTNskip_Name);

		// Verify that the backup tour text is displayed
		genMeth.isElementVisibleNative (By.name(iosData.Backup_Name) , driver);
		genMeth.isElementVisibleNative(By.name(iosData.BackupTourText_Name) , driver);
		genMeth.clickName(driver, iosData.BTNcontinue_Name);
		
		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
		Thread.sleep(1000);

		// ===== X BUTTON for Go Unlimited screen ====== - Login with Free/Limited account & check the tour display & text
		
		
		driver = genMeth.killAppIos(driver);

		// Login with an existing account & check the tour
		genMeth.clickName(driver, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, iosData.userLimited_name);
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignin_Name);
		

		// Make sure that the tour Never lose a photo display properly with full text
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseAPhoto_Name) , driver);
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseaPhotoFullText_Name) , driver);

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisibleNative( By.name(iosData.TransferPhonesSimply_Name) , driver);
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimplyFullText_Name) , driver);
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative( By.name(iosData.UnlimitedProtection_Name) , driver);
		genMeth.isElementVisibleNative( By.name(iosData.UpgradeTour_Name) , driver);

		// X BUTTON- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
		genMeth.clickName(driver, iosData.BTNxInTour_Name);

		// Verify that the backup tour text is displayed
		genMeth.isElementVisibleNative( By.name(iosData.Backup_Name),driver);
		genMeth.isElementVisibleNative( By.name(iosData.BackupTourText_Name),driver);
		genMeth.clickName(driver, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
		Thread.sleep(1000);

		// ====== GO UNLIMITED BUTTON ====- Login with Free/Limited account & check the tour display & text
		
		driver = genMeth.killAppIos(driver);		
		genMeth.clickName(driver, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, iosData.userLimited_name);
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignin_Name);
		
		// Make sure that the tour "Never lose a photo" display properly with full text
		genMeth.isElementVisibleNative( By.name(iosData.NeverLoseAPhoto_Name) , driver);
		genMeth.isElementVisibleNative( By.name(iosData.NeverLoseaPhotoFullText_Name), driver);

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisibleNative( By.name(iosData.TransferPhonesSimply_Name) , driver);
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimplyFullText_Name) , driver);
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative(By.name(iosData.UnlimitedProtection_Name) , driver);
		genMeth.isElementVisibleNative(By.name(iosData.UpgradeTour_Name) , driver);

		// Go Unlimited button- [Need to test 3 options (X button, Go Unlimited button & Skip button)]
		genMeth.clickName(driver, iosData.BTNgoUnlimited_Name);
		genMeth.isElementVisibleNative(By.name(iosData.UpgradeAccount_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.UpgardeAccountText_Name), driver);
		genMeth.isElementVisibleNative( By.name(iosData.BTNupgrade_Name) , driver);

		genMeth.clickName(driver, iosData.BTNdismiss_Name);
		genMeth.clickName(driver, iosData.BTNskip_Name);
		genMeth.clickName(driver, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContacts(iosData);
		
		// Open the Upgrade Account screen from LSM
		genMeth.clickName(driver, iosData.BTNupgrade_Name);
		genMeth.isElementVisibleNative(By.name(iosData.UpgradeAccount_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.UpgardeAccountText_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.BTNupgrade_Name), driver);
		
		//open the Terms an conditions
		genMeth.clickName(driver, iosData.iconTermsAndCondition_Name);
		
		//Make sure that the terms of service page is open successfully
		genMeth.isElementVisibleNative(By.name(iosData.TermsOfService_Name), driver);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdismiss_Name);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
		
		
	// =====================================================
	//     Tour for Unlimited Account
	// =====================================================
		 
		driver = genMeth.killAppIos(driver);
		genMeth.clickName(driver, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, iosData.userUnlimited_name);
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignin_Name);

		// Make sure that the tour "Never lose a photo" display properly with full text
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseAPhoto_Name) , driver);
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseaPhotoFullText_Name) , driver);

		driver.swipe(270, 265, 55, 265, 1000);
		// Make sure that the tour Transfer phone simply is displayed properly with full text
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimply_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimplyFullText_Name),driver);
		driver.swipe(270, 265, 55, 265, 1000);
		Thread.sleep(1000);

		// Verify that the backup tour text is displayed
		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.BackupTourText_Name),driver);
		genMeth.clickName(driver, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Sign up- Create new user (Negetive positive test), Privacy Policy, TRUSTe",
			groups = { "Sanity Native iOS" })
	public void createNewUser() throws Exception, Throwable {

		String randomName = genMeth.randomString();
		String currentDateFolder = genMeth.currentTime();
		genMeth.signOutFromStartupIphone5( driver , iosData);

		// Create a new user & Login
		genMeth.clickName(driver, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, "meny@" + randomName + ".com");
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignUpForFree_Name);
		
		By byNameNeverLosePhoto = By.name(iosData.NeverLoseAPhoto_Name);
		genMeth.isElementVisibleNative(byNameNeverLosePhoto, driver);
		driver.swipe(270, 265, 55, 265, 1000);
		By byNameTransferPhonesSimply = By.name(iosData.TransferPhonesSimply_Name);
		genMeth.isElementVisibleNative(byNameTransferPhonesSimply,driver);
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative(By.name(iosData.UnlimitedProtection_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.UpgradeTour_Name),driver);
		genMeth.clickName(driver, "UIAccessoryButtonX");

		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name),driver);
		genMeth.clickName(driver, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the home screen is open with the LSM (left side menu)
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);

		// Make sure that the "Anything that is backed up..." screen display when no files were uploaded yet to the cloud
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.isElementVisibleNative(By.name(iosData.CloudEmptyFilesScreen_Name), driver);

		// Attempt to Create new user with *bad format (* incorrect email format)
		driver = genMeth.killAppIos(driver);
		
		genMeth.clickName(driver, iosData.BTNsignUp_Name);
		// check that the Back button works properly
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, "meny");
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignUpForFree_Name);
		genMeth.takeScreenShotNative(driver, "positive_bad_email_format");

		// privacy policy
		genMeth.clickName(driver, iosData.LinkPrivacyPolicy_Name);
		genMeth.isElementVisibleNative(By.name(iosData.PrivacyPolicyUrl_Name), driver);
		
		driver = genMeth.killAppIos(driver);
		
		// TRUSe link
		genMeth.clickName(driver, iosData.BTNsignUp_Name);
		genMeth.clickName(driver, iosData.LinkTRUSTe_Name);
		genMeth.isElementVisibleNative(By.id(iosData.TrusteUrl_Name), driver);
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "login with bad/missing credentials , forgot password (negative & positive)",
			groups = { "Sanity Native iOS" })
	public void badCredentials() throws Exception, Throwable {

		
		String randomName = genMeth.randomString();
		genMeth.signOutFromStartupIphone5(driver , iosData);

		// Login with bad credentials
		genMeth.clickName(driver, iosData.BTNalreadyHaveAnAccount_name);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, iosData.userUnlimited_name);
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.badPassword);
		genMeth.clickName(driver, iosData.BTNsignin_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BadCredentialsPopup),driver);
		genMeth.clickName(driver, iosData.BTNok_Name);

		// Forgot your password Negative (attempt to restore password with a non existing email)
		genMeth.clickName(driver, iosData.LinkForgotYourPassword_Name);
		genMeth.clickName(driver, iosData.iconClearText_Name);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, randomName );
		genMeth.clickName(driver, iosData.BTNsubmit_Name);
		genMeth.isElementVisibleNative(By.name(iosData.ForgotPasswordErrorPopup_Name),driver);
		genMeth.clickName(driver, iosData.BTNdismiss_Name);
		genMeth.clickId(driver, randomName);
		genMeth.clickName(driver, iosData.BTNclearTextIcon_Name);
		
		// Forgot your password Positive (attempt to restore password with an existing email)
		genMeth.sendId(driver,iosData.TEXTFIELDemail_Id,iosData.userUnlimited_name);
		genMeth.clickName(driver, iosData.BTNsubmit_Name);
		genMeth.isElementVisibleNative(By.name(iosData.ResetEmailPopup_Name),driver);
		genMeth.clickName(driver, iosData.BTNok_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNsignin_Name), driver);

	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Search functionality & filter",
			groups = { "Sanity Native iOS" })
	public void search() throws Exception, Throwable {
		
		String Random = genMeth.randomString();	

		//Search in ROOT for a folder
		WebElement search = genMeth.returnId(driver, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchFolderFromRoot_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchFolderFromRoot_Name),driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Search in ROOT for an image
		search.click();
		search.sendKeys(iosData.SearchImageFromRoot_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchImageFromRoot_Name), driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Search in ROOT for empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoFilesFound_Name),driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		
		// Search in FOLDER for a folder
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, iosData.SearchMainFolder_Name);
		search = genMeth.returnId(driver, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchFolderFromFolder_Name);
		genMeth.isElementVisibleNative(By.name(iosData.tabCurrentFolder_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.SearchFolderFromFolder_Name),driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Search in FOLDER for an image
		search.click();
		search.sendKeys(iosData.SearchImageFromFolder_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchImageFromFolder_Name), driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Search in FOLDER for empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoFilesFound_Name),driver);
		genMeth.clickName(driver, iosData.iconClearText_Name);
		
		// Search in POGOPLUG for a folder
		genMeth.clickName(driver, iosData.tabPogoplugCloud_Name);
		search.click();
		search.sendKeys(iosData.SearchFolderFromRoot_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchFolderFromRoot_Name),driver);
		genMeth.clickName(driver, iosData.tabCurrentFolder_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.SearchFolderFromRoot_Name),driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		
		// Search in POGOPLUG for an image
		search.click();
		search.sendKeys(iosData.SearchImageFromRoot_Name);
		genMeth.clickName(driver, iosData.tabPogoplugCloud_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchImageFromRoot_Name), driver);
		genMeth.clickName(driver, iosData.iconClearText_Name);
		
		// Search in POGOPLUG for empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoFilesFound_Name),driver);
		genMeth.clickName(driver, iosData.iconClearText_Name);
		
		//   Music Player  
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.clickName(driver, iosData.BTNmusicPlayer_Name);
		
		//Search in Songs A song	
		search = genMeth.returnId(driver, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInSong_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchSongInSong_Name),driver);
		driver.tap(1, 285, 40, 2);

		//Search in songs empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoResults_Name),driver);
		driver.tap(1, 285, 40, 2);

		//Search in Artists song
		genMeth.clickName(driver, iosData.iconArtists_Name);
		search = genMeth.returnId(driver, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInArtists_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchSongInArtists_Name),driver);
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisibleNative(By.name(iosData.NoResults_Name), driver);
		
		//Search in Artists empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoResults_Name),driver);
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisibleNative(By.name(iosData.NoResults_Name), driver);
		
		//Search in Albums song
		genMeth.clickName(driver, iosData.iconAlbums_Name);
		search = genMeth.returnId(driver, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInAlbums_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchSongInAlbums_Name),driver);
		driver.tap(1, 285, 40, 2);
		
		//Search in Albums empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoResults_Name),driver);
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisibleNative(By.name(iosData.NoResults_Name), driver);
		
		//Search in Genres song 
		genMeth.clickName(driver, iosData.iconGenres_Name);
		search = genMeth.returnId(driver, iosData.TEXTFIELDsearch_Id);
		search.click();
		search.sendKeys(iosData.SearchSongInGenres_Name);
		genMeth.isElementVisibleNative(By.name(iosData.SearchSongInGenres_Name),driver);
		driver.tap(1, 285, 40, 2);
		
		//Search in Genres empty results
		search.click();
		search.sendKeys(Random);
		genMeth.isElementVisibleNative(By.name(iosData.NoResults_Name),driver);
		driver.tap(1, 285, 40, 2);
		genMeth.isElementInvisibleNative(By.name(iosData.NoResults_Name), driver);
		
		//search in add users screen
		genMeth.clickName(driver,iosData.BTNleft_Name);
		
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, iosData.BTNshareOn_Name);
		genMeth.clickName(driver, iosData.BTNaddUsers_Name);
		
		genMeth.handleAccessPhotosContacts(iosData);
				
		genMeth.isElementVisibleNative(By.name(iosData.BTNaddUsers_Name), driver);
		
		//search for a contact
		genMeth.sendId(driver, iosData.TEXTFIELDsearch_Id, iosData.userUnlimited_name );
		genMeth.isElementVisibleNative(By.name(iosData.userUnlimited_name), driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.userUnlimited_name), driver);
		
		//no results search using clear & cancel 
		genMeth.sendId(driver, iosData.TEXTFIELDsearch_Id, Random );
		genMeth.isElementVisibleNative(By.name(iosData.NoResults_Name), driver);
		
		//Back to start up screen
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.clickName(driver, iosData.BTNleft_Name);
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Passcode",
			groups = { "Sanity Native iOS" })
	public void settingsPasscodeSanity() throws Exception, Throwable {

		// Cancel the Passcode screen
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
		genMeth.clickName(driver, iosData.BTNpassCodeLock_Name);
		genMeth.isElementVisibleNative(By.name(iosData.PasscodeLock_Name),driver);
		genMeth.clickXpth(driver, iosData.TOGGLEpasscodeLock_Xpth);
		genMeth.isElementVisibleNative(By.name(iosData.EnterPasscode_Name),driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);

		// Attempt to create wrong Passcode
		genMeth.isElementVisibleNative(By.name(iosData.PasscodeLock_Name),driver);
		genMeth.clickXpth(driver, iosData.TOGGLEpasscodeLock_Xpth);
		genMeth.isElementVisibleNative(By.name(iosData.EnterPasscode_Name),driver);		
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisibleNative(By.name(iosData.PinCodeReEnter_Name),driver);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton2_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);

		genMeth.clickName(driver, iosData.PinCodeNotMatch_Name);

		// Create a correct Passcode
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisibleNative(By.name(iosData.PinCodeReEnter_Name),driver);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisibleNative(By.name(iosData.BTNchangePasscode_Name),driver);
		genMeth.clickXpth(driver, iosData.TOGGLErequireImmediately_Xpth);

		// make sure that the passcode initiated properly
		driver.lockScreen(2);
		driver.swipe(30, 300, 250, 30, 500);
		genMeth.isElementVisibleNative(By.name(iosData.EnterPasscode_Name),driver);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		genMeth.clickId(driver, iosData.BTNpinButton1_Id);
		
		genMeth.isElementVisibleNative(By.name(iosData.BTNchangePasscode_Name),driver);

		// Disable the passcode
		genMeth.clickXpth(driver, iosData.TOGGLEpasscodeLock_Xpth);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNchangePasscode_Name), driver);

		// make sure that the passcode isn't initiated
		driver.lockScreen(2);
		driver.swipe(30, 300, 250, 30, 500);
		genMeth.isElementVisibleNative(By.xpath(iosData.TOGGLEpasscodeLock_Xpth), driver);
		// Go to startup screen
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
		


	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Save Login",
			groups = { "Sanity Native iOS" })
	public void settingsSaveLoginSanity() throws Exception, Throwable {

		// Save Login = true
		driver.closeApp();
		driver.launchApp();
		genMeth.clickId(driver, iosData.Settings_Name);
		genMeth.isElementVisibleNative(By.name(iosData.TOGGLEsaveLogin_Name), driver);
		
		// Save Login = false
		genMeth.clickName(driver, iosData.TOGGLEsaveLogin_Name);
		driver.closeApp();
		driver.launchApp();
		genMeth.isElementVisibleNative(By.name(iosData.BTNalreadyHaveAnAccount_name), driver);

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Backup Enable/disable without upload in the background",
			groups = { "Sanity Native iOS" })
	public void settingsBackupEnableDisable() throws Exception, Throwable {

		// Disable the Backup
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.clickName(driver, iosData.BTNon_Name);
		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name), driver);
		genMeth.clickName(driver, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Disabled_Name),driver);

		// "Enable" the backup form LSM (Left Side Menu) & cancel it
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.isElementVisibleNative(By.name(iosData.ProtectYourPhotos_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.AutomaticallyBackUpPhotosAndVideosToYourAccount_Name),driver);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);

		// Enable the Backup form LSM (Left Side Menu)
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name),driver);

		// Enable the backup from settings (first enable it & then disable it from backup screen)
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.clickName(driver, iosData.BTNon_Name);
		genMeth.clickName(driver, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Disabled_Name),driver);

		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.clickXpth(driver, "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[2]");
		genMeth.clickName(driver, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name),driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name),driver);

	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Backup Enable/disable *with upload in the background",
			groups = { "Sanity Native iOS" })// , dependsOnMethods={"successTest"})
	public void settingsBackupEnableDisableDuringUpload() throws Exception,Throwable {

		// login with new account & enable/disable the backup from Tour/Settings/LSM/Photo Gallery
		iosData = genMeth.iOSelementInit(langEng);
		String randomName = genMeth.randomString();
		String currentDateFolder = genMeth.currentTime();
		
		genMeth.clickName(driver, iosData.Settings_Name);
		driver.swipe(170, 350, 170, 150, 500);
		Thread.sleep(1000);
		driver.swipe(170, 450, 170, 250, 500);
		driver.swipe(170, 550, 170, 350, 500);
		genMeth.clickName(driver, iosData.BTNsignOut_Name);	
		
		genMeth.clickName(driver, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, "meny@" + randomName + ".com");
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignUpForFree_Name);
		
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseAPhoto_Name),driver);
		driver.swipe(270, 265, 55, 265, 1000);
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimply_Name),driver);
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative(By.name(iosData.UnlimitedProtection_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.UpgradeTour_Name),driver);
		genMeth.clickName(driver, "UIAccessoryButtonX");

		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name),driver);
		
		//Disable the backup from TOUR
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		
		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the backup is Disabled in LSM
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
				
		//Enable backup form LSM
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.clickName(driver, iosData.BTNenable_Name);
		
		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the backup is running
		genMeth.isElementVisibleNative(By.name(iosData.iconInProgress_Name), driver);
		
		//Disable the backup from SETTINGS
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.clickName(driver, iosData.BTNon_Name);
		genMeth.clickName(driver, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		
		//verify that the backup was disabled in LSM
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Disabled_Name), driver);
		
		//Enable backup from TIMELINE 
		genMeth.clickName(driver, iosData.BTNphoneGallery_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.takeScreenShotNative(driver, "Positive_TimelineThumbnail_settingsBackupEnableDisableDuringUpload");
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNwifiAndCellular_Name), driver);
		genMeth.clickName(driver, iosData.BTNenable_Name);
		
		// check that the backup disabled notification isn't displayed
		genMeth.isElementVisibleNative(By.name(iosData.iconInProgress_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);		
		
		// Disable the upload form settings
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.clickName(driver, iosData.BTNon_Name);
		genMeth.clickName(driver, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		
		//Enable backup from VIDEOS 
		genMeth.clickName(driver, iosData.BTNphoneGallery_Name);
		genMeth.clickName(driver, iosData.BTNvideos_Name);
		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.takeScreenShotNative(driver, "Positive_VideoThumbnail_settingsBackupEnableDisableDuringUpload");
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNwifiAndCellular_Name), driver);
		genMeth.clickName(driver, iosData.BTNenable_Name);

		// check that the backup disabled notification isn't displayed
		genMeth.isElementVisibleNative(By.name(iosData.iconInProgress_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name),driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name),driver);	
		
		
		// Disable the upload form settings
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.clickName(driver, iosData.BTNon_Name);
		genMeth.clickName(driver, iosData.TOGGLEcameraRoll_Name);
		genMeth.clickName(driver, iosData.BTNback_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		
		
		// Enable backup from ALBUMS
		genMeth.clickName(driver, iosData.BTNphoneGallery_Name);
		genMeth.clickName(driver, iosData.BTNalbums_Name);
		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.takeScreenShotNative(driver, "Positive_AlbumsThumbnail_settingsBackupEnableDisableDuringUpload");
		genMeth.clickName(driver, iosData.BTNenable_Name);
		genMeth.isElementVisibleNative(By.name(iosData.BTNwifiAndCellular_Name), driver);
		genMeth.clickName(driver, iosData.BTNenable_Name);


		// check that the backup disabled notification isn't displayed
		genMeth.isElementVisibleNative(By.name(iosData.iconInProgress_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name),driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name),driver);	

		// Check that the backup has finished at ALBUMS,VIDEOS,PHOTO GALLERY & LSM
		genMeth.waitForElementToBeInvisible(driver, By.name(iosData.BTNenable_Name), 5);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name), driver);
		
		genMeth.clickName(driver, iosData.BTNvideos_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);
		
		genMeth.clickName(driver, iosData.BTNtimeline_Name_name);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.Disabled_Name), driver);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNenable_Name), driver);	
		
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.waitForElementToBeVisible(driver, By.name(iosData.Completed_Name), 8);
		
		// verify that the images were uploaded to the cloud
		genMeth.clickName(driver, iosData.BTNphoneGallery_Name);		
		genMeth.clickXpth(driver, "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[2]");
		
		// i am using the swipe (position is relevant to iPhone5 only) since click with xpath is failing (seems like bug in the appium environment)
		driver.swipe(150, 150, 150, 150, 500);
		
		// Make sure that the "Image not available" text doesn't displayed
		genMeth.isElementInvisibleTextNative(By.name(iosData.ImageNotAvailable_Name),iosData.ImageNotAvailable_Name, driver);
		Thread.sleep(2000);
		genMeth.takeScreenShotNative(driver, "Positive_ImagePreview_settingsBackupEnableDisableDuringUpload");
		
		
	}

	@Test(enabled = true, testName = "Sanity Tests", description = " Backup running in background -> make sure process keeps alive and completes its queue even in background AND if taking new shots they are automatically backed up",
			groups = { "Sanity Native iOS" })
	public void backupInBackground() throws Exception, Throwable {

		String currentDateFolder = genMeth.currentTime();
		webElementiOS iosData = genMeth.iOSelementInit(langEng);
		String randomName = genMeth.randomString();
		
		//Login with new account (*backup will initiate)
		genMeth.signOutFromStartupIphone5(driver , iosData);
		genMeth.clickName(driver, iosData.BTNsignUp_Name);
		genMeth.sendId(driver, iosData.TEXTFIELDnameOptional_Id, "meny:" + currentDateFolder);
		genMeth.sendId(driver, iosData.TEXTFIELDemail_Id, "meny@" + randomName + ".com");
		genMeth.sendId(driver, iosData.TEXTFIELDpass_Id, iosData.password);
		genMeth.clickName(driver, iosData.BTNsignUpForFree_Name);
		
		genMeth.isElementVisibleNative(By.name(iosData.NeverLoseAPhoto_Name),driver);
		driver.swipe(270, 265, 55, 265, 1000);
		genMeth.isElementVisibleNative(By.name(iosData.TransferPhonesSimply_Name),driver);
		driver.swipe(270, 265, 55, 265, 1000);

		// Verify that the go unlimited tour text is displayed
		genMeth.isElementVisibleNative(By.name(iosData.UnlimitedProtection_Name),driver);
		genMeth.isElementVisibleNative(By.name(iosData.UpgradeTour_Name),driver);
		genMeth.clickName(driver, "UIAccessoryButtonX");

		genMeth.isElementVisibleNative(By.name(iosData.Backup_Name),driver);
		
		//Enable the backup from Tour
		genMeth.clickName(driver, iosData.BTNcontinue_Name);

		genMeth.handleAccessPhotosContacts(iosData);
		
		// verify that the backup is Enable & running
		genMeth.clickName(driver,iosData.BTNleft_Name);
		genMeth.clickName(driver,iosData.BTNleft_Name);
		genMeth.isElementVisibleNative(By.name(iosData.iconInProgress_Name), driver);

		//Go to background/Sleep & wait 60 seconds
		driver.lockScreen(60);
		
		//Bring App back to foreground & make sure that backup has finished successfully
		genMeth.waitForElementToBeInvisible(driver, By.name(iosData.iconInProgress_Name), 8);
		
		//Verify that the backup is completed
		genMeth.isElementVisibleNative(By.name(iosData.Completed_Name), driver);
		
		// Make sure that a random image is open successfully
		genMeth.clickName(driver, iosData.BTNphoneGallery_Name);
		genMeth.clickXpth(driver,"//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]");
		driver.tap(1, 60, 120, 1);

		// Make sure that the "Image not available" text doesn't displayed
		Thread.sleep(2000);
		genMeth.isElementInvisibleTextNative(By.name(iosData.ImageNotAvailable_Name),iosData.ImageNotAvailable_Name, driver);
		genMeth.takeScreenShotNative(driver, "positive_backupInBackground");

	}

	@Test(enabled = true, testName = "Sanity Tests", description = "Switching from Foreground to Background and vice versa use cases",
			groups = { "Sanity Native iOS" })
	public void foregroundBackgroundSwith() throws Exception, Throwable {

		//Take the app to background & foreground x times
		genMeth.backgroundToForeground(driver, 10);
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);
		
		//Take the app to sleep/lock  x times
		genMeth.lockUnlock(driver,10);
		genMeth.isElementVisibleNative(By.name(iosData.Settings_Name), driver);

	}
		@Test(enabled = true , testName = "Sanity Tests" , description = " Add remove files from favorites" ,
				groups = { "Sanity Native iOS"} )
		public void Favorites() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
			// open favorites & make sure that it is empty
			genMeth.clickName(driver, iosData.BTNfavorites_Name);
			genMeth.isElementVisibleNative(By.name(iosData.EmptyFavorites_Name), driver);
			
			//positive empty favorites screenshot
			genMeth.takeScreenShotNative(driver, iosData.FavoritesTitle_Name);
			genMeth.clickName(driver, iosData.BTNleft_Name);
			genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
			genMeth.clickName(driver, iosData.FavoritesTitle_Name);
			Thread.sleep(1000);
			
			//Add image/video/song to favorites
			genMeth.clickName(driver, iosData.FavoritesMp3_Name);
			genMeth.clickName(driver, iosData.iconAddToFavorites_Name);
			genMeth.isElementVisibleNative(By.name(iosData.iconRemoveFromFavorites_Name), driver);
			genMeth.clickName(driver, iosData.BTNback_Name);
			
			genMeth.clickName(driver, iosData.FavoritesMov_Name);
			Thread.sleep(1000);
			driver.findElementByName(iosData.iconAddToFavorites_Name).click();
			driver.findElementByName(iosData.BTNback_Name).click();
			
			genMeth.clickName(driver, iosData.FavoritesPng_Name);
			Thread.sleep(1000);
			driver.findElementByName(iosData.iconAddToFavorites_Name).click();
			driver.findElementByName(iosData.BTNback_Name).click();	
			
			//Make sure that it is displayed in favorites
			genMeth.clickName(driver, iosData.BTNback_Name);
			genMeth.clickName(driver, iosData.BTNleft_Name);
			genMeth.clickName(driver, iosData.BTNfavorites_Name);
			genMeth.isElementVisibleNative(By.name(iosData.FavoritesMov_Name), driver);
			genMeth.isElementVisibleNative(By.name(iosData.FavoritesMp3_Name), driver);
			genMeth.isElementVisibleNative(By.name(iosData.FavoritesPng_Name), driver);
				
			// remove the files from favorites & make sure that the empty screen display
			genMeth.clickName(driver, iosData.BTNedit_Name);
			genMeth.clickName(driver, iosData.FavoritesMp3_Name);
			genMeth.clickName(driver, iosData.FavoritesMov_Name);
			genMeth.clickName(driver, iosData.FavoritesPng_Name);
			
			genMeth.clickName(driver, iosData.BTNremoveFavorites_Name);
			genMeth.isElementInvisibleNative(By.name(iosData.FavoritesMov_Name), driver);
			genMeth.isElementInvisibleNative(By.name(iosData.FavoritesMp3_Name), driver);
			genMeth.isElementInvisibleNative(By.name(iosData.FavoritesPng_Name), driver);
			genMeth.isElementVisibleNative(By.name(iosData.EmptyFavorites_Name), driver);
			
			//Now remove from favorites from Toolbar 
			
			genMeth.clickName(driver, iosData.BTNleft_Name);
			genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
			genMeth.clickName(driver, iosData.FavoritesTitle_Name);
			Thread.sleep(1000);
			
			//Add image/video/song to favorites
			genMeth.clickName(driver, iosData.FavoritesMp3_Name);
			genMeth.clickName(driver, iosData.iconAddToFavorites_Name);
			genMeth.isElementVisibleNative(By.name(iosData.iconRemoveFromFavorites_Name), driver);
			genMeth.clickName(driver, iosData.BTNback_Name);
			
			genMeth.clickName(driver, iosData.FavoritesMov_Name);
			Thread.sleep(1000);
			driver.findElementByName(iosData.iconAddToFavorites_Name).click();
			driver.findElementByName(iosData.BTNback_Name).click();
			
			genMeth.clickName(driver, iosData.FavoritesPng_Name);
			Thread.sleep(1000);
			driver.findElementByName(iosData.iconAddToFavorites_Name).click();
			driver.findElementByName(iosData.BTNback_Name).click();	
			
			//Make sure that it is displayed in favorites
			genMeth.clickName(driver, iosData.BTNback_Name);
			genMeth.clickName(driver, iosData.BTNleft_Name);
			genMeth.clickName(driver, iosData.BTNfavorites_Name);
			genMeth.isElementVisibleNative(By.name(iosData.FavoritesMov_Name), driver);
			genMeth.isElementVisibleNative(By.name(iosData.FavoritesMp3_Name), driver);
			genMeth.isElementVisibleNative(By.name(iosData.FavoritesPng_Name), driver);
			
			genMeth.clickName(driver, iosData.FavoritesMp3_Name);
			genMeth.clickName(driver, iosData.iconRemoveFromFavorites_Name);
			genMeth.clickName(driver, iosData.BTNback_Name);
			genMeth.isElementInvisibleNative(By.name(iosData.FavoritesMp3_Name), driver);
			
			genMeth.clickName(driver, iosData.FavoritesMov_Name);
			Thread.sleep(1000);
			driver.findElementByName(iosData.iconRemoveFromFavorites_Name).click();
			driver.findElementByName(iosData.BTNback_Name).click();
			genMeth.isElementInvisibleNative(By.name(iosData.FavoritesMov_Name), driver);
			
			genMeth.clickName(driver, iosData.FavoritesPng_Name);
			Thread.sleep(1000);
			driver.findElementByName(iosData.iconRemoveFromFavorites_Name).click();
			genMeth.isElementInvisibleNative(By.name(iosData.FavoritesPng_Name), driver);
			
			// go back to startup page
			genMeth.clickName(driver, iosData.BTNleft_Name);
			Thread.sleep(1000);
			
			
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
		 */
	
		
		//create restore snapshot
		
		
		
	@Test(enabled = true, testName = "Sanity Tests", description = "Adding & removing team folders",
			groups = { "Sanity Native iOS now" })
	public void addRemoveTeamFolders() throws Exception, Throwable {
		
		webElementiOS iosData = genMeth.iOSelementInit(langEng);

		//Share user with team folder 
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, iosData.BTNedit_Name);
		genMeth.clickName(driver, iosData.FavoritesTitle_Name);
		genMeth.clickName(driver, iosData.BTNshareOn_Name);
		boolean isShared = genMeth.checkIsElementVisibleNative(driver, By.name(iosData.BTNremoveAllUsers_Name));
		if (isShared == true) {

			genMeth.clickName(driver, iosData.BTNremoveAllUsers_Name);
			genMeth.clickName(driver, iosData.BTNshareOn_Name);
		}
		genMeth.clickName(driver, iosData.BTNaddUsers_Name);
		genMeth.handleAccessPhotosContacts(iosData);
		genMeth.isElementVisibleNative(By.name(iosData.BTNaddUsers_Name), driver);
		
		genMeth.sendId(driver, iosData.TEXTFIELDsearch_Id, iosData.userAutomation2_Name );
		genMeth.isElementVisibleNative(By.name(iosData.userAutomation2_Name), driver);
		genMeth.clickName(driver, iosData.userAutomation2_Name);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.isElementVisibleNative(By.id("1"), driver);
		genMeth.takeScreenShotNative(driver, "positive_teamFolderSharedUsersNumber");
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.clickName(driver, iosData.BTNleft_Name);
		
		// login with the SHARED user & make sure that the team folder was added & can be open
		genMeth.signOutFromStartupIphone5(driver, iosData);
		genMeth.loginIos(iosData, iosData.userAutomation2_Name);
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, iosData.BTNteamFolders_Name);
		genMeth.isElementVisibleNative(By.name(iosData.FavoritesTitle_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.BTNteamFolders_Name), driver);
		
		//Cancel remove share
		genMeth.clickName(driver, iosData.BTNedit_Name);
		genMeth.clickName(driver, iosData.FavoritesTitle_Name);
		genMeth.clickName(driver, iosData.BTNremoveShare_Name);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.isElementVisibleNative(By.name(iosData.FavoritesTitle_Name), driver);
		
		//Remove share folder from the SHARED user
		genMeth.clickName(driver, iosData.BTNremoveShare_Name);
		//genMeth.clickName(driver, iosData.BTNremoveShare_Name);
		genMeth.clickXpth(driver, "//UIAApplication[1]/UIAWindow[4]/UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]");
		
		//Make sure that the shared folder was removed
		genMeth.isElementInvisibleNative(By.name(iosData.FavoritesTitle_Name), driver);
		genMeth.isElementVisibleNative(By.name(iosData.EmptyFolder_Name), driver);
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.signOutFromStartupIphone5(driver, iosData);
		
		// login with the SHARING user & make sure that the folder isn't shared anymore
		genMeth.loginIos(iosData, iosData.userUnlimited_name);
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNteamFolders_Name), driver);
				
		//Remove shared folder from the SHARING user
		genMeth.clickName(driver, iosData.BTNedit_Name);
		genMeth.clickName(driver, iosData.FavoritesTitle_Name);
		genMeth.clickName(driver, iosData.BTNshareOn_Name);
		genMeth.clickName(driver, iosData.BTNaddUsers_Name);
		genMeth.handleAccessPhotosContacts(iosData);
		genMeth.isElementVisibleNative(By.name(iosData.BTNaddUsers_Name), driver);
		
		//Share user with team folder & then remove share
		genMeth.sendId(driver, iosData.TEXTFIELDsearch_Id, iosData.userAutomation2_Name );
		genMeth.isElementVisibleNative(By.name(iosData.userAutomation2_Name), driver);
		genMeth.clickName(driver, iosData.userAutomation2_Name);
		genMeth.clickName(driver, iosData.BTNcancel_Name);
		genMeth.clickName(driver, iosData.BTNdone_Name);
		genMeth.isElementVisibleNative(By.id("1"), driver);
		genMeth.clickName(driver, iosData.BTNshareOn_Name);
		genMeth.clickName(driver, iosData.BTNremoveAllUsers_Name);
		genMeth.takeScreenShotNative(driver, "positive_teamFolderSharedUsersRemoveNumber");
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.clickName(driver, iosData.Settings_Name);
		genMeth.signOutFromStartupIphone5(driver, iosData);
		
		//Make sure that the folder isn't shared under the SHARED account
		genMeth.loginIos(iosData, iosData.userAutomation2_Name);
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		genMeth.isElementInvisibleNative(By.name(iosData.BTNteamFolders_Name), driver);
		genMeth.clickName(driver, iosData.BTNleft_Name);
		genMeth.signOutFromStartupIphone5(driver, iosData);
		genMeth.loginIos(iosData, iosData.userUnlimited_name);
		genMeth.clickName(driver, iosData.BTNfileExplorer_Name);
		
	}
	
	
	@Test(enabled = false, testName = "Sanity Tests", description = "verify that the delete from favorites/Albums/ use cases", groups = { "Sanity Native iOS" })
	public void deleteFrom () throws Exception, Throwable {

		// ??????????????????
		Thread.sleep(1000);
		String currentDateFolder = genMeth.currentTime();
		webElementiOS iosData = genMeth.iOSelementInit(langEng);
		
		//add team folder for user (automation2@test.com)
		
		// login with user (automation2@test.com) & make sure that the team folder was added & can be open
		
		// login with master user & remove the team folder for user (automation2@test.com)
		
		//login with (automation2@test.com) make sure that the team folder was removed
		
	}
	

	@AfterSuite(alwaysRun = true)
	public void sendMail() throws Exception {
		driver.removeApp("com.cloudengines.pogoplug");
		try {
			driver.quit();
		} catch (Exception x) {
			// For iPhone4
		}
		/*
		 * SendResults sr = new SendResults("elicherni444@gmail.com",
		 * "meny@cloudengines.com", "TestNG results", "Test Results");
		 * sr.sendTestNGResult();
		 */

	}

}
