package com.neusoft.szair.model.noticelist;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class queryNoticeList implements SOAPObject
{
	/**当前页码(页码1开始,当页码≤0时,默认1页) 必填*/
    public Integer _PAGE_NO = null;
    /**每页条数(当条数≤0时,默认10条) 必填*/
    public Integer _PAGE_COUNT = null;
    
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
        return "http://com/shenzhenair/mobilewebservice/notice";
    }

    public void addAttributesToNode(XmlSerializer xml) throws IOException {
    }

    public void addElementsToNode(XmlSerializer xml) throws IOException
    {
        if(_PAGE_NO != null) {
            xml.startTag(null, "PAGE_NO");
            xml.text(String.valueOf(_PAGE_NO));
            xml.endTag(null, "PAGE_NO");
        }
        if(_PAGE_COUNT != null) {
            xml.startTag(null, "PAGE_COUNT");
            xml.text(String.valueOf(_PAGE_COUNT));
            xml.endTag(null, "PAGE_COUNT");
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
	            	if("PAGE_NO".equals(parser.getName())){
	            		_PAGE_NO = Integer.valueOf(parser.nextText());
	            		break;
	            	}
	            	else if("PAGE_COUNT".equals(parser.getName())){
	            		_PAGE_COUNT = Integer.valueOf(parser.nextText());
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
