package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class flightSearchResultVO extends bookingResponseBaseVO implements SOAPObject
{

    public String _AIRPORT_DUTY = null;
    public String _CA_SEARCH_ID = null;
    public String _CRM_YJYD = null;
    public String _DEP_TIME = null;
    public String _DEP_TIME2 = null;
    public String _DISCOUNT_CHILD = null;
    public String _DST_CITY = null;
    public String _FLIGHT_CHANNEL = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST2 = null;
    public String _HC_TYPE = null;
    public String _IS_AUTHED = null;
    public String _M3D_CAPTCHA_STATUS = null;
    public String _OILTAX_ADULT = null;
    public String _OILTAX_CHILD = null;
    public java.util.List<onwardInfoVO> _ONWARD_INFO_LIST = null;
    public String _ORG_CITY = null;
    public String _OP_RESULT = null;
    
    private java.lang.Exception _exception = null;

    public void setexception(java.lang.Exception _exception){
    	this._exception = _exception;
    }

    public java.lang.Exception getexception(){
    	return this._exception;
    }
    
	public void toXml(XmlSerializer xml, String name, String namespace) throws IOException {
        String ns = null;
        if(namespace != null && namespace.length() > 0) {
            ns = namespace;
        }
        else {
            ns = getNamespace();
        }

        xml.startTag(ns, name);
        addAttributesToNode(xml);
		addElementsToNode(xml);
        xml.endTag(ns, name);
    }

    public String getNamespace() {
        return "http://com/shenzhenair/mobilewebservice/booking";
    }

    public void addAttributesToNode(XmlSerializer xml) throws IOException {
    }

    public void addElementsToNode(XmlSerializer xml) throws IOException
    {
        if(_AIRPORT_DUTY != null) {
            xml.startTag(null, "AIRPORT_DUTY");
            xml.text(_AIRPORT_DUTY);
            xml.endTag(null, "AIRPORT_DUTY");
        }
        if(_CA_SEARCH_ID != null) {
            xml.startTag(null, "CA_SEARCH_ID");
            xml.text(_CA_SEARCH_ID);
            xml.endTag(null, "CA_SEARCH_ID");
        }
        if(_CRM_YJYD != null) {
            xml.startTag(null, "CRM_YJYD");
            xml.text(_CRM_YJYD);
            xml.endTag(null, "CRM_YJYD");
        }
        if(_DEP_TIME != null) {
            xml.startTag(null, "DEP_TIME");
            xml.text(_DEP_TIME);
            xml.endTag(null, "DEP_TIME");
        }
        if(_DEP_TIME2 != null) {
            xml.startTag(null, "DEP_TIME2");
            xml.text(_DEP_TIME2);
            xml.endTag(null, "DEP_TIME2");
        }
        if(_DISCOUNT_CHILD != null) {
            xml.startTag(null, "DISCOUNT_CHILD");
            xml.text(_DISCOUNT_CHILD);
            xml.endTag(null, "DISCOUNT_CHILD");
        }
        if(_DST_CITY != null) {
            xml.startTag(null, "DST_CITY");
            xml.text(_DST_CITY);
            xml.endTag(null, "DST_CITY");
        }
        if(_FLIGHT_CHANNEL != null) {
            xml.startTag(null, "FLIGHT_CHANNEL");
            xml.text(_FLIGHT_CHANNEL);
            xml.endTag(null, "FLIGHT_CHANNEL");
        }
        if(_FLIGHT_INFO_LIST != null) {
        	if(_FLIGHT_INFO_LIST.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST");
                    _FLIGHT_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST");
        		}
        	}
        }
        if(_FLIGHT_INFO_LIST2 != null) {
        	if(_FLIGHT_INFO_LIST2.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST2.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST2");
                    _FLIGHT_INFO_LIST2.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST2");
        		}
        	}
        }
        if(_HC_TYPE != null) {
            xml.startTag(null, "HC_TYPE");
            xml.text(_HC_TYPE);
            xml.endTag(null, "HC_TYPE");
        }
        if(_IS_AUTHED != null) {
            xml.startTag(null, "IS_AUTHED");
            xml.text(_IS_AUTHED);
            xml.endTag(null, "IS_AUTHED");
        }
        if(_M3D_CAPTCHA_STATUS != null) {
            xml.startTag(null, "M3D_CAPTCHA_STATUS");
            xml.text(_M3D_CAPTCHA_STATUS);
            xml.endTag(null, "M3D_CAPTCHA_STATUS");
        }
        if(_OILTAX_ADULT != null) {
            xml.startTag(null, "OILTAX_ADULT");
            xml.text(_OILTAX_ADULT);
            xml.endTag(null, "OILTAX_ADULT");
        }
        if(_OILTAX_CHILD != null) {
            xml.startTag(null, "OILTAX_CHILD");
            xml.text(_OILTAX_CHILD);
            xml.endTag(null, "OILTAX_CHILD");
        }
        if(_ONWARD_INFO_LIST != null) {
        	if(_ONWARD_INFO_LIST.size()>0){
        		for(int i=0,len=_ONWARD_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "ONWARD_INFO_LIST");
                    _ONWARD_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "ONWARD_INFO_LIST");
        		}
        	}
        }
        if(_ORG_CITY != null) {
            xml.startTag(null, "ORG_CITY");
            xml.text(_ORG_CITY);
            xml.endTag(null, "ORG_CITY");
        }
        if(_OP_RESULT != null) {
            xml.startTag(null, "OP_RESULT");
            xml.text(_OP_RESULT);
            xml.endTag(null, "OP_RESULT");
        }
    }
    
    public void parse(SOAPBinding binding, XmlPullParser parser)
    {
    	int event = 0;
		try {
			event = parser.next();

	        while(event!= XmlPullParser.END_TAG){
	            switch(event){  
	            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件
	            	if("AIRPORT_DUTY".equals(parser.getName())){
	            		_AIRPORT_DUTY = parser.nextText();
	            		break;
	            	}
	            	else if("CA_SEARCH_ID".equals(parser.getName())){
	            		_CA_SEARCH_ID = parser.nextText();
	            		break;
	            	}
	            	else if("CRM_YJYD".equals(parser.getName())){
	            		_CRM_YJYD = parser.nextText();
	            		break;
	            	}
	            	else if("DEP_TIME".equals(parser.getName())){
	            		_DEP_TIME = parser.nextText();
	            		break;
	            	}
	            	else if("DEP_TIME2".equals(parser.getName())){
	            		_DEP_TIME2 = parser.nextText();
	            		break;
	            	}
	            	else if("DISCOUNT_CHILD".equals(parser.getName())){
	            		_DISCOUNT_CHILD = parser.nextText();
	            		break;
	            	}
	            	else if("DST_CITY".equals(parser.getName())){
	            		_DST_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_CHANNEL".equals(parser.getName())){
	            		_FLIGHT_CHANNEL = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST==null){
	            			_FLIGHT_INFO_LIST = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST2".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST2==null){
	            			_FLIGHT_INFO_LIST2 = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST2.add(soapObject);
	            		break;
	            	}
	            	else if("HC_TYPE".equals(parser.getName())){
	            		_HC_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("IS_AUTHED".equals(parser.getName())){
	            		_IS_AUTHED = parser.nextText();
	            		break;
	            	}
	            	else if("M3D_CAPTCHA_STATUS".equals(parser.getName())){
	            		_M3D_CAPTCHA_STATUS = parser.nextText();
	            		break;
	            	}
	            	else if("OILTAX_ADULT".equals(parser.getName())){
	            		_OILTAX_ADULT = parser.nextText();
	            		break;
	            	}
	            	else if("OILTAX_CHILD".equals(parser.getName())){
	            		_OILTAX_CHILD = parser.nextText();
	            		break;
	            	}
	            	else if("ONWARD_INFO_LIST".equals(parser.getName())){
	            		if(_ONWARD_INFO_LIST==null){
	            			_ONWARD_INFO_LIST = new java.util.ArrayList<onwardInfoVO>();
	            		}
	            		onwardInfoVO soapObject = new onwardInfoVO();
	                    soapObject.parse(binding, parser);
	                    _ONWARD_INFO_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("ORG_CITY".equals(parser.getName())){
	            		_ORG_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("OP_RESULT".equals(parser.getName())){
	            		_OP_RESULT = parser.nextText();
	            		break;
	            	}
	            	else{
	        			UnknownSOAPObject soapObject = new UnknownSOAPObject();
	                    soapObject.parse(binding, parser);
	            		break;
	            	}
	            }  
            	event = parser.next();
	        }//end while 
		} catch (XmlPullParserException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
}
