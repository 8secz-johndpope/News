package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class onwardInfoVO implements SOAPObject
{

    public java.util.List<zzClassInfoVO> _CLASS_INFO_LIST = null;
    public java.util.List<zzFlightInfoVO> _FLIGHT_INFO_LIST = null;
    public String _STOP_TIME = null;
    public java.util.List<zzCityVO> _ZZ_CITY_LIST = null;
    
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
        if(_CLASS_INFO_LIST != null) {
        	if(_CLASS_INFO_LIST.size()>0){
        		for(int i=0,len=_CLASS_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "CLASS_INFO_LIST");
                    _CLASS_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "CLASS_INFO_LIST");
        		}
        	}
        }
        if(_FLIGHT_INFO_LIST != null) {
        	if(_FLIGHT_INFO_LIST.size()>0){
        		for(int i=0,len=_FLIGHT_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_INFO_LIST");
                    _FLIGHT_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_INFO_LIST");
        		}
        	}
        }
        if(_STOP_TIME != null) {
            xml.startTag(null, "STOP_TIME");
            xml.text(_STOP_TIME);
            xml.endTag(null, "STOP_TIME");
        }
        if(_ZZ_CITY_LIST != null) {
        	if(_ZZ_CITY_LIST.size()>0){
        		for(int i=0,len=_ZZ_CITY_LIST.size();i<len;i++){
                    xml.startTag(null, "ZZ_CITY_LIST");
                    _ZZ_CITY_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "ZZ_CITY_LIST");
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
	            	if("CLASS_INFO_LIST".equals(parser.getName())){
	            		if(_CLASS_INFO_LIST==null){
	            			_CLASS_INFO_LIST = new java.util.ArrayList<zzClassInfoVO>();
	            		}
	            		zzClassInfoVO soapObject = new zzClassInfoVO();
	                    soapObject.parse(binding, parser);
	                    _CLASS_INFO_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_INFO_LIST".equals(parser.getName())){
	            		if(_FLIGHT_INFO_LIST==null){
	            			_FLIGHT_INFO_LIST = new java.util.ArrayList<zzFlightInfoVO>();
	            		}
	            		zzFlightInfoVO soapObject = new zzFlightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_INFO_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("STOP_TIME".equals(parser.getName())){
	            		_STOP_TIME = parser.nextText();
	            		break;
	            	}
	            	else if("ZZ_CITY_LIST".equals(parser.getName())){
	            		if(_ZZ_CITY_LIST==null){
	            			_ZZ_CITY_LIST = new java.util.ArrayList<zzCityVO>();
	            		}
	            		zzCityVO soapObject = new zzCityVO();
	                    soapObject.parse(binding, parser);
	                    _ZZ_CITY_LIST.add(soapObject);
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
