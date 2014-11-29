package com.pp.ios.auto;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;




public class runIos {
	
	public static void main(String[] args) throws Exception, Throwable {
		// TODO Auto-generated method stub
		TestListenerAdapter tla = new TestListenerAdapter();
	
	    @SuppressWarnings("unused")
		//Process proc = Runtime.getRuntime().exec("/Applications/Appium.app/Contents/MacOS/Appium");
	    
		//Start Appium server
		Process proc = Runtime.getRuntime().exec("/Users/qa/startAppium");	
		//Process proc = Runtime.getRuntime().exec("/Users/pogoplug/startAppium");	    
	    Thread.sleep(4000);
		
	    //run the sanity testing 
		TestNG testng1 = new TestNG();
		testng1.setTestClasses(new Class[] { sanityIos.class });
		testng1.setGroups("Sanity Native iOS simulator");
		testng1.addListener(tla);
		testng1.run();
		
		//Send report by mail post sanity testing
		TestNG testng2 = new TestNG();
		testng2.setTestClasses(new Class[] { sendReport.class });
		testng2.setGroups("temp1");
		testng2.addListener(tla);
		testng2.run();
		
		//Stop Appium server
		Runtime.getRuntime().exec("/Users/qa/stopAppium");
		//Runtime.getRuntime().exec("/Users/pogoplug/stopAppium");


	}

}