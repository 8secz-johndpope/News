package com.neusoft.szair.model.noticelist;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class noticeCountResultVO extends bookingResponseBaseVO implements SOAPObject
{

	/**公告数量*/
    public Long _NOTICE_COUNT = null;
    /** 上次更新时间戳 */
    public Long _TAG_TIME = null;
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
        return "http://com/shenzhenair/mobilewebservice/notice";
    }

    public void addAttributesToNode(XmlSerializer xml) throws IOException {
    }

    public void addElementsToNode(XmlSerializer xml) throws IOException
    {
        if(_NOTICE_COUNT != null) {
            xml.startTag(null, "NOTICE_COUNT");
            xml.text(String.valueOf(_NOTICE_COUNT));
            xml.endTag(null, "NOTICE_COUNT");
        }
        if(_TAG_TIME != null) {
            xml.startTag(null, "TAG_TIME");
            xml.text(String.valueOf(_TAG_TIME));
            xml.endTag(null, "TAG_TIME");
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
	            	if("NOTICE_COUNT".equals(parser.getName())){
	            		_NOTICE_COUNT = Long.valueOf(parser.nextText());
	            		break;
	            	}
	            	else if("TAG_TIME".equals(parser.getName())){
	            		_TAG_TIME = Long.valueOf(parser.nextText());
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
