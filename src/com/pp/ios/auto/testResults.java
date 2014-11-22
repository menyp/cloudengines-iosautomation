package com.pp.ios.auto;

import org.testng.annotations.Test;

public class testResults {
	
	@Test (enabled = true, testName ="testResults", groups = {"temp"})
	public void runSuccessFaillure (){
		org.testng.Assert.fail("WebElement" + " still visible");
		//org.testng.Assert.assertTrue(true, "success");
		
		
	}

}
