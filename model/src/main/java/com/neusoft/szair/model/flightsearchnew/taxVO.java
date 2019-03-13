package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class taxVO implements SOAPObject, Serializable
{

    public String _AMOUNT = null;
    public String _CURRENCY_CODE = null;
    public String _PSGR_TYPE = null;
    public String _TAX_CODE = null;
    public String _TAX_NAME = null;
    public String _TAX_TYPE = null;
    
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
        if(_AMOUNT != null) {
            xml.startTag(null, "AMOUNT");
            xml.text(_AMOUNT);
            xml.endTag(null, "AMOUNT");
        }
        if(_CURRENCY_CODE != null) {
            xml.startTag(null, "CURRENCY_CODE");
            xml.text(_CURRENCY_CODE);
            xml.endTag(null, "CURRENCY_CODE");
        }
        if(_PSGR_TYPE != null) {
            xml.startTag(null, "PSGR_TYPE");
            xml.text(_PSGR_TYPE);
            xml.endTag(null, "PSGR_TYPE");
        }
        if(_TAX_CODE != null) {
            xml.startTag(null, "TAX_CODE");
            xml.text(_TAX_CODE);
            xml.endTag(null, "TAX_CODE");
        }
        if(_TAX_NAME != null) {
            xml.startTag(null, "TAX_NAME");
            xml.text(_TAX_NAME);
            xml.endTag(null, "TAX_NAME");
        }
        if(_TAX_TYPE != null) {
            xml.startTag(null, "TAX_TYPE");
            xml.text(_TAX_TYPE);
            xml.endTag(null, "TAX_TYPE");
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
	            	if("AMOUNT".equals(parser.getName())){
	            		_AMOUNT = parser.nextText();
	            		break;
	            	}
	            	else if("CURRENCY_CODE".equals(parser.getName())){
	            		_CURRENCY_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("PSGR_TYPE".equals(parser.getName())){
	            		_PSGR_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("TAX_CODE".equals(parser.getName())){
	            		_TAX_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("TAX_NAME".equals(parser.getName())){
	            		_TAX_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("TAX_TYPE".equals(parser.getName())){
	            		_TAX_TYPE = parser.nextText();
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
