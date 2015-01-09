package com.pp.ios.auto;

import java.io.IOException;

public class WebElementsIos {
	
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
	String BTNphotoGallery_Name;
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
	String BTNdeleteDraft_Name;
	String BTNratePogoplug_Name;
	String BTNmore_Name;
	String BTNhelp_Name;
	String BTNcontactSupport_Name;
	
	
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
	String SharePogoplug_Name;
	String ShareMailText_Name;
	String PogoplugHelpCenter_Name;
	
	
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
	String scrollDown;
	String scrollUp;
	
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
	String iconFeatured_Name;
	
// Tabs
	String tabCurrentFolder_Name;
	String tabPogoplugCloud_Name;

		
public WebElementsIos (String langXml, String xmlPath ) throws Exception, Throwable, IOException {
	
	this.BTNalreadyHaveAnAccount_name = XmlHandel.readIosXml("BTNalreadyHaveAnAccount_name" , langXml, xmlPath);
	this.BTNsignUp_Name = XmlHandel.readIosXml("BTNsignUp_Name" , langXml, xmlPath);
	this.BTNsignin_Name = XmlHandel.readIosXml("BTNsignin_Name", langXml, xmlPath);
	this.BTNleft_Name = XmlHandel.readIosXml("BTNleft_Name", langXml, xmlPath);
	this.BTNshareOn_Name = XmlHandel.readIosXml("BTNshareOn_Name", langXml, xmlPath);
	this.BTNcameraOn_Name = XmlHandel.readIosXml("BTNcameraOn_Name", langXml, xmlPath);
	this.BTNcreateOn_Name = XmlHandel.readIosXml("BTNcreateOn_Name", langXml, xmlPath);
	this.BTNcancel_Name = XmlHandel.readIosXml("BTNcancel_Name", langXml, xmlPath);
	this.BTNcreate_Name = XmlHandel.readIosXml("BTNcreate_Name", langXml, xmlPath);
	this.BTNback_Name = XmlHandel.readIosXml("BTNback_Name", langXml, xmlPath);
	this.BTNdelete_Name = XmlHandel.readIosXml("BTNdelete_Name", langXml, xmlPath);
	this.BTNexistingPhotosorVideos_Name = XmlHandel.readIosXml("BTNexistingPhotosorVideos_Name", langXml, xmlPath);
	this.BTNuploadProgressHistory_Name = XmlHandel.readIosXml("BTNuploadProgressHistory_Name", langXml, xmlPath);
	this.BTNphotoCapture_Name = XmlHandel.readIosXml("BTNphotoCapture_Name", langXml, xmlPath);
	this.BTNusePhoto_Name = XmlHandel.readIosXml("BTNusePhoto_Name", langXml, xmlPath);
	this.BTNretake_Name = XmlHandel.readIosXml("BTNretake_Name", langXml, xmlPath);
	this.BTNok_Name = XmlHandel.readIosXml("BTNok_Name", langXml, xmlPath);
	this.BTNedit_Name = XmlHandel.readIosXml("BTNedit_Name", langXml, xmlPath);
	this.BTNdeleteOn_Name = XmlHandel.readIosXml("BTNdeleteOn_Name", langXml, xmlPath);
	this.BTNdone_Name = XmlHandel.readIosXml("BTNdone_Name", langXml, xmlPath);
	this.BTNfileExplorer_Name = XmlHandel.readIosXml("BTNfileExplorer_Name", langXml, xmlPath);
	this.BTNcontinue_Name = XmlHandel.readIosXml("BTNcontinue_Name", langXml, xmlPath);
	this.BTNgoUnlimited_Name = XmlHandel.readIosXml("BTNgoUnlimited_Name", langXml, xmlPath);
	this.BTNskip_Name = XmlHandel.readIosXml("BTNskip_Name", langXml, xmlPath);
	this.BTNxInTour_Name = XmlHandel.readIosXml("BTNxInTour_Name", langXml, xmlPath);
	this.BTNdismiss_Name = XmlHandel.readIosXml("BTNdismiss_Name", langXml, xmlPath);
	this.BTNsignUpForFree_Name = XmlHandel.readIosXml("BTNsignUpForFree_Name", langXml, xmlPath);
	this.BTNsubmit_Name = XmlHandel.readIosXml("BTNsubmit_Name", langXml, xmlPath);
	this.BTNclearTextIcon_Name = XmlHandel.readIosXml("BTNclearTextIcon_Name", langXml, xmlPath);
	this.BTNpassCodeLock_Name = XmlHandel.readIosXml("BTNpassCodeLock_Name", langXml, xmlPath);
	this.BTNoff_Name = XmlHandel.readIosXml("BTNoff_Name", langXml, xmlPath);
	this.BTNon_Name = XmlHandel.readIosXml("BTNon_Name", langXml, xmlPath);
	this.BTNchangePasscode_Name = XmlHandel.readIosXml("BTNchangePasscode_Name", langXml, xmlPath);
	this.BTNbackupOn_Name = XmlHandel.readIosXml("BTNbackupOn_Name", langXml, xmlPath);
	this.BTNbackupOff_Name = XmlHandel.readIosXml("BTNbackupOff_Name", langXml, xmlPath);
	this.BTNenable_Name = XmlHandel.readIosXml("BTNenable_Name", langXml, xmlPath);
	this.BTNupgrade_Name = XmlHandel.readIosXml("BTNupgrade_Name", langXml, xmlPath);
	this.BTNmusicPlayer_Name = XmlHandel.readIosXml("BTNmusicPlayer_Name", langXml, xmlPath);
	this.BTNphotoGallery_Name = XmlHandel.readIosXml("BTNphotoGallery_Name", langXml, xmlPath);
	this.BTNwifiAndCellular_Name = XmlHandel.readIosXml("BTNwifiAndCellular_Name", langXml, xmlPath);
	this.BTNvideos_Name = XmlHandel.readIosXml("BTNvideos_Name", langXml, xmlPath);
	this.BTNalbums_Name = XmlHandel.readIosXml("BTNalbums_Name", langXml, xmlPath);
	this.BTNtimeline_Name_name = XmlHandel.readIosXml("BTNtimeline_Name_name", langXml, xmlPath);
	this.BTNsignOut_Name = XmlHandel.readIosXml("BTNsignOut_Name", langXml, xmlPath);
	this.BTNpinButton1_Id = XmlHandel.readIosXml("BTNpinButton1_Id", langXml, xmlPath);
	this.BTNpinButton2_Id = XmlHandel.readIosXml("BTNpinButton2_Id", langXml, xmlPath);
	this.BTNxButtonInTour_Name = XmlHandel.readIosXml("BTNxButtonInTour_Name", langXml, xmlPath);
	this.BTNcameraRoll_Name = XmlHandel.readIosXml("BTNcameraRoll_Name", langXml, xmlPath);
	this.BTNnoSpaceOn_Name = XmlHandel.readIosXml("BTNnoSpaceOn_Name", langXml, xmlPath);
	this.BTNresumeUpload_Name = XmlHandel.readIosXml("BTNresumeUpload_Name", langXml, xmlPath);
	this.BTNfavorites_Name = XmlHandel.readIosXml("BTNfavorites_Name", langXml, xmlPath);
	this.BTNremoveFavorites_Name = XmlHandel.readIosXml("BTNremoveFavorites_Name", langXml, xmlPath);
	this.BTNaddUsers_Name = XmlHandel.readIosXml("BTNaddUsers_Name", langXml, xmlPath);
	this.BTNteamFolders_Name = XmlHandel.readIosXml("BTNteamFolders_Name", langXml, xmlPath);
	this.BTNremoveShare_Name = XmlHandel.readIosXml("BTNremoveShare_Name", langXml, xmlPath);
	this.BTNremoveAllUsers_Name = XmlHandel.readIosXml("BTNremoveAllUsers_Name", langXml, xmlPath);
	this.BTNdeleteDraft_Name = XmlHandel.readIosXml("BTNdeleteDraft_Name", langXml, xmlPath);
	this.BTNratePogoplug_Name = XmlHandel.readIosXml("BTNratePogoplug_Name", langXml, xmlPath);
	this.BTNmore_Name = XmlHandel.readIosXml("BTNmore_Name", langXml, xmlPath);
	this.BTNhelp_Name = XmlHandel.readIosXml("BTNhelp_Name", langXml, xmlPath);
	this.BTNcontactSupport_Name = XmlHandel.readIosXml("BTNcontactSupport_Name", langXml, xmlPath);

	
	
	
	this.TEXTFIELDemail_Id = XmlHandel.readIosXml("TEXTFIELDemail_Id", langXml, xmlPath);
	this.TEXTFIELDpass_Id = XmlHandel.readIosXml("TEXTFIELDpass_Id", langXml, xmlPath);
	this.TEXTFIELDcreateNewFolder_Xpth = XmlHandel.readIosXml("TEXTFIELDcreateNewFolder_Xpth", langXml, xmlPath);
	this.TEXTFIELDsearch_Id = XmlHandel.readIosXml("TEXTFIELDsearch_Id", langXml, xmlPath);
	this.TEXTFIELDnameOptional_Id = XmlHandel.readIosXml("TEXTFIELDnameOptional_Id", langXml, xmlPath);

	
	

	this.LinkForgotYourPassword_Name = XmlHandel.readIosXml("LinkForgotYourPassword_Name", langXml, xmlPath);
	this.LinkPrivacyPolicy_Name = XmlHandel.readIosXml("LinkPrivacyPolicy_Name", langXml, xmlPath);
	this.LinkTRUSTe_Name = XmlHandel.readIosXml("LinkTRUSTe_Name", langXml, xmlPath);
	this.NeverLoseAPhoto_Name = XmlHandel.readIosXml("NeverLoseAPhoto_Name", langXml, xmlPath);
	this.TransferPhonesSimply_Name = XmlHandel.readIosXml("TransferPhonesSimply_Name", langXml, xmlPath);
	this.Backup_Name = XmlHandel.readIosXml("Backup_Name", langXml, xmlPath);
	this.PogoplugCloudUnlimited_Name = XmlHandel.readIosXml("PogoplugCloudUnlimited_Name", langXml, xmlPath);
	this.DuplicateFolder_Name = XmlHandel.readIosXml("DuplicateFolder_Name", langXml, xmlPath);
	this.userUnlimited_name = XmlHandel.readIosXml("userUnlimited_name", langXml, xmlPath);
	this.userLimited_name = XmlHandel.readIosXml("userLimited_name", langXml, xmlPath);
	this.userAutomation2_Name = XmlHandel.readIosXml("userAutomation2_Name", langXml, xmlPath);

	
	
	
	this.password = XmlHandel.readIosXml("password", langXml, xmlPath);
	this.newPassword = XmlHandel.readIosXml("newPassword", langXml, xmlPath);
	this.badPassword = XmlHandel.readIosXml("badPassword", langXml, xmlPath);
	this.emptyPassword = XmlHandel.readIosXml("emptyPassword", langXml, xmlPath);
	this.emptyUserName = XmlHandel.readIosXml("emptyUserName", langXml, xmlPath);
	this.AccessToPhotos = XmlHandel.readIosXml("AccessToPhotos", langXml, xmlPath);
	this.Categories = XmlHandel.readIosXml("Categories", langXml, xmlPath);
	this.UnlimitedProtection_Name = XmlHandel.readIosXml("UnlimitedProtection_Name", langXml, xmlPath);
	this.UpgradeTour_Name = XmlHandel.readIosXml("UpgradeTour_Name", langXml, xmlPath);
	this.BackupTourText_Name = XmlHandel.readIosXml("BackupTourText_Name", langXml, xmlPath);
	this.NeverLoseaPhotoFullText_Name = XmlHandel.readIosXml("NeverLoseaPhotoFullText_Name", langXml, xmlPath);
	this.TransferPhonesSimplyFullText_Name = XmlHandel.readIosXml("TransferPhonesSimplyFullText_Name", langXml, xmlPath);
	this.UpgradeAccount_Name = XmlHandel.readIosXml("UpgradeAccount_Name", langXml, xmlPath);
	this.UpgardeAccountText_Name = XmlHandel.readIosXml("UpgardeAccountText_Name", langXml, xmlPath);
	this.Settings_Name = XmlHandel.readIosXml("Settings_Name", langXml, xmlPath);
	this.CreateAccountFailure_Name = XmlHandel.readIosXml("CreateAccountFailure_Name", langXml, xmlPath);
	this.PhotoAlbums_Name = XmlHandel.readIosXml("PhotoAlbums_Name", langXml, xmlPath);
	this.CloudEmptyFilesScreen_Name = XmlHandel.readIosXml("CloudEmptyFilesScreen_Name", langXml, xmlPath);
	this.BadCredentialsPopup = XmlHandel.readIosXml("BadCredentialsPopup", langXml, xmlPath);
	this.ForgotPasswordErrorPopup_Name = XmlHandel.readIosXml("ForgotPasswordErrorPopup_Name", langXml, xmlPath);
	this.ResetEmailPopup_Name = XmlHandel.readIosXml("ResetEmailPopup_Name", langXml, xmlPath);
	this.NoFilesFound_Name = XmlHandel.readIosXml("NoFilesFound_Name", langXml, xmlPath);
	this.PasscodeLock_Name = XmlHandel.readIosXml("PasscodeLock_Name", langXml, xmlPath);
	this.EnterPasscode_Name = XmlHandel.readIosXml("EnterPasscode_Name", langXml, xmlPath);
	this.PinCodeNotMatch_Name = XmlHandel.readIosXml("PinCodeNotMatch_Name", langXml, xmlPath);
	this.PinCodeReEnter_Name = XmlHandel.readIosXml("PinCodeReEnter_Name", langXml, xmlPath);
	this.BackupDisabled_Name = XmlHandel.readIosXml("BackupDisabled_Name", langXml, xmlPath);
	this.BackupCompleted_Name = XmlHandel.readIosXml("BackupCompleted_Name", langXml, xmlPath);
	this.Completed_Name = XmlHandel.readIosXml("Completed_Name", langXml, xmlPath);
	this.TrusteUrl_Name = XmlHandel.readIosXml("TrusteUrl_Name", langXml, xmlPath);
	this.EmptyFolder_Name = XmlHandel.readIosXml("EmptyFolder_Name", langXml, xmlPath);
	this.UploadError_Name = XmlHandel.readIosXml("UploadError_Name", langXml, xmlPath);
	this.ImageNotAvailable_Name = XmlHandel.readIosXml("ImageNotAvailable_Name", langXml, xmlPath);
	this.EmptyFavorites_Name = XmlHandel.readIosXml("EmptyFavorites_Name", langXml, xmlPath);
	this.FavoritesTitle_Name = XmlHandel.readIosXml("FavoritesTitle_Name", langXml, xmlPath);
	this.ProtectYourPhotos_Name = XmlHandel.readIosXml("ProtectYourPhotos_Name", langXml, xmlPath);
	this.AutomaticallyBackUpPhotosAndVideosToYourAccount_Name = XmlHandel.readIosXml("AutomaticallyBackUpPhotosAndVideosToYourAccount_Name", langXml, xmlPath);
	this.FavoritesMp3_Name = XmlHandel.readIosXml("FavoritesMp3_Name", langXml, xmlPath);
	this.FavoritesPng_Name = XmlHandel.readIosXml("FavoritesPng_Name", langXml, xmlPath);
	this.FavoritesMov_Name = XmlHandel.readIosXml("FavoritesMov_Name", langXml, xmlPath);
	this.UploadExistingImage_Name = XmlHandel.readIosXml("UploadExistingImage_Name", langXml, xmlPath);
	this.UploadExistingVideo_Name = XmlHandel.readIosXml("UploadExistingVideo_Name", langXml, xmlPath);
	this.PrivacyPolicyUrl_Name = XmlHandel.readIosXml("PrivacyPolicyUrl_Name", langXml, xmlPath);
	this.SearchFolderFromRoot_Name = XmlHandel.readIosXml("SearchFolderFromRoot_Name", langXml, xmlPath);
	this.SearchImageFromRoot_Name = XmlHandel.readIosXml("SearchImageFromRoot_Name", langXml, xmlPath);
	this.SearchFolderFromFolder_Name = XmlHandel.readIosXml("SearchFolderFromFolder_Name", langXml, xmlPath);
	this.SearchImageFromFolder_Name = XmlHandel.readIosXml("SearchImageFromFolder_Name", langXml, xmlPath);	
	this.SearchMainFolder_Name = XmlHandel.readIosXml("SearchMainFolder_Name", langXml, xmlPath);
	this.SearchSongInSong_Name = XmlHandel.readIosXml("SearchSongInSong_Name", langXml, xmlPath);	
	this.SearchSongInArtists_Name = XmlHandel.readIosXml("SearchSongInArtists_Name", langXml, xmlPath);	
	this.SearchSongInAlbums_Name = XmlHandel.readIosXml("SearchSongInAlbums_Name", langXml, xmlPath);	
	this.SearchSongInGenres_Name = XmlHandel.readIosXml("SearchSongInGenres_Name", langXml, xmlPath);	
	this.AccessContactsWarning_Name = XmlHandel.readIosXml("AccessContactsWarning_Name", langXml, xmlPath);
	this.AccessLocationServicesWarning_Name = XmlHandel.readIosXml("AccessLocationServicesWarning_Name", langXml, xmlPath);
	this.TermsOfService_Name = XmlHandel.readIosXml("TermsOfService_Name", langXml, xmlPath);
	this.NoResults_Name = XmlHandel.readIosXml("NoResults_Name", langXml, xmlPath);
	this.Disabled_Name = XmlHandel.readIosXml("Disabled_Name", langXml, xmlPath);
	this.SharePogoplug_Name = XmlHandel.readIosXml("SharePogoplug_Name", langXml, xmlPath);
	this.ShareMailText_Name = XmlHandel.readIosXml("ShareMailText_Name", langXml, xmlPath);
	this.PogoplugHelpCenter_Name = XmlHandel.readIosXml("PogoplugHelpCenter_Name", langXml, xmlPath);


	this.TOGGLEpasscodeLock_Xpth = XmlHandel.readIosXml("TOGGLEpasscodeLock_Xpth", langXml, xmlPath);
	this.TOGGLErequireImmediately_Xpth = XmlHandel.readIosXml("TOGGLErequireImmediately_Xpth", langXml, xmlPath);
	this.TOGGLEsaveLogin_Name = XmlHandel.readIosXml("TOGGLEsaveLogin_Name", langXml, xmlPath);
	this.TOGGLEcameraRoll_Name = XmlHandel.readIosXml("TOGGLEcameraRoll_Name", langXml, xmlPath);
	
	this.iconInProgress_Name = XmlHandel.readIosXml("iconInProgress_Name", langXml, xmlPath);
	this.iconBackLeftSideUpperMenu_Name = XmlHandel.readIosXml("iconBackLeftSideUpperMenu_Name", langXml, xmlPath);	
	this.iconTermsAndCondition_Name = XmlHandel.readIosXml("iconTermsAndCondition_Name", langXml, xmlPath);	
	this.iconSongs_Name = XmlHandel.readIosXml("iconSongs_Name", langXml, xmlPath);	
	this.iconArtists_Name = XmlHandel.readIosXml("iconArtists_Name", langXml, xmlPath);	
	this.iconAlbums_Name = XmlHandel.readIosXml("iconAlbums_Name", langXml, xmlPath);	
	this.iconGenres_Name = XmlHandel.readIosXml("iconGenres_Name", langXml, xmlPath);	
	this.iconPlayLists_Name = XmlHandel.readIosXml("iconPlayLists_Name", langXml, xmlPath);
	this.iconClearText_Name = XmlHandel.readIosXml("iconClearText_Name", langXml, xmlPath);
	this.iconAddToFavorites_Name = XmlHandel.readIosXml("iconAddToFavorites_Name", langXml, xmlPath);
	this.iconRemoveFromFavorites_Name = XmlHandel.readIosXml("iconRemoveFromFavorites_Name", langXml, xmlPath);
	this.iconFeatured_Name = XmlHandel.readIosXml("iconFeatured_Name", langXml, xmlPath);

	
	
	
	this.scrollDown = XmlHandel.readIosXml("scrollDown", langXml, xmlPath);
	this.scrollUp = XmlHandel.readIosXml("scrollUp", langXml, xmlPath);

	this.tabCurrentFolder_Name = XmlHandel.readIosXml("tabCurrentFolder_Name", langXml, xmlPath);
	this.tabPogoplugCloud_Name = XmlHandel.readIosXml("tabPogoplugCloud_Name", langXml, xmlPath);
	
}
}


