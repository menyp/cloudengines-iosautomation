package com.pp.ios.auto;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

	public class genData {
	
	GenericMethods genMeth = new GenericMethods();
		
		String NewFolderName_String;
		String userName;
		String password;
		String newPassword;
		String badPassword;
		String emptyPassword;
		String emptyUserName;
		String url;
		String userNameFB;
		String passwordFB;
		String uniqueFolderSearch;
		String screenShotPath;
		String firefox;
		String chrome;
	
	public genData() throws ParserConfigurationException, SAXException, IOException{
		
		this.NewFolderName_String = xmlHandel.readXml("NewFolderName_String");
		this.userName = xmlHandel.readXml("userName");
		this.password = xmlHandel.readXml("password");
		this.newPassword = xmlHandel.readXml("newPassword");
		this.badPassword = xmlHandel.readXml("badPassword");
		this.emptyPassword = xmlHandel.readXml("emptyPassword");
		this.emptyUserName = xmlHandel.readXml("emptyUserName");
		this.url = xmlHandel.readXml("url");
		this.userNameFB = xmlHandel.readXml("userNameFB");
		this.passwordFB = xmlHandel.readXml("passwordFB");
		this.uniqueFolderSearch = xmlHandel.readXml("uniqueFolderSearch");
		this.screenShotPath = xmlHandel.readXml("screenShotPath");
		this.firefox = xmlHandel.readXml("firefox");
		this.chrome = xmlHandel.readXml("chrome");
	}

}
