package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class flightSearchDomesticResultVO extends bookingResponseBaseVO implements SOAPObject, Serializable
{

    public String _CA_SEARCH_ID = null;
    public String _CRM_YJYD = null;
    public java.util.List<flightInfoSegmentVO> _FLIGHTINFO_SEGMENT_LIST = null;
    public String _HC_TYPE = null;
    public String _RESULT_MSG = null;
    public String _OP_RESULT = null;
    
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
        if(_CA_SEARCH_ID != null) {
            xml.startTag(null, "CA_SEARCH_ID");
            xml.text(_CA_SEARCH_ID);
            xml.endTag(null, "CA_SEARCH_ID");
        }
        if(_CRM_YJYD != null) {
            xml.startTag(null, "CRM_YJYD");
            xml.text(_CRM_YJYD);
            xml.endTag(null, "CRM_YJYD");
        }
        if(_FLIGHTINFO_SEGMENT_LIST != null) {
        	if(_FLIGHTINFO_SEGMENT_LIST.size()>0){
        		for(int i=0,len=_FLIGHTINFO_SEGMENT_LIST.size();i<len;i++){
                    xml.startTag(null, "FLIGHTINFO_SEGMENT_LIST");
                    _FLIGHTINFO_SEGMENT_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHTINFO_SEGMENT_LIST");
        		}
        	}
        }
        if(_HC_TYPE != null) {
            xml.startTag(null, "HC_TYPE");
            xml.text(_HC_TYPE);
            xml.endTag(null, "HC_TYPE");
        }
        if(_RESULT_MSG != null) {
            xml.startTag(null, "RESULT_MSG");
            xml.text(_RESULT_MSG);
            xml.endTag(null, "RESULT_MSG");
        }
        if(_OP_RESULT != null) {
            xml.startTag(null, "OP_RESULT");
            xml.text(_OP_RESULT);
            xml.endTag(null, "OP_RESULT");
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
	            	if("CA_SEARCH_ID".equals(parser.getName())){
	            		_CA_SEARCH_ID = parser.nextText();
	            		break;
	            	}
	            	else if("CRM_YJYD".equals(parser.getName())){
	            		_CRM_YJYD = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHTINFO_SEGMENT_LIST".equals(parser.getName())){
	            		if(_FLIGHTINFO_SEGMENT_LIST==null){
	            			_FLIGHTINFO_SEGMENT_LIST = new java.util.ArrayList<flightInfoSegmentVO>();
	            		}
	            		flightInfoSegmentVO soapObject = new flightInfoSegmentVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHTINFO_SEGMENT_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("HC_TYPE".equals(parser.getName())){
	            		_HC_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("RESULT_MSG".equals(parser.getName())){
	            		_RESULT_MSG = parser.nextText();
	            		break;
	            	}
	            	else if("OP_RESULT".equals(parser.getName())){
	            		_OP_RESULT = parser.nextText();
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
