package com.pp.ios.auto;

import org.testng.annotations.Test;

public class sendReport {

	@Test(alwaysRun = true, groups = "send mail")
	public void sendMail() throws Exception {

		SendResults sr = new SendResults("elicherni444@gmail.com","meny@cloudengines.com", "TestNG results", "Test Results");
	//	sr.sendTestNGResult();
		sr.sendRegularEmail();

	}
	
}
