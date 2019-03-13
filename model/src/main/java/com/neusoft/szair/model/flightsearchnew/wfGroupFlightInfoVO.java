package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class wfGroupFlightInfoVO implements SOAPObject
{

    public String _CLASS_PRICE_GROUP = null;
    public String _CLASS_TYPE = null;
    public flightInfoVO _FLIGHT_INFO_BACK = null;
    public flightInfoVO _FLIGHT_INFO_GO = null;
    
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
        if(_CLASS_PRICE_GROUP != null) {
            xml.startTag(null, "CLASS_PRICE_GROUP");
            xml.text(_CLASS_PRICE_GROUP);
            xml.endTag(null, "CLASS_PRICE_GROUP");
        }
        if(_CLASS_TYPE != null) {
            xml.startTag(null, "CLASS_TYPE");
            xml.text(_CLASS_TYPE);
            xml.endTag(null, "CLASS_TYPE");
        }
        if(_FLIGHT_INFO_BACK != null) {
            xml.startTag(null, "FLIGHT_INFO_BACK");
            _FLIGHT_INFO_BACK.addElementsToNode(xml);
            xml.endTag(null, "FLIGHT_INFO_BACK");
        }
        if(_FLIGHT_INFO_GO != null) {
            xml.startTag(null, "FLIGHT_INFO_GO");
            _FLIGHT_INFO_GO.addElementsToNode(xml);
            xml.endTag(null, "FLIGHT_INFO_GO");
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
	            	if("CLASS_PRICE_GROUP".equals(parser.getName())){
	            		_CLASS_PRICE_GROUP = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_TYPE".equals(parser.getName())){
	            		_CLASS_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_INFO_BACK".equals(parser.getName())){
	                    flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_BACK = soapObject;
	            		break;
	            	}
	            	else if("FLIGHT_INFO_GO".equals(parser.getName())){
	                    flightInfoVO soapObject = new flightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_GO = soapObject;
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
