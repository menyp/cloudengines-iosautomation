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
		this.BTNsigninWithEmail_id = XmlHandel.readXml("BTNsigninWithEmail_id");
		this.BTNsigninFacebook_xpth = XmlHandel.readXml("BTNsigninFacebook_xpth");
		this.TEXTFIELDemail_xpth = XmlHandel.readXml("TEXTFIELDemail_xpth");
		this.TEXTFIELDpass_xpth = XmlHandel.readXml("TEXTFIELDpass_xpth");
		this.BTNsignin_Class = XmlHandel.readXml("BTNsignin_Class");
		this.settingsOptionSignout_xpath = XmlHandel.readXml("settingsOptionSignout_xpath");
		this.settingsOptionSignoutFromSettings_xpath = XmlHandel.readXml("settingsOptionSignoutFromSettings_xpath");
		this.signUp_xpth = XmlHandel.readXml("signUp_xpth");
		this.BTNsettings_id = XmlHandel.readXml("BTNsettings_id");
		this.settingsOption_xpth = XmlHandel.readXml("settingsOption_xpth");
		this.BTNsecurityXpth = XmlHandel.readXml("BTNsecurityXpth");
		this.BTNchangePass_class = XmlHandel.readXml("BTNchangePass_class");
		this.changePassIframe_id = XmlHandel.readXml("changePassIframe_id");
		this.TEXTFIELDcurrentPassword_xpth = XmlHandel.readXml("TEXTFIELDcurrentPassword_xpth");
		this.TEXTFIELDnewPass_xpth = XmlHandel.readXml("TEXTFIELDnewPass_xpth");
		this.TEXTFIELDconfirmPass_xpth = XmlHandel.readXml("TEXTFIELDconfirmPass_xpth");
		this.BTNcreatePassCancel_xpth = XmlHandel.readXml("BTNcreatePassCancel_xpth");
		this.BTNcreatePassOK_xpth = XmlHandel.readXml("BTNcreatePassOK_xpth");
		//this.user = genMeth.getValueFromPropFile(user.userName);
		//this.password = genMeth.getValueFromPropFile(user.userPassword);
		this.BTNfacebookLogin_xpth = XmlHandel.readXml("BTNfacebookLogin_xpth");
		//this.BTNSigninFacebook_xpth = xmlHandel.readXml(BTNSigninFacebook_xpth);
		this.TEXTFIELDemailFacebook_xpth = XmlHandel.readXml("TEXTFIELDemailFacebook_xpth");
		this.TEXTFIELDpass_Facebook_xpth = XmlHandel.readXml("TEXTFIELDpass_Facebook_xpth");
		this.BTNFacebook_Skip_xpth = XmlHandel.readXml("BTNFacebook_Skip_xpth");
		this.BTNFacebook_Confirm_xpth = XmlHandel.readXml("BTNFacebook_Confirm_xpth");
		this.BTNfacebookLogin_xpth = XmlHandel.readXml("BTNfacebookLogin_xpth");
		this.BTNCreateNewFolderInMainScreen_xpth = XmlHandel.readXml("BTNCreateNewFolderInMainScreen_xpth");
		this.NameTheNewFolder_Id = XmlHandel.readXml("NameTheNewFolder_Id");
		this.NewFolderName_String = XmlHandel.readXml("NewFolderName_String");
		this.CECKBOXFolder_CSS = XmlHandel.readXml("CECKBOXFolder_CSS");
		this.BTNmoreAction_ClassName = XmlHandel.readXml("BTNmoreAction_ClassName");
		this.BTNdeleteFile_CSS = XmlHandel.readXml("BTNdeleteFile_CSS");
		this.BTNdeleteFileOk_CSS = XmlHandel.readXml("BTNdeleteFileOk_CSS");
		this.TEXTFIELDSsearch_CSS = XmlHandel.readXml("TEXTFIELDSsearch_CSS");
		this.TEXTFIELDsearchMainScreen_Id = XmlHandel.readXml("TEXTFIELDsearchMainScreen_Id");
		this.BTNrefresh_Xpth = XmlHandel.readXml("BTNrefresh_Xpth");
		this.BTNcancelInCreateNewPassClass = XmlHandel.readXml("BTNcancelInCreateNewPassClass");


	}

}
