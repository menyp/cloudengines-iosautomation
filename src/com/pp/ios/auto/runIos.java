package com.pp.ios.auto;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;




public class runIos {
	
	public static void main(String[] args) throws Exception, Throwable {
		// TODO Auto-generated method stub
		TestListenerAdapter tla = new TestListenerAdapter();
		//need to start Appium server
	    @SuppressWarnings("unused")
		Process proc = Runtime.getRuntime().exec("/Applications/Appium.app/Contents/MacOS/Appium");
	    
		TestNG testng1 = new TestNG();
		testng1.setTestClasses(new Class[] { sanityIos.class });
		testng1.setGroups("Sanity Native iOS simulator");
		testng1.addListener(tla);
		testng1.run();
		
		//Will run after the testNG suite will finished & send the updated results
		TestNG testng2 = new TestNG();
		testng2.setTestClasses(new Class[] { sendReport.class });
		testng2.setGroups("temp1");
		testng2.addListener(tla);
		testng2.run();
		


	}

}