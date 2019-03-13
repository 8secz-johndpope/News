package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class onwardResultVO extends bookingResponseBaseVO implements SOAPObject
{

    public String _CRM_YJYD = null;
    public String _ERROR_MESSAGE = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST_ONE = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST_TWO = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST_THREE = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST_FOUR = null;
    public java.util.List<flightInfoVO> _FLIGHT_INFO_LIST_FIVE = null;
    public String _HC_TYPE = null;
    public String _IS_AUTHED = null;
    public java.util.List<onwardInfoVO> _ONWARD_INFO_LIST = null;
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
        if(_CRM_YJYD != null) {
            xml.startTag(null, "CRM_YJYD");
            xml.text(_CRM_YJYD);
            xml.endTag(null, "CRM_YJYD");
        }
        if(_ERROR_MESSAGE != null) {
            xml.startTag(null, "ERROR_MESSAGE");
            xml.text(_ERROR_MESSAGE);
            xml.endTag(null, "ERROR_MESSAGE");
        }
        if(_FLIGHT_INFO_LIST_ONE != null) {
        	if(_FLIGHT_INFO_LIST_ONE.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST_ONE.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST_ONE");
                    _FLIGHT_INFO_LIST_ONE.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST_ONE");
        		}
        	}
        }
        if(_FLIGHT_INFO_LIST_TWO != null) {
        	if(_FLIGHT_INFO_LIST_TWO.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST_TWO.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST_TWO");
                    _FLIGHT_INFO_LIST_TWO.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST_TWO");
        		}
        	}
        }
        if(_FLIGHT_INFO_LIST_THREE != null) {
        	if(_FLIGHT_INFO_LIST_THREE.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST_THREE.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST_THREE");
                    _FLIGHT_INFO_LIST_THREE.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST_THREE");
        		}
        	}
        }
        if(_FLIGHT_INFO_LIST_FOUR != null) {
        	if(_FLIGHT_INFO_LIST_FOUR.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST_FOUR.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST_FOUR");
                    _FLIGHT_INFO_LIST_FOUR.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST_FOUR");
        		}
        	}
        }
        if(_FLIGHT_INFO_LIST_FIVE != null) {
        	if(_FLIGHT_INFO_LIST_FIVE.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST_FIVE.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST_FIVE");
                    _FLIGHT_INFO_LIST_FIVE.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST_FIVE");
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
        if(_ONWARD_INFO_LIST != null) {
        	if(_ONWARD_INFO_LIST.size()>0){
        		for(int i=0,len=_ONWARD_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "ONWARD_INFO_LIST");
                    _ONWARD_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "ONWARD_INFO_LIST");
        		}
        	}
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
	            	if("CRM_YJYD".equals(parser.getName())){
	            		_CRM_YJYD = parser.nextText();
	            		break;
	            	}
	            	else if("ERROR_MESSAGE".equals(parser.getName())){
	            		_ERROR_MESSAGE = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST_ONE".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST_ONE==null){
	            			_FLIGHT_INFO_LIST_ONE = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST_ONE.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST_TWO".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST_TWO==null){
	            			_FLIGHT_INFO_LIST_TWO = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST_TWO.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST_THREE".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST_THREE==null){
	            			_FLIGHT_INFO_LIST_THREE = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST_THREE.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST_FOUR".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST_FOUR==null){
	            			_FLIGHT_INFO_LIST_FOUR = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST_FOUR.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST_FIVE".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST_FIVE==null){
	            			_FLIGHT_INFO_LIST_FIVE = new java.util.ArrayList<flightInfoVO>();
	            		}
	            		flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST_FIVE.add(soapObject);
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
	            	else if("ONWARD_INFO_LIST".equals(parser.getName())){
	            		if(_ONWARD_INFO_LIST==null){
	            			_ONWARD_INFO_LIST = new java.util.ArrayList<onwardInfoVO>();
	            		}
	            		onwardInfoVO soapObject = new onwardInfoVO();
	                    soapObject.parse(binding, parser);
	                    _ONWARD_INFO_LIST.add(soapObject);
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
