package com.pp.ios.auto;

import org.testng.annotations.DataProvider;

public class StaticProvider {
	//This method will provide data to any test method that declares that its Data Provider
	//is named "siginvals"
	@DataProvider(name = "siginvals")
	public static Object[][] createSigninData() {
	 return new Object[][] {
	   { "lior@cloudengines.com", "1" },
	 };
	}
	
	//This method will provide data to any test method that declares that its Data Provider
	//is named "createfolder"
	@DataProvider(name = "createfolder")
	public static Object[][] signIn() {
	 return new Object[][] {
	   { "eli+devices2@cloudengines.com", "2","pass" },
	 };
	}

}
