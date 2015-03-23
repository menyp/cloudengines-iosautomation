package com.pp.ios.auto;

import org.testng.annotations.Test;

public class SendReport {

	@Test(alwaysRun = true, groups = "send mail")
	public void sendMail() throws Exception {

		SendResults sr1 = new SendResults("elicherni444@gmail.com","meny@cloudengines.com", "TestNG results", "Test Results");
		//sr.sendTestNGResult();
		sr1.sendRegularEmail();
		
//		SendResults sr2 = new SendResults("elicherni444@gmail.com","lior@cloudengines.com", "TestNG results", "Test Results");
//		//sr.sendTestNGResult();
//		sr2.sendRegularEmail();
//		
//		SendResults sr3 = new SendResults("elicherni444@gmail.com","evgeny@cloudengines.com", "TestNG results", "Test Results");
//		//sr.sendTestNGResult();
//		sr3.sendRegularEmail();

	}
	
}
