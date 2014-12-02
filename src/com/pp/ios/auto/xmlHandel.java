package com.pp.ios.auto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlHandel {

	public static void main(String[] args) {
	

	}
	
	

	
	
	public static String readIosXml (String nodeName , String langXml, String xmlPath) throws ParserConfigurationException, SAXException, IOException{
		//Declare file path
		File xmlFile = new File (xmlPath + langXml);
//		File xmlFile = new File ("/resources/iOStestDataENG.xml");
		//File xmlFile = new File ("/Users/qa/cloudengines-iosautomation/src/resources/"+langXml);
		//File xmlFile = new File ("/Users/pogoplug/Appium/"+langXml);

		//Create instance for document builder factory
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		//Get node list for XML
		NodeList nlXml = doc.getChildNodes();
		//get XML first node
		Node nXml = nlXml.item(0);
		//Assign Node element
		Element element = (Element)nXml;
		String nodeContent = element.getElementsByTagName(nodeName).item(0).getTextContent();

		return nodeContent;
		
	};
	public static String readXml (String nodeName ) throws ParserConfigurationException, SAXException, IOException{
		//Declare file path
		File xmlFile = new File ("/Users/qa/cloudengines-automation/src/resources/testData.xml");

		//Create instance for document builder factory
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		//Get node list for XML
		NodeList nlXml = doc.getChildNodes();
		//get XML first node
		Node nXml = nlXml.item(0);
		//Assign Node element
		Element element = (Element)nXml;
		String nodeContent = element.getElementsByTagName(nodeName).item(0).getTextContent();

		return nodeContent;
		
	};
	
	public static String readAndroidXml (String nodeName) throws ParserConfigurationException, SAXException, IOException{
		//Declare file path
		File xmlFile = new File ("/Users/qa/cloudengines-automation/src/resources/androidTestData.xml");
		//Create instance for document builder factory
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		//Get node list for XML
		NodeList nlXml = doc.getChildNodes();
		//get XML first node
		Node nXml = nlXml.item(0);
		//Assign Node element
		Element element = (Element)nXml;
		String nodeContent = element.getElementsByTagName(nodeName).item(0).getTextContent();

		return nodeContent;
		
	};
	
	
	/*public static String rXml (String nodeName) throws SAXException, IOException, ParserConfigurationException{
		//String nodeName = "username"
		File xmlFile = new File ("C:\\Users\\Meny\\workspace\\test1111\\src\\test1111\\DataTest.xml\\");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);
		NodeList nodeList = document.getElementsByTagName(nodeName);
		String nodeContent = nodeList.item(0).getTextContent();
		
		return nodeName;
	};*/

}
