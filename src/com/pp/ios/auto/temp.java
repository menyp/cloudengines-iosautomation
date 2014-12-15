package com.pp.ios.auto;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestListenerAdapter tla = new TestListenerAdapter();

		TestNG testng1 = new TestNG();
		testng1.setTestClasses(new Class[] { SanityIos.class });
		testng1.setGroups("Sanity iOS");
		testng1.addListener(tla);
		testng1.run();
	}

}
