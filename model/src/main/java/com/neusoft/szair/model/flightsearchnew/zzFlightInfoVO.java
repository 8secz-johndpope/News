package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class zzFlightInfoVO implements SOAPObject
{

    public String _AC_TYPE = null;
    public String _AIRPORT_DUTY = null;
    public String _CARRIER = null;
    public String _CARRIER_FLIGHT_NO = null;
    public String _CLASS_STORAGE_C = null;
    public String _CLASS_STORAGE_F = null;
    public String _CLASS_STORAGE_Y = null;
    public String _CODE_SHARE_TEXT = null;
    public String _DST_AIRPORT = null;
    public String _DST_AIRPORT_FULL_NAME = null;
    public String _DST_AIRPORT_NAME = null;
    public String _DST_CITY = null;
    public String _DST_CITY_CH = null;
    public String _DST_DATE = null;
    public String _DST_TIME = null;
    public String _DURATION = null;
    public String _ETKT = null;
    public String _FBR_PRODUCT_CODE = null;
    public String _FBR_RULE_CODE = null;
    public java.util.List<flightInfoTaxVO> _FLIGHT_INFO_TAX = null;
    public String _FLIGHT_NO = null;
    public String _IS_CODE_SHARE = null;
    public String _MEAL = null;
    public String _OILTAX_ADULT = null;
    public String _OILTAX_CHILD = null;
    public String _OPERATE_AIRLINE = null;
    public String _ORG_AIRPORT = null;
    public String _ORG_AIRPORT_FULL_NAME = null;
    public String _ORG_AIRPORT_NAME = null;
    public String _ORG_CITY = null;
    public String _ORG_CITY_CH = null;
    public String _ORG_DATE = null;
    public String _ORG_TIME = null;
    public String _PULIC_CLASS_PRICE_C = null;
    public String _PULIC_CLASS_PRICE_F = null;
    public String _PULIC_CLASS_PRICE_Y = null;
    public String _SEARCH_ID = null;
    public String _SEGMENT_ID = null;
    public String _STOP_CITY = null;
    public String _STOP_CITY_CH = null;
    public String _STOP_DEPT_DATE = null;
    public String _STOP_DST_DATE = null;
    public String _TRIP_ID = null;
    public String _TRIP_OPTION_ID = null;
    
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
        if(_CLASS_STORAGE_C != null) {
            xml.startTag(null, "CLASS_STORAGE_C");
            xml.text(_CLASS_STORAGE_C);
            xml.endTag(null, "CLASS_STORAGE_C");
        }
        if(_CLASS_STORAGE_F != null) {
            xml.startTag(null, "CLASS_STORAGE_F");
            xml.text(_CLASS_STORAGE_F);
            xml.endTag(null, "CLASS_STORAGE_F");
        }
        if(_CLASS_STORAGE_Y != null) {
            xml.startTag(null, "CLASS_STORAGE_Y");
            xml.text(_CLASS_STORAGE_Y);
            xml.endTag(null, "CLASS_STORAGE_Y");
        }
        if(_CODE_SHARE_TEXT != null) {
            xml.startTag(null, "CODE_SHARE_TEXT");
            xml.text(_CODE_SHARE_TEXT);
            xml.endTag(null, "CODE_SHARE_TEXT");
        }
        if(_DST_AIRPORT != null) {
            xml.startTag(null, "DST_AIRPORT");
            xml.text(_DST_AIRPORT);
            xml.endTag(null, "DST_AIRPORT");
        }
        if(_DST_AIRPORT_FULL_NAME != null) {
            xml.startTag(null, "DST_AIRPORT_FULL_NAME");
            xml.text(_DST_AIRPORT_FULL_NAME);
            xml.endTag(null, "DST_AIRPORT_FULL_NAME");
        }
        if(_DST_AIRPORT_NAME != null) {
            xml.startTag(null, "DST_AIRPORT_NAME");
            xml.text(_DST_AIRPORT_NAME);
            xml.endTag(null, "DST_AIRPORT_NAME");
        }
        if(_DST_CITY != null) {
            xml.startTag(null, "DST_CITY");
            xml.text(_DST_CITY);
            xml.endTag(null, "DST_CITY");
        }
        if(_DST_CITY_CH != null) {
            xml.startTag(null, "DST_CITY_CH");
            xml.text(_DST_CITY_CH);
            xml.endTag(null, "DST_CITY_CH");
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
        if(_ETKT != null) {
            xml.startTag(null, "ETKT");
            xml.text(_ETKT);
            xml.endTag(null, "ETKT");
        }
        if(_FBR_PRODUCT_CODE != null) {
            xml.startTag(null, "FBR_PRODUCT_CODE");
            xml.text(_FBR_PRODUCT_CODE);
            xml.endTag(null, "FBR_PRODUCT_CODE");
        }
        if(_FBR_RULE_CODE != null) {
            xml.startTag(null, "FBR_RULE_CODE");
            xml.text(_FBR_RULE_CODE);
            xml.endTag(null, "FBR_RULE_CODE");
        }
        if(_FLIGHT_INFO_TAX != null) {
        	if(_FLIGHT_INFO_TAX.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_TAX.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_TAX");
                    _FLIGHT_INFO_TAX.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_TAX");
        		}
        	}
        }
        if(_FLIGHT_NO != null) {
            xml.startTag(null, "FLIGHT_NO");
            xml.text(_FLIGHT_NO);
            xml.endTag(null, "FLIGHT_NO");
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
        if(_OILTAX_CHILD != null) {
            xml.startTag(null, "OILTAX_CHILD");
            xml.text(_OILTAX_CHILD);
            xml.endTag(null, "OILTAX_CHILD");
        }
        if(_OPERATE_AIRLINE != null) {
            xml.startTag(null, "OPERATE_AIRLINE");
            xml.text(_OPERATE_AIRLINE);
            xml.endTag(null, "OPERATE_AIRLINE");
        }
        if(_ORG_AIRPORT != null) {
            xml.startTag(null, "ORG_AIRPORT");
            xml.text(_ORG_AIRPORT);
            xml.endTag(null, "ORG_AIRPORT");
        }
        if(_ORG_AIRPORT_FULL_NAME != null) {
            xml.startTag(null, "ORG_AIRPORT_FULL_NAME");
            xml.text(_ORG_AIRPORT_FULL_NAME);
            xml.endTag(null, "ORG_AIRPORT_FULL_NAME");
        }
        if(_ORG_AIRPORT_NAME != null) {
            xml.startTag(null, "ORG_AIRPORT_NAME");
            xml.text(_ORG_AIRPORT_NAME);
            xml.endTag(null, "ORG_AIRPORT_NAME");
        }
        if(_ORG_CITY != null) {
            xml.startTag(null, "ORG_CITY");
            xml.text(_ORG_CITY);
            xml.endTag(null, "ORG_CITY");
        }
        if(_ORG_CITY_CH != null) {
            xml.startTag(null, "ORG_CITY_CH");
            xml.text(_ORG_CITY_CH);
            xml.endTag(null, "ORG_CITY_CH");
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
        if(_PULIC_CLASS_PRICE_C != null) {
            xml.startTag(null, "PULIC_CLASS_PRICE_C");
            xml.text(_PULIC_CLASS_PRICE_C);
            xml.endTag(null, "PULIC_CLASS_PRICE_C");
        }
        if(_PULIC_CLASS_PRICE_F != null) {
            xml.startTag(null, "PULIC_CLASS_PRICE_F");
            xml.text(_PULIC_CLASS_PRICE_F);
            xml.endTag(null, "PULIC_CLASS_PRICE_F");
        }
        if(_PULIC_CLASS_PRICE_Y != null) {
            xml.startTag(null, "PULIC_CLASS_PRICE_Y");
            xml.text(_PULIC_CLASS_PRICE_Y);
            xml.endTag(null, "PULIC_CLASS_PRICE_Y");
        }
        if(_SEARCH_ID != null) {
            xml.startTag(null, "SEARCH_ID");
            xml.text(_SEARCH_ID);
            xml.endTag(null, "SEARCH_ID");
        }
        if(_SEGMENT_ID != null) {
            xml.startTag(null, "SEGMENT_ID");
            xml.text(_SEGMENT_ID);
            xml.endTag(null, "SEGMENT_ID");
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
        if(_STOP_DEPT_DATE != null) {
            xml.startTag(null, "STOP_DEPT_DATE");
            xml.text(_STOP_DEPT_DATE);
            xml.endTag(null, "STOP_DEPT_DATE");
        }
        if(_STOP_DST_DATE != null) {
            xml.startTag(null, "STOP_DST_DATE");
            xml.text(_STOP_DST_DATE);
            xml.endTag(null, "STOP_DST_DATE");
        }
        if(_TRIP_ID != null) {
            xml.startTag(null, "TRIP_ID");
            xml.text(_TRIP_ID);
            xml.endTag(null, "TRIP_ID");
        }
        if(_TRIP_OPTION_ID != null) {
            xml.startTag(null, "TRIP_OPTION_ID");
            xml.text(_TRIP_OPTION_ID);
            xml.endTag(null, "TRIP_OPTION_ID");
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
	            	else if("CARRIER".equals(parser.getName())){
	            		_CARRIER = parser.nextText();
	            		break;
	            	}
	            	else if("CARRIER_FLIGHT_NO".equals(parser.getName())){
	            		_CARRIER_FLIGHT_NO = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_STORAGE_C".equals(parser.getName())){
	            		_CLASS_STORAGE_C = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_STORAGE_F".equals(parser.getName())){
	            		_CLASS_STORAGE_F = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_STORAGE_Y".equals(parser.getName())){
	            		_CLASS_STORAGE_Y = parser.nextText();
	            		break;
	            	}
	            	else if("CODE_SHARE_TEXT".equals(parser.getName())){
	            		_CODE_SHARE_TEXT = parser.nextText();
	            		break;
	            	}
	            	else if("DST_AIRPORT".equals(parser.getName())){
	            		_DST_AIRPORT = parser.nextText();
	            		break;
	            	}
	            	else if("DST_AIRPORT_FULL_NAME".equals(parser.getName())){
	            		_DST_AIRPORT_FULL_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("DST_AIRPORT_NAME".equals(parser.getName())){
	            		_DST_AIRPORT_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("DST_CITY".equals(parser.getName())){
	            		_DST_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("DST_CITY_CH".equals(parser.getName())){
	            		_DST_CITY_CH = parser.nextText();
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
	            	else if("ETKT".equals(parser.getName())){
	            		_ETKT = parser.nextText();
	            		break;
	            	}
	            	else if("FBR_PRODUCT_CODE".equals(parser.getName())){
	            		_FBR_PRODUCT_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("FBR_RULE_CODE".equals(parser.getName())){
	            		_FBR_RULE_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_INFO_TAX".equals(parser.getName())){
	            		if(_FLIGHT_INFO_TAX==null){
	            			_FLIGHT_INFO_TAX = new java.util.ArrayList<flightInfoTaxVO>();
	            		}
	            		flightInfoTaxVO soapObject = new flightInfoTaxVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_TAX.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_NO".equals(parser.getName())){
	            		_FLIGHT_NO = parser.nextText();
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
	            	else if("OILTAX_CHILD".equals(parser.getName())){
	            		_OILTAX_CHILD = parser.nextText();
	            		break;
	            	}
	            	else if("OPERATE_AIRLINE".equals(parser.getName())){
	            		_OPERATE_AIRLINE = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_AIRPORT".equals(parser.getName())){
	            		_ORG_AIRPORT = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_AIRPORT_FULL_NAME".equals(parser.getName())){
	            		_ORG_AIRPORT_FULL_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_AIRPORT_NAME".equals(parser.getName())){
	            		_ORG_AIRPORT_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_CITY".equals(parser.getName())){
	            		_ORG_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("ORG_CITY_CH".equals(parser.getName())){
	            		_ORG_CITY_CH = parser.nextText();
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
	            	else if("PULIC_CLASS_PRICE_C".equals(parser.getName())){
	            		_PULIC_CLASS_PRICE_C = parser.nextText();
	            		break;
	            	}
	            	else if("PULIC_CLASS_PRICE_F".equals(parser.getName())){
	            		_PULIC_CLASS_PRICE_F = parser.nextText();
	            		break;
	            	}
	            	else if("PULIC_CLASS_PRICE_Y".equals(parser.getName())){
	            		_PULIC_CLASS_PRICE_Y = parser.nextText();
	            		break;
	            	}
	            	else if("SEARCH_ID".equals(parser.getName())){
	            		_SEARCH_ID = parser.nextText();
	            		break;
	            	}
	            	else if("SEGMENT_ID".equals(parser.getName())){
	            		_SEGMENT_ID = parser.nextText();
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
	            	else if("STOP_DEPT_DATE".equals(parser.getName())){
	            		_STOP_DEPT_DATE = parser.nextText();
	            		break;
	            	}
	            	else if("STOP_DST_DATE".equals(parser.getName())){
	            		_STOP_DST_DATE = parser.nextText();
	            		break;
	            	}
	            	else if("TRIP_ID".equals(parser.getName())){
	            		_TRIP_ID = parser.nextText();
	            		break;
	            	}
	            	else if("TRIP_OPTION_ID".equals(parser.getName())){
	            		_TRIP_OPTION_ID = parser.nextText();
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
