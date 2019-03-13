package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class onwardConditionVO implements SOAPObject
{

    public String _CRM_MEMBER_ID = null;
    public String _HC_TYPE = null;
    public java.util.List<tripInfoVO> _TRIP_INFO_LIST = null;
    
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
        if(_CRM_MEMBER_ID != null) {
            xml.startTag(null, "CRM_MEMBER_ID");
            xml.text(_CRM_MEMBER_ID);
            xml.endTag(null, "CRM_MEMBER_ID");
        }
        if(_HC_TYPE != null) {
            xml.startTag(null, "HC_TYPE");
            xml.text(_HC_TYPE);
            xml.endTag(null, "HC_TYPE");
        }
        if(_TRIP_INFO_LIST != null) {
        	if(_TRIP_INFO_LIST.size()>0){
        		for(int i=0,len=_TRIP_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "TRIP_INFO_LIST");
                    _TRIP_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "TRIP_INFO_LIST");
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
	            	if("CRM_MEMBER_ID".equals(parser.getName())){
	            		_CRM_MEMBER_ID = parser.nextText();
	            		break;
	            	}
	            	else if("HC_TYPE".equals(parser.getName())){
	            		_HC_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("TRIP_INFO_LIST".equals(parser.getName())){
	            		if(_TRIP_INFO_LIST==null){
	            			_TRIP_INFO_LIST = new java.util.ArrayList<tripInfoVO>();
	            		}
	            		tripInfoVO soapObject = new tripInfoVO();
	                    soapObject.parse(binding, parser);
	                    _TRIP_INFO_LIST.add(soapObject);
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
