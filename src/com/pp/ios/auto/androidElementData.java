package com.pp.ios.auto;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class androidElementData {
	//GenericMethods genMeth = new GenericMethods();

	String BTNalreadyHaveAnAccount_id;
	String BTNlogin_id;
	String BTNfinishTour_id;
	String BTNcontinue_id;
	
	String BTNupload_name;
	String BTNsearch_name;
	String BTNrefresh_name;
	String BTNnewFolder_name;
	String BTNsort_name;
	String BTNdelete_name;
	String BTNcapturePhoto_id;
	String BTNcaptureVideo_id;
	String BTNcaptureDone_id;
	String BTNretakeCapture_id;
	String BTNcancelCapture_id;
	String BTNdeleteConfirm_id;
	String BTNcancelDelete_id;
	String BTNdismissNewFolder_id;
	String BTNcreateNewFolder_id;
	String BTNopenLSM_xpth;
	
	String TEXTFIELDemail_id;
	String TEXTFIELDpassword_id;
	
	String OPTIONcaptureNewPhoto_name;
	String OPTIONcaptureNewVideo_name;
	String OPTIONlocalPhoto_name;
	String OPTIONlocalVideo;
	String OPTIONmusicPlayer_name;
	String OPTIONanyFile_name;
	
	String OPTIONsortAZ_name;
	String OPTIONsortZA_name;
	String OPTIONsortNewestFirst_name;
	String OPTIONsortOldestFirst_name;
	String OPTIONsortSmallestFirst_name;
	String OPTIONsortBiggestFirst_name;
	
	String LIST1_xpth;
	String LIST2_xpth;
	String LIST3_xpth;
	String LIST4_xpth;
	String LIST5_xpth;
	String LIST6_xpth;
	
	public androidElementData() throws ParserConfigurationException, SAXException, IOException{
		this.BTNalreadyHaveAnAccount_id = xmlHandel.readAndroidXml("BTNalreadyHaveAnAccount_id");
		this.BTNlogin_id = xmlHandel.readAndroidXml("BTNlogin_id");
		this.BTNfinishTour_id = xmlHandel.readAndroidXml("BTNfinishTour_id");
		this.BTNcontinue_id = xmlHandel.readAndroidXml("BTNcontinue_id");
		this.BTNupload_name = xmlHandel.readAndroidXml("BTNupload_name");
		this.BTNsearch_name = xmlHandel.readAndroidXml("BTNsearch_name");
		this.BTNrefresh_name = xmlHandel.readAndroidXml("BTNrefresh_name");
		this.BTNnewFolder_name = xmlHandel.readAndroidXml("BTNnewFolder_name");
		this.BTNsort_name = xmlHandel.readAndroidXml("BTNsort_name");
		this.BTNdelete_name = xmlHandel.readAndroidXml("BTNdelete_name");
		this.BTNcapturePhoto_id = xmlHandel.readAndroidXml("BTNcapturePhoto_id");
		this.BTNcaptureVideo_id = xmlHandel.readAndroidXml("BTNcaptureVideo_id");
		this.BTNcaptureDone_id = xmlHandel.readAndroidXml("BTNcaptureDone_id");
		this.BTNretakeCapture_id = xmlHandel.readAndroidXml("BTNretakeCapture_id");
		this.BTNcancelCapture_id = xmlHandel.readAndroidXml("BTNcancelCapture_id");
		this.BTNdeleteConfirm_id = xmlHandel.readAndroidXml("BTNdeleteConfirm_id");
		this.BTNcancelDelete_id = xmlHandel.readAndroidXml("BTNcancelDelete_id");
		this.BTNdismissNewFolder_id = xmlHandel.readAndroidXml("BTNdismissNewFolder_id");
		this.BTNcreateNewFolder_id = xmlHandel.readAndroidXml("BTNcreateNewFolder_id");
		this.BTNopenLSM_xpth = xmlHandel.readAndroidXml("BTNopenLSM_xpth");

		
		

		
		
		this.TEXTFIELDemail_id = xmlHandel.readAndroidXml("TEXTFIELDemail_id");
		this.TEXTFIELDpassword_id = xmlHandel.readAndroidXml("TEXTFIELDpassword_id");
		this.OPTIONcaptureNewPhoto_name = xmlHandel.readAndroidXml("OPTIONcaptureNewPhoto_name");
		this.OPTIONcaptureNewVideo_name = xmlHandel.readAndroidXml("OPTIONcaptureNewVideo_name");
		this.OPTIONlocalPhoto_name = xmlHandel.readAndroidXml("OPTIONlocalPhoto_name");
		this.OPTIONlocalVideo = xmlHandel.readAndroidXml("OPTIONlocalVideo");
		this.OPTIONmusicPlayer_name = xmlHandel.readAndroidXml("OPTIONmusicPlayer_name");
		this.OPTIONanyFile_name = xmlHandel.readAndroidXml("OPTIONanyFile_name");
		this.OPTIONsortAZ_name = xmlHandel.readAndroidXml("OPTIONsortAZ_name");
		this.OPTIONsortZA_name = xmlHandel.readAndroidXml("OPTIONsortZA_name");
		this.OPTIONsortNewestFirst_name = xmlHandel.readAndroidXml("OPTIONsortNewestFirst_name");
		this.OPTIONsortOldestFirst_name = xmlHandel.readAndroidXml("OPTIONsortOldestFirst_name");
		this.OPTIONsortSmallestFirst_name = xmlHandel.readAndroidXml("OPTIONsortSmallestFirst_name");
		this.OPTIONsortBiggestFirst_name = xmlHandel.readAndroidXml("OPTIONsortBiggestFirst_name");
		
		
		this.LIST1_xpth = xmlHandel.readAndroidXml("LIST1_xpth");
		this.LIST2_xpth = xmlHandel.readAndroidXml("LIST2_xpth");
		this.LIST3_xpth = xmlHandel.readAndroidXml("LIST3_xpth");
		this.LIST4_xpth = xmlHandel.readAndroidXml("LIST4_xpth");
		this.LIST5_xpth = xmlHandel.readAndroidXml("LIST5_xpth");
		this.LIST6_xpth = xmlHandel.readAndroidXml("LIST6_xpth");
		
	}

}
