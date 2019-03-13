package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class wfGroupFlightSearchResultVO extends bookingResponseBaseVO implements SOAPObject
{

    public String _DEP_TIME = null;
    public String _DEP_TIME2 = null;
    public String _DST_CITY = null;
    public java.util.List<wfGroupFlightInfoVO> _FLIGHT_INFO_FIRST_LIST = null;
    public java.util.List<wfGroupFlightInfoVO> _FLIGHT_INFO_TOURIST_LIST = null;
    public String _IS_AUTHED = null;
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
        if(_DST_CITY != null) {
            xml.startTag(null, "DST_CITY");
            xml.text(_DST_CITY);
            xml.endTag(null, "DST_CITY");
        }
        if(_FLIGHT_INFO_FIRST_LIST != null) {
        	if(_FLIGHT_INFO_FIRST_LIST.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_FIRST_LIST.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_FIRST_LIST");
                    _FLIGHT_INFO_FIRST_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_FIRST_LIST");
        		}
        	}
        }
        if(_FLIGHT_INFO_TOURIST_LIST != null) {
        	if(_FLIGHT_INFO_TOURIST_LIST.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_TOURIST_LIST.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_TOURIST_LIST");
                    _FLIGHT_INFO_TOURIST_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_TOURIST_LIST");
        		}
        	}
        }
        if(_IS_AUTHED != null) {
            xml.startTag(null, "IS_AUTHED");
            xml.text(_IS_AUTHED);
            xml.endTag(null, "IS_AUTHED");
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
	            	if("DEP_TIME".equals(parser.getName())){
	            		_DEP_TIME = parser.nextText();
	            		break;
	            	}
	            	else if("DEP_TIME2".equals(parser.getName())){
	            		_DEP_TIME2 = parser.nextText();
	            		break;
	            	}
	            	else if("DST_CITY".equals(parser.getName())){
	            		_DST_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_INFO_FIRST_LIST".equals(parser.getName())){
	            		if(_FLIGHT_INFO_FIRST_LIST==null){
	            			_FLIGHT_INFO_FIRST_LIST = new java.util.ArrayList<wfGroupFlightInfoVO>();
	            		}
	            		wfGroupFlightInfoVO soapObject = new wfGroupFlightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_FIRST_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_TOURIST_LIST".equals(parser.getName())){
	            		if(_FLIGHT_INFO_TOURIST_LIST==null){
	            			_FLIGHT_INFO_TOURIST_LIST = new java.util.ArrayList<wfGroupFlightInfoVO>();
	            		}
	            		wfGroupFlightInfoVO soapObject = new wfGroupFlightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_TOURIST_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("IS_AUTHED".equals(parser.getName())){
	            		_IS_AUTHED = parser.nextText();
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
