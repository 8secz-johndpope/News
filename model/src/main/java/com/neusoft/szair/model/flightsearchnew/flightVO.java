package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class flightVO implements SOAPObject, Serializable
{

    public String _AC_TYPE = null;
    public String _basePrice = null;
    public String _CA_FLIGHT_FLAG = null;
    public String _CARRIER = null;
    public String _CARRIER_FLIGHT_NO = null;
    public String _CLASS_STORAGE_F = null;
    public String _CLASS_STORAGE_Y = null;
    public String _codeShareText = null;
    public String _DST_CITY = null;
    public String _DST_DATE = null;
    public String _DST_TIME = null;
    public String _DURATION = null;
    public String _FLIGHT_NO = null;
    public String _IS_CHECK_SEGMENT = null;
    public String _IS_CODE_SHARE = null;
    public String _MARKET = null;
    public String _MEAL = null;
    public String _ONTIME_RATE = null;
    public String _ORG_CITY = null;
    public String _ORG_DATE = null;
    public String _ORG_TIME = null;
    public String _PULIC_CLASS_PRICE_F = null;
    public String _PULIC_CLASS_PRICE_Y = null;
    public String _SP_MEAL = null;
    public String _STOP_CITY = null;
    public String _STOP_CITY_CH = null;
    public String _STP_TIME = null;
    public String _STOP_QUANTITY = null;
    public String _IS_NEW_FARE = null;
    public java.util.List<taxVO> _TAX_LIST = null;

    /***********单程往返中转使用*********/
    public String oilTaxAdult;
    public String oiltaxChild;
    public String oiltaxBaby;
    public String airportDuty;
    /***********单程往返中转使用*********/
    
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
        if(_basePrice != null) {
            xml.startTag(null, "basePrice");
            xml.text(_basePrice);
            xml.endTag(null, "basePrice");
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
        if(_codeShareText != null) {
            xml.startTag(null, "codeShareText");
            xml.text(_codeShareText);
            xml.endTag(null, "codeShareText");
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
        if(_MARKET != null) {
            xml.startTag(null, "MARKET");
            xml.text(_MARKET);
            xml.endTag(null, "MARKET");
        }
        if(_MEAL != null) {
            xml.startTag(null, "MEAL");
            xml.text(_MEAL);
            xml.endTag(null, "MEAL");
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
        if(_SP_MEAL != null) {
            xml.startTag(null, "SP_MEAL");
            xml.text(_SP_MEAL);
            xml.endTag(null, "SP_MEAL");
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
        if(_STP_TIME != null) {
            xml.startTag(null, "STP_TIME");
            xml.text(_STP_TIME);
            xml.endTag(null, "STP_TIME");
        }

        if(_STOP_QUANTITY != null) {
            xml.startTag(null, "STOP_QUANTITY");
            xml.text(_STOP_QUANTITY);
            xml.endTag(null, "STOP_QUANTITY");
        }

        if(_IS_NEW_FARE != null) {
            xml.startTag(null, "IS_NEW_FARE");
            xml.text(_IS_NEW_FARE);
            xml.endTag(null, "IS_NEW_FARE");
        }

        if(_TAX_LIST != null) {
        	if(_TAX_LIST.size()>0){
        		for(int i=0,len=_TAX_LIST.size();i<len;i++){
                    xml.startTag(null, "TAX_LIST");
                    _TAX_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "TAX_LIST");
        		}
        	}
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
	            	else if("basePrice".equals(parser.getName())){
	            		_basePrice = parser.nextText();
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
	            	else if("CLASS_STORAGE_F".equals(parser.getName())){
	            		_CLASS_STORAGE_F = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_STORAGE_Y".equals(parser.getName())){
	            		_CLASS_STORAGE_Y = parser.nextText();
	            		break;
	            	}
	            	else if("codeShareText".equals(parser.getName())){
	            		_codeShareText = parser.nextText();
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
	            	else if("MARKET".equals(parser.getName())){
	            		_MARKET = parser.nextText();
	            		break;
	            	}
	            	else if("MEAL".equals(parser.getName())){
	            		_MEAL = parser.nextText();
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
	            	else if("PULIC_CLASS_PRICE_F".equals(parser.getName())){
	            		_PULIC_CLASS_PRICE_F = parser.nextText();
	            		break;
	            	}
	            	else if("PULIC_CLASS_PRICE_Y".equals(parser.getName())){
	            		_PULIC_CLASS_PRICE_Y = parser.nextText();
	            		break;
	            	}
	            	else if("SP_MEAL".equals(parser.getName())){
	            		_SP_MEAL = parser.nextText();
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
	            	else if("STP_TIME".equals(parser.getName())){
	            		_STP_TIME = parser.nextText();
	            		break;
	            	}
                    else if("STOP_QUANTITY".equals(parser.getName())){
                        _STOP_QUANTITY = parser.nextText();
                        break;
                    }
                    else if("IS_NEW_FARE".equals(parser.getName())){
                        _IS_NEW_FARE = parser.nextText();
                        break;
                    }

	            	else if("TAX_LIST".equals(parser.getName())){
	            		if(_TAX_LIST==null){
	            			_TAX_LIST = new java.util.ArrayList<taxVO>();
	            		}
	            		taxVO soapObject = new taxVO();
	                    soapObject.parse(binding, parser);
	                    _TAX_LIST.add(soapObject);
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
