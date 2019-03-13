package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class classProductSubVO implements SOAPObject, Serializable
{

    public java.util.List<classVO> _CLASS_INFO_LIST = null;
    public String _PRODOCT_SUB_NAME = null;
    public String _PRODOCT_SUB_TYPE = null;
    public String lowestPrice;
    
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
        if(_PRODOCT_SUB_NAME != null) {
            xml.startTag(null, "PRODOCT_SUB_NAME");
            xml.text(_PRODOCT_SUB_NAME);
            xml.endTag(null, "PRODOCT_SUB_NAME");
        }
        if(_PRODOCT_SUB_TYPE != null) {
            xml.startTag(null, "PRODOCT_SUB_TYPE");
            xml.text(_PRODOCT_SUB_TYPE);
            xml.endTag(null, "PRODOCT_SUB_TYPE");
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
	            			_CLASS_INFO_LIST = new java.util.ArrayList<classVO>();
	            		}
	            		classVO soapObject = new classVO();
	                    soapObject.parse(binding, parser);
	                    _CLASS_INFO_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("PRODOCT_SUB_NAME".equals(parser.getName())){
	            		_PRODOCT_SUB_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("PRODOCT_SUB_TYPE".equals(parser.getName())){
	            		_PRODOCT_SUB_TYPE = parser.nextText();
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
