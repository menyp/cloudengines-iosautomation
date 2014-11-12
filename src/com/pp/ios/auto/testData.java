package com.pp.ios.auto;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
public class testData {
	GenericMethods genMeth = new GenericMethods();
	String signinWithEmailBtn_id = genMeth.getValueFromPropFile("element.signinWithEmailBtn_id");
	String signinFacebookBtn_class = genMeth.getValueFromPropFile("element.signinFacebookBtn_class");
	String emailTextField_Id = genMeth.getValueFromPropFile("element.emailTextField_Id");
	String signUp_xpth = genMeth.getValueFromPropFile("element.signUp_xpth");
	String settingsBtn_id = genMeth.getValueFromPropFile("element.settingsBtn_id");
	String settingsOption_xpth = genMeth.getValueFromPropFile("element.settingsOption_xpth");
	String securityBtn_id = genMeth.getValueFromPropFile("element.securityBtn");
	String changePassBtn_class = genMeth.getValueFromPropFile("element.changePassBtn_class");
	String changePassIframe_id = genMeth.getValueFromPropFile("element.changePassIframe_id");
	String currentPassword_id = genMeth.getValueFromPropFile("element.currentPassword_id");
	String newPass_id = genMeth.getValueFromPropFile("element.newPass_id");
	String confirmPass_id = genMeth.getValueFromPropFile("element.confirmPass_id");
	String createPassOKbtn_xpth = genMeth.getValueFromPropFile("element.createPassOKbtn_xpth");
	String createPassCancelBtn_xpth = genMeth.getValueFromPropFile("element.createPassCancelBtn_xpth");
	
	public testData() throws ParserConfigurationException, SAXException, IOException{
		this.signinWithEmailBtn_id = xmlHandel.readXml(signinWithEmailBtn_id);
		this.signinFacebookBtn_class = xmlHandel.readXml(signinFacebookBtn_class);
		this.emailTextField_Id = xmlHandel.readXml(emailTextField_Id);
		this.signUp_xpth = xmlHandel.readXml(signUp_xpth);
		this.settingsBtn_id = xmlHandel.readXml(settingsBtn_id);
		this.settingsOption_xpth = xmlHandel.readXml(settingsOption_xpth);
		this.securityBtn_id = xmlHandel.readXml(securityBtn_id);
		this.changePassBtn_class = xmlHandel.readXml(changePassBtn_class);
		this.changePassIframe_id = xmlHandel.readXml(changePassIframe_id);
		this.currentPassword_id = xmlHandel.readXml(currentPassword_id);
		this.newPass_id = xmlHandel.readXml(newPass_id);
		this.confirmPass_id = xmlHandel.readXml(confirmPass_id);
		this.createPassCancelBtn_xpth = xmlHandel.readXml(createPassCancelBtn_xpth);
		this.createPassOKbtn_xpth = xmlHandel.readXml(createPassOKbtn_xpth);
		
		
	};

}
