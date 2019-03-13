package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class flightSearchConditionVO implements SOAPObject
{

    public String _CHALLENGE = null;
    public Integer _CHANGE_RATE = null;
    public String _CRM_MEMBER_ID = null;
    public String _DEP_DATE = null;
    public String _DEP_DATE2 = null;
    public String _DST_CITY = null;
    public String _EI = null;
    public String _FZYFLAG = null;
    public String _FLIGHT_CHANNEL = null;
    public String _HC_TYPE = null;
    public String _MEMBER_CARD_NO = null;
    public String _OLD_CLASS_CODE = null;
    public Integer _OLD_PRICE = null;
    public String _ORG_CITY = null;
    public String _SECCODE = null;
    public String _VALIDATE = null;
    public String _ZYFLAG = null;
    
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
        if(_CHALLENGE != null) {
            xml.startTag(null, "CHALLENGE");
            xml.text(_CHALLENGE);
            xml.endTag(null, "CHALLENGE");
        }
        if(_CHANGE_RATE != null) {
            xml.startTag(null, "CHANGE_RATE");
            xml.text(String.valueOf(_CHANGE_RATE));
            xml.endTag(null, "CHANGE_RATE");
        }
        if(_CRM_MEMBER_ID != null) {
            xml.startTag(null, "CRM_MEMBER_ID");
            xml.text(_CRM_MEMBER_ID);
            xml.endTag(null, "CRM_MEMBER_ID");
        }
        if(_DEP_DATE != null) {
            xml.startTag(null, "DEP_DATE");
            xml.text(_DEP_DATE);
            xml.endTag(null, "DEP_DATE");
        }
        if(_DEP_DATE2 != null) {
            xml.startTag(null, "DEP_DATE2");
            xml.text(_DEP_DATE2);
            xml.endTag(null, "DEP_DATE2");
        }
        if(_DST_CITY != null) {
            xml.startTag(null, "DST_CITY");
            xml.text(_DST_CITY);
            xml.endTag(null, "DST_CITY");
        }
        if(_EI != null) {
            xml.startTag(null, "EI");
            xml.text(_EI);
            xml.endTag(null, "EI");
        }
        if(_FZYFLAG != null) {
            xml.startTag(null, "FZYFLAG");
            xml.text(_FZYFLAG);
            xml.endTag(null, "FZYFLAG");
        }
        if(_FLIGHT_CHANNEL != null) {
            xml.startTag(null, "FLIGHT_CHANNEL");
            xml.text(_FLIGHT_CHANNEL);
            xml.endTag(null, "FLIGHT_CHANNEL");
        }
        if(_HC_TYPE != null) {
            xml.startTag(null, "HC_TYPE");
            xml.text(_HC_TYPE);
            xml.endTag(null, "HC_TYPE");
        }
        if(_MEMBER_CARD_NO != null) {
            xml.startTag(null, "MEMBER_CARD_NO");
            xml.text(_MEMBER_CARD_NO);
            xml.endTag(null, "MEMBER_CARD_NO");
        }
        if(_OLD_CLASS_CODE != null) {
            xml.startTag(null, "OLD_CLASS_CODE");
            xml.text(_OLD_CLASS_CODE);
            xml.endTag(null, "OLD_CLASS_CODE");
        }
        if(_OLD_PRICE != null) {
            xml.startTag(null, "OLD_PRICE");
            xml.text(String.valueOf(_OLD_PRICE));
            xml.endTag(null, "OLD_PRICE");
        }
        if(_ORG_CITY != null) {
            xml.startTag(null, "ORG_CITY");
            xml.text(_ORG_CITY);
            xml.endTag(null, "ORG_CITY");
        }
        if(_SECCODE != null) {
            xml.startTag(null, "SECCODE");
            xml.text(_SECCODE);
            xml.endTag(null, "SECCODE");
        }
        if(_VALIDATE != null) {
            xml.startTag(null, "VALIDATE");
            xml.text(_VALIDATE);
            xml.endTag(null, "VALIDATE");
        }
        if(_ZYFLAG != null) {
            xml.startTag(null, "ZYFLAG");
            xml.text(_ZYFLAG);
            xml.endTag(null, "ZYFLAG");
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
	            	if("CHALLENGE".equals(parser.getName())){
	            		_CHALLENGE = parser.nextText();
	            		break;
	            	}
	            	else if("CHANGE_RATE".equals(parser.getName())){
	            		_CHANGE_RATE = Integer.valueOf(parser.nextText());
	            		break;
	            	}
	            	else if("CRM_MEMBER_ID".equals(parser.getName())){
	            		_CRM_MEMBER_ID = parser.nextText();
	            		break;
	            	}
	            	else if("DEP_DATE".equals(parser.getName())){
	            		_DEP_DATE = parser.nextText();
	            		break;
	            	}
	            	else if("DEP_DATE2".equals(parser.getName())){
	            		_DEP_DATE2 = parser.nextText();
	            		break;
	            	}
	            	else if("DST_CITY".equals(parser.getName())){
	            		_DST_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("EI".equals(parser.getName())){
	            		_EI = parser.nextText();
	            		break;
	            	}
	            	else if("FZYFLAG".equals(parser.getName())){
	            		_FZYFLAG = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_CHANNEL".equals(parser.getName())){
	            		_FLIGHT_CHANNEL = parser.nextText();
	            		break;
	            	}
	            	else if("HC_TYPE".equals(parser.getName())){
	            		_HC_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("MEMBER_CARD_NO".equals(parser.getName())){
	            		_MEMBER_CARD_NO = parser.nextText();
	            		break;
	            	}
	            	else if("OLD_CLASS_CODE".equals(parser.getName())){
	            		_OLD_CLASS_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("OLD_PRICE".equals(parser.getName())){
	            		_OLD_PRICE = Integer.valueOf(parser.nextText());
	            		break;
	            	}
	            	else if("ORG_CITY".equals(parser.getName())){
	            		_ORG_CITY = parser.nextText();
	            		break;
	            	}
	            	else if("SECCODE".equals(parser.getName())){
	            		_SECCODE = parser.nextText();
	            		break;
	            	}
	            	else if("VALIDATE".equals(parser.getName())){
	            		_VALIDATE = parser.nextText();
	            		break;
	            	}
	            	else if("ZYFLAG".equals(parser.getName())){
	            		_ZYFLAG = parser.nextText();
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
