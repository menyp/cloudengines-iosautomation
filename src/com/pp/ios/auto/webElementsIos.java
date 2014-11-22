package com.pp.ios.auto;

import java.io.IOException;

public class webElementsIos {
	
// Buttons
	String BTNalreadyHaveAnAccount_name;
	String BTNsignUp_Name;
	String BTNsignin_Name;
	String BTNleft_Name;
	String BTNshareOn_Name;
	String BTNcameraOn_Name;
	String BTNcreateOn_Name;
	String BTNcancel_Name;
	String BTNcreate_Name;
	String BTNback_Name;
	String BTNdelete_Name;
	String BTNexistingPhotosorVideos_Name;
	String BTNuploadProgressHistory_Name;
	String BTNphotoCapture_Name;
	String BTNusePhoto_Name;
	String BTNretake_Name;
	String BTNok_Name;
	String BTNedit_Name;
	String BTNdeleteOn_Name;
	String BTNdone_Name;
	String BTNfileExplorer_Name;
	String BTNexistingphotosOrVideos_Name;
	String BTNuploadProgress_History_Name;
	String BTNcontinue_Name;
	String BTNgoUnlimited_Name;
	String BTNskip_Name;
	String BTNxInTour_Name;
	String BTNdismiss_Name;
	String BTNsignUpForFree_Name;
	String BTNsubmit_Name;
	String BTNclearTextIcon_Name;
	String BTNpassCodeLock_Name;
	String BTNoff_Name;
	String BTNon_Name;
	String BTNchangePasscode_Name;
	String BTNbackupOn_Name;
	String BTNbackupOff_Name;
	String BTNenable_Name;
	String BTNupgrade_Name;
	String BTNmusicPlayer_Name;
	String BTNphoneGallery_Name;
	String BTNwifiAndCellular_Name;
	String BTNvideos_Name;
	String BTNalbums_Name;
	String BTNtimeline_Name_name;
	String BTNsignOut_Name;
	String BTNpinButton1_Id;
	String BTNpinButton2_Id;
	String BTNxButtonInTour_Name;
	String BTNcameraRoll_Name;
	String BTNnoSpaceOn_Name;
	String BTNresumeUpload_Name;
	String BTNfavorites_Name;
	String BTNremoveFavorites_Name;
	String BTNaddUsers_Name;
	String BTNteamFolders_Name;
	String BTNremoveShare_Name;
	String BTNremoveAllUsers_Name;
	
// Text Fields
	String TEXTFIELDemail_xpth;
	String TEXTFIELDpass_xpth;
	String TEXTFIELDcreateNewFolder_Xpth;
	String TEXTFIELDsearch_Id;
	String TEXTFIELDemail_Id;
	String TEXTFIELDpass_Id;
	String TEXTFIELDnameOptional_Id;
	
// Links
	String LinkForgotYourPassword_Name;
	String LinkPrivacyPolicy_Name;
	String LinkTRUSTe_Name;
	
// Toggle
	String TOGGLEpasscodeLock_Xpth;
	String TOGGLErequireImmediately_Xpth;
	String TOGGLEsaveLogin_Name;
	String TOGGLEcameraRoll_Name;
	
// Strings 
	String NeverLoseAPhoto_Name;
	String TransferPhonesSimply_Name;
	String Backup_Name;
	String PogoplugCloudUnlimited_Name;
	String DuplicateFolder_Name;
	String UnlimitedProtection_Name;
	String UpgradeTour_Name;
	String BackupTourText_Name;
	String NeverLoseaPhotoFullText_Name;
	String TransferPhonesSimplyFullText_Name;
	String UpgradeAccount_Name;
	String UpgardeAccountText_Name;
	String Settings_Name;
	String CreateAccountFailure_Name;
	String PhotoAlbums_Name;
	String CloudEmptyFilesScreen_Name;
	String ForgotPasswordErrorPopup_Name;
	String ResetEmailPopup_Name;
	String NoFilesFound_Name;
	String PasscodeLock_Name;
	String EnterPasscode_Name;
	String PinCodeNotMatch_Name;
	String PinCodeReEnter_Name;
	String BackupDisabled_Name;
	String TermsOfService_Name;
	String NoResults_Name;
	String Disabled_Name;
	String Completed_Name;
	String TrusteUrl_Name;
	String EmptyFolder_Name;
	String UploadError_Name;
	String ImageNotAvailable_Name;
	String EmptyFavorites_Name;
	String FavoritesTitle_Name;
	String ProtectYourPhotos_Name;
	String AutomaticallyBackUpPhotosAndVideosToYourAccount_Name;
	String FavoritesMp3_Name;
	String FavoritesPng_Name;
	String FavoritesMov_Name;
	String UploadExistingImage_Name;
	String UploadExistingVideo_Name;
	String PrivacyPolicyUrl_Name;
	String SearchFolderFromRoot_Name;
	String SearchImageFromRoot_Name;
	String SearchFolderFromFolder_Name;
	String SearchImageFromFolder_Name;
	String SearchMainFolder_Name;
	String SearchSongInSong_Name;
	String SearchSongInArtists_Name;
	String SearchSongInAlbums_Name;
	String SearchSongInGenres_Name;
	String AccessContactsWarning_Name;
	String AccessLocationServicesWarning_Name;
	
	
//	general info	
	String userUnlimited_name;
	String userLimited_name;
	String userAutomation2_Name;
	String password;
	String newPassword;
	String badPassword;
	String emptyPassword;
	String emptyUserName;
	String AccessToPhotos;
	String Categories;
	String BadCredentialsPopup;
	String BackupCompleted_Name;

	
// Icons
	String iconInProgress_Name;
	String iconBackLeftSideUpperMenu_Name;
	String iconTermsAndCondition_Name;
	String iconSongs_Name;
	String iconArtists_Name;
	String iconAlbums_Name;
	String iconGenres_Name;
	String iconPlayLists_Name;
	String iconClearText_Name;
	String iconAddToFavorites_Name;
	String iconRemoveFromFavorites_Name;
	
// Tabs
	String tabCurrentFolder_Name;
	String tabPogoplugCloud_Name;
		
public webElementsIos (String langXml ) throws Exception, Throwable, IOException {
	
	this.BTNalreadyHaveAnAccount_name = xmlHandel.readIosXml("BTNalreadyHaveAnAccount_name" , langXml);
	this.BTNsignUp_Name = xmlHandel.readIosXml("BTNsignUp_Name" , langXml);
	this.BTNsignin_Name = xmlHandel.readIosXml("BTNsignin_Name", langXml);
	this.BTNleft_Name = xmlHandel.readIosXml("BTNleft_Name", langXml);
	this.BTNshareOn_Name = xmlHandel.readIosXml("BTNshareOn_Name", langXml);
	this.BTNcameraOn_Name = xmlHandel.readIosXml("BTNcameraOn_Name", langXml);
	this.BTNcreateOn_Name = xmlHandel.readIosXml("BTNcreateOn_Name", langXml);
	this.BTNcancel_Name = xmlHandel.readIosXml("BTNcancel_Name", langXml);
	this.BTNcreate_Name = xmlHandel.readIosXml("BTNcreate_Name", langXml);
	this.BTNback_Name = xmlHandel.readIosXml("BTNback_Name", langXml);
	this.BTNdelete_Name = xmlHandel.readIosXml("BTNdelete_Name", langXml);
	this.BTNexistingPhotosorVideos_Name = xmlHandel.readIosXml("BTNexistingPhotosorVideos_Name", langXml);
	this.BTNuploadProgressHistory_Name = xmlHandel.readIosXml("BTNuploadProgressHistory_Name", langXml);
	this.BTNphotoCapture_Name = xmlHandel.readIosXml("BTNphotoCapture_Name", langXml);
	this.BTNusePhoto_Name = xmlHandel.readIosXml("BTNusePhoto_Name", langXml);
	this.BTNretake_Name = xmlHandel.readIosXml("BTNretake_Name", langXml);
	this.BTNok_Name = xmlHandel.readIosXml("BTNok_Name", langXml);
	this.BTNedit_Name = xmlHandel.readIosXml("BTNedit_Name", langXml);
	this.BTNdeleteOn_Name = xmlHandel.readIosXml("BTNdeleteOn_Name", langXml);
	this.BTNdone_Name = xmlHandel.readIosXml("BTNdone_Name", langXml);
	this.BTNfileExplorer_Name = xmlHandel.readIosXml("BTNfileExplorer_Name", langXml);
	this.BTNcontinue_Name = xmlHandel.readIosXml("BTNcontinue_Name", langXml);
	this.BTNgoUnlimited_Name = xmlHandel.readIosXml("BTNgoUnlimited_Name", langXml);
	this.BTNskip_Name = xmlHandel.readIosXml("BTNskip_Name", langXml);
	this.BTNxInTour_Name = xmlHandel.readIosXml("BTNxInTour_Name", langXml);
	this.BTNdismiss_Name = xmlHandel.readIosXml("BTNdismiss_Name", langXml);
	this.BTNsignUpForFree_Name = xmlHandel.readIosXml("BTNsignUpForFree_Name", langXml);
	this.BTNsubmit_Name = xmlHandel.readIosXml("BTNsubmit_Name", langXml);
	this.BTNclearTextIcon_Name = xmlHandel.readIosXml("BTNclearTextIcon_Name", langXml);
	this.BTNpassCodeLock_Name = xmlHandel.readIosXml("BTNpassCodeLock_Name", langXml);
	this.BTNoff_Name = xmlHandel.readIosXml("BTNoff_Name", langXml);
	this.BTNon_Name = xmlHandel.readIosXml("BTNon_Name", langXml);
	this.BTNchangePasscode_Name = xmlHandel.readIosXml("BTNchangePasscode_Name", langXml);
	this.BTNbackupOn_Name = xmlHandel.readIosXml("BTNbackupOn_Name", langXml);
	this.BTNbackupOff_Name = xmlHandel.readIosXml("BTNbackupOff_Name", langXml);
	this.BTNenable_Name = xmlHandel.readIosXml("BTNenable_Name", langXml);
	this.BTNupgrade_Name = xmlHandel.readIosXml("BTNupgrade_Name", langXml);
	this.BTNmusicPlayer_Name = xmlHandel.readIosXml("BTNmusicPlayer_Name", langXml);
	this.BTNphoneGallery_Name = xmlHandel.readIosXml("BTNphoneGallery_Name", langXml);
	this.BTNwifiAndCellular_Name = xmlHandel.readIosXml("BTNwifiAndCellular_Name", langXml);
	this.BTNvideos_Name = xmlHandel.readIosXml("BTNvideos_Name", langXml);
	this.BTNalbums_Name = xmlHandel.readIosXml("BTNalbums_Name", langXml);
	this.BTNtimeline_Name_name = xmlHandel.readIosXml("BTNtimeline_Name_name", langXml);
	this.BTNsignOut_Name = xmlHandel.readIosXml("BTNsignOut_Name", langXml);
	this.BTNpinButton1_Id = xmlHandel.readIosXml("BTNpinButton1_Id", langXml);
	this.BTNpinButton2_Id = xmlHandel.readIosXml("BTNpinButton2_Id", langXml);
	this.BTNxButtonInTour_Name = xmlHandel.readIosXml("BTNxButtonInTour_Name", langXml);
	this.BTNcameraRoll_Name = xmlHandel.readIosXml("BTNcameraRoll_Name", langXml);
	this.BTNnoSpaceOn_Name = xmlHandel.readIosXml("BTNnoSpaceOn_Name", langXml);
	this.BTNresumeUpload_Name = xmlHandel.readIosXml("BTNresumeUpload_Name", langXml);
	this.BTNfavorites_Name = xmlHandel.readIosXml("BTNfavorites_Name", langXml);
	this.BTNremoveFavorites_Name = xmlHandel.readIosXml("BTNremoveFavorites_Name", langXml);
	this.BTNaddUsers_Name = xmlHandel.readIosXml("BTNaddUsers_Name", langXml);
	this.BTNteamFolders_Name = xmlHandel.readIosXml("BTNteamFolders_Name", langXml);
	this.BTNremoveShare_Name = xmlHandel.readIosXml("BTNremoveShare_Name", langXml);
	this.BTNremoveAllUsers_Name = xmlHandel.readIosXml("BTNremoveAllUsers_Name", langXml);

	this.TEXTFIELDemail_Id = xmlHandel.readIosXml("TEXTFIELDemail_Id", langXml);
	this.TEXTFIELDpass_Id = xmlHandel.readIosXml("TEXTFIELDpass_Id", langXml);
	this.TEXTFIELDcreateNewFolder_Xpth = xmlHandel.readIosXml("TEXTFIELDcreateNewFolder_Xpth", langXml);
	this.TEXTFIELDsearch_Id = xmlHandel.readIosXml("TEXTFIELDsearch_Id", langXml);
	this.TEXTFIELDnameOptional_Id = xmlHandel.readIosXml("TEXTFIELDnameOptional_Id", langXml);

	
	

	this.LinkForgotYourPassword_Name = xmlHandel.readIosXml("LinkForgotYourPassword_Name", langXml);
	this.LinkPrivacyPolicy_Name = xmlHandel.readIosXml("LinkPrivacyPolicy_Name", langXml);
	this.LinkTRUSTe_Name = xmlHandel.readIosXml("LinkTRUSTe_Name", langXml);
	this.NeverLoseAPhoto_Name = xmlHandel.readIosXml("NeverLoseAPhoto_Name", langXml);
	this.TransferPhonesSimply_Name = xmlHandel.readIosXml("TransferPhonesSimply_Name", langXml);
	this.Backup_Name = xmlHandel.readIosXml("Backup_Name", langXml);
	this.PogoplugCloudUnlimited_Name = xmlHandel.readIosXml("PogoplugCloudUnlimited_Name", langXml);
	this.DuplicateFolder_Name = xmlHandel.readIosXml("DuplicateFolder_Name", langXml);
	this.userUnlimited_name = xmlHandel.readIosXml("userUnlimited_name", langXml);
	this.userLimited_name = xmlHandel.readIosXml("userLimited_name", langXml);
	this.userAutomation2_Name = xmlHandel.readIosXml("userAutomation2_Name", langXml);

	
	
	
	this.password = xmlHandel.readIosXml("password", langXml);
	this.newPassword = xmlHandel.readIosXml("newPassword", langXml);
	this.badPassword = xmlHandel.readIosXml("badPassword", langXml);
	this.emptyPassword = xmlHandel.readIosXml("emptyPassword", langXml);
	this.emptyUserName = xmlHandel.readIosXml("emptyUserName", langXml);
	this.AccessToPhotos = xmlHandel.readIosXml("AccessToPhotos", langXml);
	this.Categories = xmlHandel.readIosXml("Categories", langXml);
	this.UnlimitedProtection_Name = xmlHandel.readIosXml("UnlimitedProtection_Name", langXml);
	this.UpgradeTour_Name = xmlHandel.readIosXml("UpgradeTour_Name", langXml);
	this.BackupTourText_Name = xmlHandel.readIosXml("BackupTourText_Name", langXml);
	this.NeverLoseaPhotoFullText_Name = xmlHandel.readIosXml("NeverLoseaPhotoFullText_Name", langXml);
	this.TransferPhonesSimplyFullText_Name = xmlHandel.readIosXml("TransferPhonesSimplyFullText_Name", langXml);
	this.UpgradeAccount_Name = xmlHandel.readIosXml("UpgradeAccount_Name", langXml);
	this.UpgardeAccountText_Name = xmlHandel.readIosXml("UpgardeAccountText_Name", langXml);
	this.Settings_Name = xmlHandel.readIosXml("Settings_Name", langXml);
	this.CreateAccountFailure_Name = xmlHandel.readIosXml("CreateAccountFailure_Name", langXml);
	this.PhotoAlbums_Name = xmlHandel.readIosXml("PhotoAlbums_Name", langXml);
	this.CloudEmptyFilesScreen_Name = xmlHandel.readIosXml("CloudEmptyFilesScreen_Name", langXml);
	this.BadCredentialsPopup = xmlHandel.readIosXml("BadCredentialsPopup", langXml);
	this.ForgotPasswordErrorPopup_Name = xmlHandel.readIosXml("ForgotPasswordErrorPopup_Name", langXml);
	this.ResetEmailPopup_Name = xmlHandel.readIosXml("ResetEmailPopup_Name", langXml);
	this.NoFilesFound_Name = xmlHandel.readIosXml("NoFilesFound_Name", langXml);
	this.PasscodeLock_Name = xmlHandel.readIosXml("PasscodeLock_Name", langXml);
	this.EnterPasscode_Name = xmlHandel.readIosXml("EnterPasscode_Name", langXml);
	this.PinCodeNotMatch_Name = xmlHandel.readIosXml("PinCodeNotMatch_Name", langXml);
	this.PinCodeReEnter_Name = xmlHandel.readIosXml("PinCodeReEnter_Name", langXml);
	this.BackupDisabled_Name = xmlHandel.readIosXml("BackupDisabled_Name", langXml);
	this.BackupCompleted_Name = xmlHandel.readIosXml("BackupCompleted_Name", langXml);
	this.Completed_Name = xmlHandel.readIosXml("Completed_Name", langXml);
	this.TrusteUrl_Name = xmlHandel.readIosXml("TrusteUrl_Name", langXml);
	this.EmptyFolder_Name = xmlHandel.readIosXml("EmptyFolder_Name", langXml);
	this.UploadError_Name = xmlHandel.readIosXml("UploadError_Name", langXml);
	this.ImageNotAvailable_Name = xmlHandel.readIosXml("ImageNotAvailable_Name", langXml);
	this.EmptyFavorites_Name = xmlHandel.readIosXml("EmptyFavorites_Name", langXml);
	this.FavoritesTitle_Name = xmlHandel.readIosXml("FavoritesTitle_Name", langXml);
	this.ProtectYourPhotos_Name = xmlHandel.readIosXml("ProtectYourPhotos_Name", langXml);
	this.AutomaticallyBackUpPhotosAndVideosToYourAccount_Name = xmlHandel.readIosXml("AutomaticallyBackUpPhotosAndVideosToYourAccount_Name", langXml);
	this.FavoritesMp3_Name = xmlHandel.readIosXml("FavoritesMp3_Name", langXml);
	this.FavoritesPng_Name = xmlHandel.readIosXml("FavoritesPng_Name", langXml);
	this.FavoritesMov_Name = xmlHandel.readIosXml("FavoritesMov_Name", langXml);
	this.UploadExistingImage_Name = xmlHandel.readIosXml("UploadExistingImage_Name", langXml);
	this.UploadExistingVideo_Name = xmlHandel.readIosXml("UploadExistingVideo_Name", langXml);
	this.PrivacyPolicyUrl_Name = xmlHandel.readIosXml("PrivacyPolicyUrl_Name", langXml);
	this.SearchFolderFromRoot_Name = xmlHandel.readIosXml("SearchFolderFromRoot_Name", langXml);
	this.SearchImageFromRoot_Name = xmlHandel.readIosXml("SearchImageFromRoot_Name", langXml);
	this.SearchFolderFromFolder_Name = xmlHandel.readIosXml("SearchFolderFromFolder_Name", langXml);
	this.SearchImageFromFolder_Name = xmlHandel.readIosXml("SearchImageFromFolder_Name", langXml);	
	this.SearchMainFolder_Name = xmlHandel.readIosXml("SearchMainFolder_Name", langXml);
	this.SearchSongInSong_Name = xmlHandel.readIosXml("SearchSongInSong_Name", langXml);	
	this.SearchSongInArtists_Name = xmlHandel.readIosXml("SearchSongInArtists_Name", langXml);	
	this.SearchSongInAlbums_Name = xmlHandel.readIosXml("SearchSongInAlbums_Name", langXml);	
	this.SearchSongInGenres_Name = xmlHandel.readIosXml("SearchSongInGenres_Name", langXml);	
	this.AccessContactsWarning_Name = xmlHandel.readIosXml("AccessContactsWarning_Name", langXml);
	this.AccessLocationServicesWarning_Name = xmlHandel.readIosXml("AccessLocationServicesWarning_Name", langXml);
	this.TermsOfService_Name = xmlHandel.readIosXml("TermsOfService_Name", langXml);
	this.NoResults_Name = xmlHandel.readIosXml("NoResults_Name", langXml);
	this.Disabled_Name = xmlHandel.readIosXml("Disabled_Name", langXml);



	this.TOGGLEpasscodeLock_Xpth = xmlHandel.readIosXml("TOGGLEpasscodeLock_Xpth", langXml);
	this.TOGGLErequireImmediately_Xpth = xmlHandel.readIosXml("TOGGLErequireImmediately_Xpth", langXml);
	this.TOGGLEsaveLogin_Name = xmlHandel.readIosXml("TOGGLEsaveLogin_Name", langXml);
	this.TOGGLEcameraRoll_Name = xmlHandel.readIosXml("TOGGLEcameraRoll_Name", langXml);
	
	this.iconInProgress_Name = xmlHandel.readIosXml("iconInProgress_Name", langXml);
	this.iconBackLeftSideUpperMenu_Name = xmlHandel.readIosXml("iconBackLeftSideUpperMenu_Name", langXml);	
	this.iconTermsAndCondition_Name = xmlHandel.readIosXml("iconTermsAndCondition_Name", langXml);	
	this.iconSongs_Name = xmlHandel.readIosXml("iconSongs_Name", langXml);	
	this.iconArtists_Name = xmlHandel.readIosXml("iconArtists_Name", langXml);	
	this.iconAlbums_Name = xmlHandel.readIosXml("iconAlbums_Name", langXml);	
	this.iconGenres_Name = xmlHandel.readIosXml("iconGenres_Name", langXml);	
	this.iconPlayLists_Name = xmlHandel.readIosXml("iconPlayLists_Name", langXml);
	this.iconClearText_Name = xmlHandel.readIosXml("iconClearText_Name", langXml);
	this.iconAddToFavorites_Name = xmlHandel.readIosXml("iconAddToFavorites_Name", langXml);
	this.iconRemoveFromFavorites_Name = xmlHandel.readIosXml("iconRemoveFromFavorites_Name", langXml);

	
	this.tabCurrentFolder_Name = xmlHandel.readIosXml("tabCurrentFolder_Name", langXml);
	this.tabPogoplugCloud_Name = xmlHandel.readIosXml("tabPogoplugCloud_Name", langXml);
	
}



}