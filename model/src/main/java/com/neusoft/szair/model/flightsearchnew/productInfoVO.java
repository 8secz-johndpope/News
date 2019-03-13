package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class productInfoVO implements SOAPObject, Serializable
{

    public String _createDate = null;
    public String _description = null;
    public String _giveFlag = null;
    public String _name = null;
    public String _productCode = null;
    public String _productType = null;
    public String _resultMsg = null;
    public String _validateResult = null;
    
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
        if(_createDate != null) {
            xml.startTag(null, "createDate");
            xml.text(_createDate);
            xml.endTag(null, "createDate");
        }
        if(_description != null) {
            xml.startTag(null, "description");
            xml.text(_description);
            xml.endTag(null, "description");
        }
        if(_giveFlag != null) {
            xml.startTag(null, "giveFlag");
            xml.text(_giveFlag);
            xml.endTag(null, "giveFlag");
        }
        if(_name != null) {
            xml.startTag(null, "name");
            xml.text(_name);
            xml.endTag(null, "name");
        }
        if(_productCode != null) {
            xml.startTag(null, "productCode");
            xml.text(_productCode);
            xml.endTag(null, "productCode");
        }
        if(_productType != null) {
            xml.startTag(null, "productType");
            xml.text(_productType);
            xml.endTag(null, "productType");
        }
        if(_resultMsg != null) {
            xml.startTag(null, "resultMsg");
            xml.text(_resultMsg);
            xml.endTag(null, "resultMsg");
        }
        if(_validateResult != null) {
            xml.startTag(null, "validateResult");
            xml.text(_validateResult);
            xml.endTag(null, "validateResult");
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
	            	if("createDate".equals(parser.getName())){
	            		_createDate = parser.nextText();
	            		break;
	            	}
	            	else if("description".equals(parser.getName())){
	            		_description = parser.nextText();
	            		break;
	            	}
	            	else if("giveFlag".equals(parser.getName())){
	            		_giveFlag = parser.nextText();
	            		break;
	            	}
	            	else if("name".equals(parser.getName())){
	            		_name = parser.nextText();
	            		break;
	            	}
	            	else if("productCode".equals(parser.getName())){
	            		_productCode = parser.nextText();
	            		break;
	            	}
	            	else if("productType".equals(parser.getName())){
	            		_productType = parser.nextText();
	            		break;
	            	}
	            	else if("resultMsg".equals(parser.getName())){
	            		_resultMsg = parser.nextText();
	            		break;
	            	}
	            	else if("validateResult".equals(parser.getName())){
	            		_validateResult = parser.nextText();
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
