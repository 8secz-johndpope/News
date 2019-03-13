package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class zzClassInfoVO implements SOAPObject
{

    public String _CLASS_CODE = null;
    public java.util.List<classFlightInfoVO> _CLASS_LIST = null;
    
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
        if(_CLASS_CODE != null) {
            xml.startTag(null, "CLASS_CODE");
            xml.text(_CLASS_CODE);
            xml.endTag(null, "CLASS_CODE");
        }
        if(_CLASS_LIST != null) {
        	if(_CLASS_LIST.size()>0){
        		for(int i=0,len=_CLASS_LIST.size();i<len;i++){
                    xml.startTag(null, "CLASS_LIST");
                    _CLASS_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "CLASS_LIST");
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
	            	if("CLASS_CODE".equals(parser.getName())){
	            		_CLASS_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_LIST".equals(parser.getName())){
	            		if(_CLASS_LIST==null){
	            			_CLASS_LIST = new java.util.ArrayList<classFlightInfoVO>();
	            		}
	            		classFlightInfoVO soapObject = new classFlightInfoVO();
	                    soapObject.parse(binding, parser);
	                    _CLASS_LIST.add(soapObject);
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
