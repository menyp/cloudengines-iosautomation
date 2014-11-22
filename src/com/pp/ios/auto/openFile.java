package com.pp.ios.auto;

import java.awt.Desktop;
import java.io.File;

public class openFile {

	public static void main(String[] args) throws Exception, Throwable {
		//closeSimulatorAndInstruments(); // also closes any appium servers

	    Process proc = Runtime.getRuntime().exec("/Applications/Appium.app");
	    GenericMethods genMeth = new GenericMethods();
		String langEng = "iOStestDataENG.xml";
		webElementsIos iosData = genMeth.iOSelementInit(langEng);
	    genMeth.loginIos(genMeth, iosData, "aaa");
	    proc.destroy();
	    Thread.sleep(1000);
		//Desktop.getDesktop().open(new File("/Users/qa/Desktop/Appium/runIos.jar"));


	}

}
