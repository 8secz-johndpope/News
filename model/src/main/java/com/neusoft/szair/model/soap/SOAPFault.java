package com.neusoft.szair.model.soap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class SOAPFault implements SOAPObject {
	public static final String NS_SOAP_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
	
    private String _faultcode = null;
    private String _faultstring = null;
    private String _faultactor = null;
    private String _detail = null;
    private Exception _exception = null;
    
    public void setfaultcode(String _faultcode){
    	this._faultcode = _faultcode;
    }
    
    public String getfaultcode(){
    	return this._faultcode;
    }
    
    public void setfaultstring(String _faultstring){
    	this._faultstring = _faultstring;
    }
    
    public String getfaultstring(){
    	return this._faultstring;
    }
    
    public String getfaultactor() {
		return _faultactor;
	}

	public void setfaultactor(String _faultactor) {
		this._faultactor = _faultactor;
	}

	public String getdetail() {
		return _detail;
	}

	public void setdetail(String _detail) {
		this._detail = _detail;
	}
	
    @Override
	public void setexception(Exception _exception){
    	this._exception = _exception;
    }
    
    @Override
	public Exception getexception(){
    	return this._exception;
    }

	@Override
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
    
    @Override
	public String getNamespace(){
    	return NS_SOAP_ENVELOPE;
    }

    @Override
	public void addAttributesToNode(XmlSerializer xml) throws IOException {
    
    }

    @Override
	public void addElementsToNode(XmlSerializer xml) throws IOException {
    
    }
    
    @Override
	public void parse(SOAPBinding binding, XmlPullParser parser)
    {
    	
        int event = 0;
        try {
			event = parser.getEventType();
        
	        while(event != XmlPullParser.END_TAG) {
	            switch(event){   
	            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件
	            	if("faultcode".equals(parser.getName())){
	            		//_faultcode = parser.nextText();
	            		setfaultcode(String.valueOf(parser.nextText()));
	            	}
	            	if("faultstring".equals(parser.getName())){
	            		//_faultstring = parser.nextText();
	            		setfaultstring(String.valueOf(parser.nextText()));
	            	}
	            	if("faultactor".equals(parser.getName())){
	            		//_faultstring = parser.nextText();
	            		setfaultactor(String.valueOf(parser.nextText()));
	            	}
	            	if("detail".equals(parser.getName())){
	            		//_faultstring = parser.nextText();
	            		setdetail(String.valueOf(parser.nextText()));
	            	}
	                break;
	            }  
				event = parser.next();
	        }//end while
        } catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
