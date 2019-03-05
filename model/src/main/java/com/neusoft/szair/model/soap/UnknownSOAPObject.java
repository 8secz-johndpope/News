package com.neusoft.szair.model.soap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * File_name: @UnknownSOAPObject.java
 * Instruction: 
 * Author: li-jun-neu
 * Time: 2014年9月19日上午11:34:32
 */
public class UnknownSOAPObject implements SOAPObject {
	    
	private Exception _exception = null;
	
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
	public String getNamespace() {
	    return "";
	}
	
	@Override
	public void addAttributesToNode(XmlSerializer xml) throws IOException {
	}
	
	@Override
	public void addElementsToNode(XmlSerializer xml) throws IOException
	{
	}
	
	@Override
	public void parse(SOAPBinding binding, XmlPullParser parser)
	{
		int event = 0;
		try {
			event = parser.next();
	
	        while(event!= XmlPullParser.END_TAG){
	            switch(event){  
	            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件
	        		UnknownSOAPObject soapObject = new UnknownSOAPObject();
	                soapObject.parse(binding, parser);
	        		break;
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
