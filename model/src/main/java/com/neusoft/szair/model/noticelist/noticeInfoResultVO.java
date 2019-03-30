package com.neusoft.szair.model.noticelist;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class noticeInfoResultVO extends bookingResponseBaseVO implements SOAPObject
{

    public java.util.List<noticeInfoListVO> _NOTICE_INFO_LIST = null;
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
        if(_NOTICE_INFO_LIST != null) {
        	if(_NOTICE_INFO_LIST.size()>0){
        		for(int i=0,len=_NOTICE_INFO_LIST.size();i<len;i++){
                    xml.startTag(null, "NOTICE_INFO_LIST");
                    _NOTICE_INFO_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "NOTICE_INFO_LIST");
        		}
        	}
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
	            	if("NOTICE_INFO_LIST".equals(parser.getName())){
	            		if(_NOTICE_INFO_LIST==null){
	            			_NOTICE_INFO_LIST = new java.util.ArrayList<noticeInfoListVO>();
	            		}
	            		noticeInfoListVO soapObject = new noticeInfoListVO();
	                    soapObject.parse(binding, parser);
	                    _NOTICE_INFO_LIST.add(soapObject);
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
