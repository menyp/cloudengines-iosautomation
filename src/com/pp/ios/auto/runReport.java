package com.pp.ios.auto;

import org.testng.annotations.BeforeSuite;

public class runReport {

	@BeforeSuite(alwaysRun = true, groups = "runReportIOS")
	public void sendMail() throws Exception {

		SendResults sr = new SendResults("elicherni444@gmail.com","meny@cloudengines.com", "TestNG results", "Test Results");
		sr.sendTestNGResult();

	}
	
}
