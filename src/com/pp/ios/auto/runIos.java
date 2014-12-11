package com.pp.ios.auto;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;



public class runIos {
	
	public static void main(String[] args) throws Exception, Throwable {
		// TODO Auto-generated method stub
		TestListenerAdapter tla = new TestListenerAdapter();
		
		//GenericMethods genMeth = new GenericMethods();
		//Process proc = Runtime.getRuntime().exec("/Applications/Appium.app/Contents/MacOS/Appium");
	    
		//Start Appium server
		//String startServer = genMeth.getValueFromPropFile("StartServerPath");
	//	String stopServer = genMeth.getValueFromPropFile("StopServerPath");

	//	Process proc =  Runtime.getRuntime().exec(startServer);	
	  //  Thread.sleep(4000);
		
		//Run the first suite
		
		TestNG testng1 = new TestNG();
		testng1.setTestClasses(new Class[] { sanityIos.class });
		testng1.setGroups("Sanity iOS1111, Regression iOS now");
		testng1.addListener(tla);
		testng1.run();
		
		//Second suite- send report by mail
		TestNG testng2 = new TestNG();
		testng2.setTestClasses(new Class[] { sendReport.class });
		testng2.setGroups("send mail");
		testng2.addListener(tla);
		testng2.run();
		
		//Stop Appium server
		//Runtime.getRuntime().exec(stopServer);
		//Runtime.getRuntime().exec("/Users/pogoplug/stopAppium");


	}

}