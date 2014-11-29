package com.pp.ios.auto;


import io.appium.java_client.AppiumDriver;

import java.awt.Desktop;
import java.io.File;

import org.apache.commons.exec.CommandLine;

public class openFile {

	public static void main(String[] args) throws Exception, Throwable {
		//closeSimulatorAndInstruments(); // also closes any appium servers

		//Process proc = Runtime.getRuntime().exec("/Applications/Appium.app/Contents/MacOS/Appium");
	    GenericMethods genMeth = new GenericMethods();
		String langEng = "iOStestDataENG.xml";
		//webElementsIos iosData = genMeth.iOSelementInit(langEng, );

		//Runtime.getRuntime().exec("/Users/qa/startAppium");
	//	Process proc = 
		Runtime.getRuntime().exec("/Users/pogoplug/Appium/startAppium");
		
		Thread.sleep(10000);
	
	 //   AppiumDriver driver = genMeth.setCapabilitiesIos();
		//genMeth.clickName(driver, genMeth, iosData.BTNsignUp_Name);

	    //Runtime.getRuntime().exec("/Users/qa/stopAppium");
		Runtime.getRuntime().exec("/Users/pogoplug/Appium/stopAppium");

		Thread.sleep(3000);
		
	    //proc.destroy();
		//Desktop.getDesktop().open(new File("/Users/qa/Desktop/Appium/runIos.jar"));


	}

}
