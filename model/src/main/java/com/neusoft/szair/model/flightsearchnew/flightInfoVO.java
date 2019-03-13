package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class flightInfoVO implements SOAPObject
{

    public String _AC_TYPE = null;
    public String _AIRPORT_DUTY = null;
    public String _BASE_PRICE = null;
    public String _CA_FLIGHT_FLAG = null;
    public String _CARRIER = null;
    public String _CARRIER_FLIGHT_NO = null;
    public java.util.List<classInfoVO> _CLASS_INFO_LIST = null;
    public java.util.List<classListVO> _CLASS_LIST = null;
    public String _CODE_SHARE_TEXT = null;
    public String _DST_CITY = null;
    public String _DST_DATE = null;
    public String _DST_TIME = null;
    public String _DURATION = null;
    public String _FLIGHT_NO = null;
    public String _IS_CHECK_SEGMENT = null;
    public String _IS_CODE_SHARE = null;
    public String _MEAL = null;
    public String _OILTAX_ADULT = null;
    public String _OILTAX_BABY = null;
    public String _OILTAX_CHILD = null;
    public String _ONTIME_RATE = null;
    public String _ORG_CITY = null;
    public String _ORG_DATE = null;
    public String _ORG_TIME = null;
    public String _SPECIAL_MEAL = null;
    public String _STOP_CITY = null;
    public String _STOP_CITY_CH = null;
    public classInfoVO _WF_GROUP_CLASS_INFO = null;
    public String _IS_NEW_FARE = null;
    
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
        if(_AC_TYPE != null) {
            xml.startTag(null, "AC_TYPE");
            xml.text(_AC_TYPE);
            xml.endTag(null, "AC_TYPE");
        }
        if(_AIRPORT_DUTY != null) {
            xml.startTag(null, "AIRPORT_DUTY");
            xml.text(_AIRPORT_DUTY);
            xml.endTag(null, "AIRPORT_DUTY");
        }
        if(_BASE_PRICE != null) {
            xml.startTag(null, "BASE_PRICE");
            xml.text(_BASE_PRICE);
            xml.endTag(null, "BASE_PRICE");
        }
        if(_CA_FLIGHT_FLAG != null) {
            xml.startTag(null, "CA_FLIGHT_FLAG");
            xml.text(_CA_FLIGHT_FLAG);
            xml.endTag(null, "CA_FLIGHT_FLAG");
        }
        if(_CARRIER != null) {
            xml.startTag(null, "CARRIER");
            xml.text(_CARRIER);
            xml.endTag(null, "CARRIER");
        }
        if(_CARRIER_FLIGHT_NO != null) {
            xml.startTag(null, "CARRIER_FLIGHT_NO");
            xml.text(_CARRIER_FLIGHT_NO);
            xml.endTag(null, "CARRIER_FLIGHT_NO");
        }
        if(_CLASS_INFO_LIST != null) {
        	if(_CLASS_INFO_LIST.size()>0){
        		for(int i=0,len=_CLASS_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "CLASS_INFO_LIST");
                    _CLASS_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "CLASS_INFO_LIST");
        		}
        	}
        }
        if(_CLASS_LIST != null) {
        	if(_CLASS_LIST.size()>0){
        		for(int i=0,len=_CLASS_LIST.size();i<len;i++){
                    xml.startTag(null, "CLASS_LIST");
                    _CLASS_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "CLASS_LIST");
        		}
        	}
        }
        if(_CODE_SHARE_TEXT != null) {
            xml.startTag(null, "CODE_SHARE_TEXT");
            xml.text(_CODE_SHARE_TEXT);
            xml.endTag(null, "CODE_SHARE_TEXT");
        }
        if(_DST_CITY != null) {
            xml.startTag(null, "DST_CITY");
            xml.text(_DST_CITY);
            xml.endTag(null, "DST_CITY");
        }
        if(_DST_DATE != null) {
            xml.startTag(null, "DST_DATE");
            xml.text(_DST_DATE);
            xml.endTag(null, "DST_DATE");
        }
        if(_DST_TIME != null) {
            xml.startTag(null, "DST_TIME");
            xml.text(_DST_TIME);
            xml.endTag(null, "DST_TIME");
        }
        if(_DURATION != null) {
            xml.startTag(null, "DURATION");
            xml.text(_DURATION);
            xml.endTag(null, "DURATION");
        }
        if(_FLIGHT_NO != null) {
            xml.startTag(null, "FLIGHT_NO");
            xml.text(_FLIGHT_NO);
            xml.endTag(null, "FLIGHT_NO");
        }
        if(_IS_CHECK_SEGMENT != null) {
            xml.startTag(null, "IS_CHECK_SEGMENT");
            xml.text(_IS_CHECK_SEGMENT);
            xml.endTag(null, "IS_CHECK_SEGMENT");
        }
        if(_IS_CODE_SHARE != null) {
            xml.startTag(null, "IS_CODE_SHARE");
            xml.text(_IS_CODE_SHARE);
            xml.endTag(null, "IS_CODE_SHARE");
        }
        if(_MEAL != null) {
            xml.startTag(null, "MEAL");
            xml.text(_MEAL);
            xml.endTag(null, "MEAL");
        }
        if(_OILTAX_ADULT != null) {
            xml.startTag(null, "OILTAX_ADULT");
            xml.text(_OILTAX_ADULT);
            xml.endTag(null, "OILTAX_ADULT");
        }
        if(_OILTAX_BABY != null) {
            xml.startTag(null, "OILTAX_BABY");
            xml.text(_OILTAX_BABY);
            xml.endTag(null, "OILTAX_BABY");
        }
        if(_OILTAX_CHILD != null) {
            xml.startTag(null, "OILTAX_CHILD");
            xml.text(_OILTAX_CHILD);
            xml.endTag(null, "OILTAX_CHILD");
        }
        if(_ONTIME_RATE != null) {
            xml.startTag(null, "ONTIME_RATE");
            xml.text(_ONTIME_RATE);
            xml.endTag(null, "ONTIME_RATE");
        }
        if(_ORG_CITY != null) {
            xml.startTag(null, "ORG_CITY");
            xml.text(_ORG_CITY);
            xml.endTag(null, "ORG_CITY");
        }
        if(_ORG_DATE != null) {
            xml.startTag(null, "ORG_DATE");
            xml.text(_ORG_DATE);
            xml.endTag(null, "ORG_DATE");
        }
        if(_ORG_TIME != null) {
            xml.startTag(null, "ORG_TIME");
            xml.text(_ORG_TIME);
            xml.endTag(null, "ORG_TIME");
        }
        if(_SPECIAL_MEAL != null) {
            xml.startTag(null, "SPECIAL_MEAL");
            xml.text(_SPECIAL_MEAL);
            xml.endTag(null, "SPECIAL_MEAL");
        }
        if(_STOP_CITY != null) {
            xml.startTag(null, "STOP_CITY");
            xml.text(_STOP_CITY);
            xml.endTag(null, "STOP_CITY");
        }
        if(_STOP_CITY_CH != null) {
            xml.startTag(null, "STOP_CITY_CH");
            xml.text(_STOP_CITY_CH);
            xml.endTag(null, "STOP_CITY_CH");
        }
        if(_WF_GROUP_CLASS_INFO != null) {
            xml.startTag(null, "WF_GROUP_CLASS_INFO");
            _WF_GROUP_CLASS_INFO.addElementsToNode(xml);
            xml.endTag(null, "WF_GROUP_CLASS_INFO");
        }
        if(_IS_NEW_FARE != null) {
            xml.startTag(null, "IS_NEW_FARE");
            xml.text(_IS_NEW_FARE);
            xml.endTag(null, "IS_NEW_FARE");
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
	            	if("AC_TYPE".equals(parser.getName())){
	            		_AC_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("AIRPORT_DUTY".equals(parser.getName())){
	            		_AIRPORT_DUTY = parser.nextText();
	            		break;
	            	}
	            	else if("BASE_PRICE".equals(parser.getName())){
	            		_BASE_PRICE = parser.nextText();
	            		break;
	            	}
	            	else if("CA_FLIGHT_FLAG".equals(parser.getName())){
	            		_CA_FLIGHT_FLAG = parser.nextText();
	            		break;
	            	}
	            	else if("CARRIER".equals(parser.getName())){
	            		_CARRIER = parser.nextText();
	            		break;
	            	}
	            	else if("CARRIER_FLIGHT_NO".equals(parser.getName())){
	            		_CARRIER_FLIGHT_NO = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_INFO_LIST".equals(parser.getName())){
	            		if(_CLASS_INFO_LIST==null){
	            			_CLASS_INFO_LIST = new java.util.ArrayList<classInfoVO>();
	            		}
	            		classInfoVO soapObject = new classInfoVO();
	                    soapObject.parse(binding, parser);
	                    _CLASS_INFO_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("CLASS_LIST".equals(parser.getName())){
	            		if(_CLASS_LIST==null){
	            			_CLASS_LIST = new java.util.ArrayList<classListVO>();
	            		}
	            		classListVO soapObject = new classListVO();
	                    soapObject.parse(binding, parser);
	                    _CLASS_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("CODE_SHARE_TEXT".equals(parser.getName())){
	            		_CODE_SHARE_TEXT = parser.nextText();
	            		break;
	            	}
	            	else if("DST_CITY".equals(parser.getName())){
	            		_DST_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("DST_DATE".equals(parser.getName())){
	            		_DST_DATE = parser.nextText();
	            		break;
	            	}
	            	else if("DST_TIME".equals(parser.getName())){
	            		_DST_TIME = parser.nextText();
	            		break;
	            	}
	            	else if("DURATION".equals(parser.getName())){
	            		_DURATION = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_NO".equals(parser.getName())){
	            		_FLIGHT_NO = parser.nextText();
	            		break;
	            	}
	            	else if("IS_CHECK_SEGMENT".equals(parser.getName())){
	            		_IS_CHECK_SEGMENT = parser.nextText();
	            		break;
	            	}
	            	else if("IS_CODE_SHARE".equals(parser.getName())){
	            		_IS_CODE_SHARE = parser.nextText();
	            		break;
	            	}
	            	else if("MEAL".equals(parser.getName())){
	            		_MEAL = parser.nextText();
	            		break;
	            	}
	            	else if("OILTAX_ADULT".equals(parser.getName())){
	            		_OILTAX_ADULT = parser.nextText();
	            		break;
	            	}
	            	else if("OILTAX_BABY".equals(parser.getName())){
	            		_OILTAX_BABY = parser.nextText();
	            		break;
	            	}
	            	else if("OILTAX_CHILD".equals(parser.getName())){
	            		_OILTAX_CHILD = parser.nextText();
	            		break;
	            	}
	            	else if("ONTIME_RATE".equals(parser.getName())){
	            		_ONTIME_RATE = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_CITY".equals(parser.getName())){
	            		_ORG_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_DATE".equals(parser.getName())){
	            		_ORG_DATE = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_TIME".equals(parser.getName())){
	            		_ORG_TIME = parser.nextText();
	            		break;
	            	}
	            	else if("SPECIAL_MEAL".equals(parser.getName())){
	            		_SPECIAL_MEAL = parser.nextText();
	            		break;
	            	}
	            	else if("STOP_CITY".equals(parser.getName())){
	            		_STOP_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("STOP_CITY_CH".equals(parser.getName())){
	            		_STOP_CITY_CH = parser.nextText();
	            		break;
	            	}
	            	else if("WF_GROUP_CLASS_INFO".equals(parser.getName())){
	                    classInfoVO soapObject = new classInfoVO();
	                    soapObject.parse(binding, parser);
	                    _WF_GROUP_CLASS_INFO = soapObject;
	            		break;
	            	}

                    else if("IS_NEW_FARE".equals(parser.getName())){
                        _IS_NEW_FARE = parser.nextText();
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
