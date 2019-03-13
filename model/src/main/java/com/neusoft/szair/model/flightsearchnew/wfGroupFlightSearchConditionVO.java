package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class wfGroupFlightSearchConditionVO implements SOAPObject
{

    public String _CHALLENGE = null;
    public String _DEP_DATE = null;
    public String _DEP_DATE2 = null;
    public String _DST_CITY = null;
    public String _hcType = null;
    public String _ORG_CITY = null;
    public String _SECCODE = null;
    public String _VALIDATE = null;
    
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
        if(_hcType != null) {
            xml.startTag(null, "hcType");
            xml.text(_hcType);
            xml.endTag(null, "hcType");
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
	            	else if("hcType".equals(parser.getName())){
	            		_hcType = parser.nextText();
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
