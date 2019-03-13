package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class productPropertyVO implements SOAPObject
{

    public String _isRequired = null;
    public String _isShow = null;
    public String _productCode = null;
    public String _propertyCode = null;
    public String _propertyDescription = null;
    public String _propertyType = null;
    public String _propertyValue = null;
    public String _validateType = null;
    
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
        if(_isRequired != null) {
            xml.startTag(null, "isRequired");
            xml.text(_isRequired);
            xml.endTag(null, "isRequired");
        }
        if(_isShow != null) {
            xml.startTag(null, "isShow");
            xml.text(_isShow);
            xml.endTag(null, "isShow");
        }
        if(_productCode != null) {
            xml.startTag(null, "productCode");
            xml.text(_productCode);
            xml.endTag(null, "productCode");
        }
        if(_propertyCode != null) {
            xml.startTag(null, "propertyCode");
            xml.text(_propertyCode);
            xml.endTag(null, "propertyCode");
        }
        if(_propertyDescription != null) {
            xml.startTag(null, "propertyDescription");
            xml.text(_propertyDescription);
            xml.endTag(null, "propertyDescription");
        }
        if(_propertyType != null) {
            xml.startTag(null, "propertyType");
            xml.text(_propertyType);
            xml.endTag(null, "propertyType");
        }
        if(_propertyValue != null) {
            xml.startTag(null, "propertyValue");
            xml.text(_propertyValue);
            xml.endTag(null, "propertyValue");
        }
        if(_validateType != null) {
            xml.startTag(null, "validateType");
            xml.text(_validateType);
            xml.endTag(null, "validateType");
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
	            	if("isRequired".equals(parser.getName())){
	            		_isRequired = parser.nextText();
	            		break;
	            	}
	            	else if("isShow".equals(parser.getName())){
	            		_isShow = parser.nextText();
	            		break;
	            	}
	            	else if("productCode".equals(parser.getName())){
	            		_productCode = parser.nextText();
	            		break;
	            	}
	            	else if("propertyCode".equals(parser.getName())){
	            		_propertyCode = parser.nextText();
	            		break;
	            	}
	            	else if("propertyDescription".equals(parser.getName())){
	            		_propertyDescription = parser.nextText();
	            		break;
	            	}
	            	else if("propertyType".equals(parser.getName())){
	            		_propertyType = parser.nextText();
	            		break;
	            	}
	            	else if("propertyValue".equals(parser.getName())){
	            		_propertyValue = parser.nextText();
	            		break;
	            	}
	            	else if("validateType".equals(parser.getName())){
	            		_validateType = parser.nextText();
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
