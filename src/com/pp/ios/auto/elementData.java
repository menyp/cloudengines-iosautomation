package com.pp.ios.auto;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class elementData {
	GenericMethods genMeth = new GenericMethods();
	
	String BTNsigninWithEmail_id ;// genMeth.getValueFromPropFile("element.signinWithEmailBtn_id");
	String BTNsigninFacebook_xpth ;// genMeth.getValueFromPropFile("element.BTNSigninFacebook_xpth");
	String TEXTFIELDemailFacebook_xpth ;//= genMeth.getValueFromPropFile("element.TEXTFIELDemailFacebook_xpth");
	String TEXTFIELDpass_Facebook_xpth ;//= genMeth.getValueFromPropFile("element.TEXTFIELDpass_Facebook_xpth");
	String BTNfacebookLogin_xpth;// = genMeth.getValueFromPropFile("element.BTNfacebookLogin_xpth");
	String TEXTFIELDemail_xpth;// = genMeth.getValueFromPropFile("element.emailTextField_Id");
	String TEXTFIELDpass_xpth ;//= genMeth.getValueFromPropFile("element.passTextField_Id");
	String BTNsignin_Class ;//= genMeth.getValueFromPropFile("element.signinBtn_Class");
	String settingsOptionSignout_xpath ;//= genMeth.getValueFromPropFile("element.BTNsignout_xpth");
	String signUp_xpth ;//= genMeth.getValueFromPropFile("element.signUp_xpth");
	String BTNsettings_id;// = genMeth.getValueFromPropFile("element.BTNsettings_id");
	String settingsOption_xpth ;//= genMeth.getValueFromPropFile("element.settingsOption_xpth");
	String settingsOptionSignoutFromSettings_xpath;
	String securityBtn_id ;//= genMeth.getValueFromPropFile("element.securityBtn");
	String BTNsecurityXpth;
	String BTNchangePass_class ;//= genMeth.getValueFromPropFile("element.BTNchangePass_class");
	String changePassIframe_id ;//= genMeth.getValueFromPropFile("element.changePassIframe_id");
	String TEXTFIELDcurrentPassword_xpth ;//= genMeth.getValueFromPropFile("element.currentPasswordTextField_id");
	String TEXTFIELDnewPass_xpth ;//= genMeth.getValueFromPropFile("element.newPassTextField_id");
	String TEXTFIELDconfirmPass_xpth ;//= genMeth.getValueFromPropFile("element.confirmPassTextField_id");
	String BTNcreatePassOK_xpth ;//= genMeth.getValueFromPropFile("element.BTNcreatePassOK_xpth");
	String BTNcreatePassCancel_xpth ;//= genMeth.getValueFromPropFile("element.BTNcreatePassCancel_xpth");
	String BTNFacebook_Skip_xpth;
	String BTNFacebook_Confirm_xpth;
	String user;
	String password;
	String BTNCreateNewFolderInMainScreen_xpth;
	String NameTheNewFolder_Id;
	String NewFolderName_String;
	String CECKBOXFolder_CSS;
	String BTNmoreAction_ClassName;
	String BTNdeleteFile_CSS;
	String BTNdeleteFileOk_CSS;
	String TEXTFIELDSsearch_CSS;
	String TEXTFIELDsearchMainScreen_Id;
	String BTNrefresh_Xpth;
	String BTNcancelInCreateNewPassClass;
	
	public elementData() throws ParserConfigurationException, SAXException, IOException{
		this.BTNsigninWithEmail_id = xmlHandel.readXml("BTNsigninWithEmail_id");
		this.BTNsigninFacebook_xpth = xmlHandel.readXml("BTNsigninFacebook_xpth");
		this.TEXTFIELDemail_xpth = xmlHandel.readXml("TEXTFIELDemail_xpth");
		this.TEXTFIELDpass_xpth = xmlHandel.readXml("TEXTFIELDpass_xpth");
		this.BTNsignin_Class = xmlHandel.readXml("BTNsignin_Class");
		this.settingsOptionSignout_xpath = xmlHandel.readXml("settingsOptionSignout_xpath");
		this.settingsOptionSignoutFromSettings_xpath = xmlHandel.readXml("settingsOptionSignoutFromSettings_xpath");
		this.signUp_xpth = xmlHandel.readXml("signUp_xpth");
		this.BTNsettings_id = xmlHandel.readXml("BTNsettings_id");
		this.settingsOption_xpth = xmlHandel.readXml("settingsOption_xpth");
		this.BTNsecurityXpth = xmlHandel.readXml("BTNsecurityXpth");
		this.BTNchangePass_class = xmlHandel.readXml("BTNchangePass_class");
		this.changePassIframe_id = xmlHandel.readXml("changePassIframe_id");
		this.TEXTFIELDcurrentPassword_xpth = xmlHandel.readXml("TEXTFIELDcurrentPassword_xpth");
		this.TEXTFIELDnewPass_xpth = xmlHandel.readXml("TEXTFIELDnewPass_xpth");
		this.TEXTFIELDconfirmPass_xpth = xmlHandel.readXml("TEXTFIELDconfirmPass_xpth");
		this.BTNcreatePassCancel_xpth = xmlHandel.readXml("BTNcreatePassCancel_xpth");
		this.BTNcreatePassOK_xpth = xmlHandel.readXml("BTNcreatePassOK_xpth");
		//this.user = genMeth.getValueFromPropFile(user.userName);
		//this.password = genMeth.getValueFromPropFile(user.userPassword);
		this.BTNfacebookLogin_xpth = xmlHandel.readXml("BTNfacebookLogin_xpth");
		//this.BTNSigninFacebook_xpth = xmlHandel.readXml(BTNSigninFacebook_xpth);
		this.TEXTFIELDemailFacebook_xpth = xmlHandel.readXml("TEXTFIELDemailFacebook_xpth");
		this.TEXTFIELDpass_Facebook_xpth = xmlHandel.readXml("TEXTFIELDpass_Facebook_xpth");
		this.BTNFacebook_Skip_xpth = xmlHandel.readXml("BTNFacebook_Skip_xpth");
		this.BTNFacebook_Confirm_xpth = xmlHandel.readXml("BTNFacebook_Confirm_xpth");
		this.BTNfacebookLogin_xpth = xmlHandel.readXml("BTNfacebookLogin_xpth");
		this.BTNCreateNewFolderInMainScreen_xpth = xmlHandel.readXml("BTNCreateNewFolderInMainScreen_xpth");
		this.NameTheNewFolder_Id = xmlHandel.readXml("NameTheNewFolder_Id");
		this.NewFolderName_String = xmlHandel.readXml("NewFolderName_String");
		this.CECKBOXFolder_CSS = xmlHandel.readXml("CECKBOXFolder_CSS");
		this.BTNmoreAction_ClassName = xmlHandel.readXml("BTNmoreAction_ClassName");
		this.BTNdeleteFile_CSS = xmlHandel.readXml("BTNdeleteFile_CSS");
		this.BTNdeleteFileOk_CSS = xmlHandel.readXml("BTNdeleteFileOk_CSS");
		this.TEXTFIELDSsearch_CSS = xmlHandel.readXml("TEXTFIELDSsearch_CSS");
		this.TEXTFIELDsearchMainScreen_Id = xmlHandel.readXml("TEXTFIELDsearchMainScreen_Id");
		this.BTNrefresh_Xpth = xmlHandel.readXml("BTNrefresh_Xpth");
		this.BTNcancelInCreateNewPassClass = xmlHandel.readXml("BTNcancelInCreateNewPassClass");


	}

}
