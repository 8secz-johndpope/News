package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class mileageDeductionVO implements SOAPObject, Serializable
{

    public String _DEDUCTIBLE_AMOUNT = null;
    public String _DEDUCTIBLE_CODE = null;
    public String _DEDUCTIBLE_MILE = null;
    public String _UPPER_LIMIT = null;
    
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
        if(_DEDUCTIBLE_AMOUNT != null) {
            xml.startTag(null, "DEDUCTIBLE_AMOUNT");
            xml.text(_DEDUCTIBLE_AMOUNT);
            xml.endTag(null, "DEDUCTIBLE_AMOUNT");
        }
        if(_DEDUCTIBLE_CODE != null) {
            xml.startTag(null, "DEDUCTIBLE_CODE");
            xml.text(_DEDUCTIBLE_CODE);
            xml.endTag(null, "DEDUCTIBLE_CODE");
        }
        if(_DEDUCTIBLE_MILE != null) {
            xml.startTag(null, "DEDUCTIBLE_MILE");
            xml.text(_DEDUCTIBLE_MILE);
            xml.endTag(null, "DEDUCTIBLE_MILE");
        }
        if(_UPPER_LIMIT != null) {
            xml.startTag(null, "UPPER_LIMIT");
            xml.text(_UPPER_LIMIT);
            xml.endTag(null, "UPPER_LIMIT");
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
	            	if("DEDUCTIBLE_AMOUNT".equals(parser.getName())){
	            		_DEDUCTIBLE_AMOUNT = parser.nextText();
	            		break;
	            	}
	            	else if("DEDUCTIBLE_CODE".equals(parser.getName())){
	            		_DEDUCTIBLE_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("DEDUCTIBLE_MILE".equals(parser.getName())){
	            		_DEDUCTIBLE_MILE = parser.nextText();
	            		break;
	            	}
                    else if("UPPER_LIMIT".equals(parser.getName())){
                        _UPPER_LIMIT = parser.nextText();
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
