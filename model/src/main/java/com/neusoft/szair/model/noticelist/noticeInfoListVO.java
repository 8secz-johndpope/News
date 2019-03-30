package com.neusoft.szair.model.noticelist;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class noticeInfoListVO implements SOAPObject
{

    public String _INFO_COVERTFLAG = null;
    /**信息顺序*/
    public String _INFO_NORDER = null;
    /**信息的摘要*/
    public String _INFO_SABSTRACT = null;
    public String _INFO_SATTACHMENT = null;
    public String _INFO_SAUTHOR = null;
    public String _INFO_SBODY = null;
    public String _INFO_SCREATEDATE = null;
    public String _INFO_SCREATEUID = null;
    public String _INFO_SDELDATE = null;
    public String _INFO_SDELFLAG = null;
    public String _INFO_SENBODY = null;
    public String _INFO_SENTITLE = null;
    /**唯一ID*/
    public String _INFO_SID = null;//
    public String _INFO_SINFOCLASS = null;
    public String _INFO_SISSUEDATE = null;
    public String _INFO_SKEYWORD = null;
    public String _INFO_SLOCALE = null;
    public String _INFO_SSOURCE = null;
    public String _INFO_SSTATE = null;
    /**标题*/
    public String _INFO_STITLE = null;
    public String _INFO_STITLECOLOR = null;
    public String _INFO_STYPE = null;
    public String _INFO_SUODATEUID = null;
    /**最后修改时间*/
    public String _INFO_SUPDATEDATE = null;
    /**公告链接地址*/
    public String _PAGE_URL = null;
    
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
        if(_INFO_COVERTFLAG != null) {
            xml.startTag(null, "INFO_COVERTFLAG");
            xml.text(_INFO_COVERTFLAG);
            xml.endTag(null, "INFO_COVERTFLAG");
        }
        if(_INFO_NORDER != null) {
            xml.startTag(null, "INFO_NORDER");
            xml.text(_INFO_NORDER);
            xml.endTag(null, "INFO_NORDER");
        }
        if(_INFO_SABSTRACT != null) {
            xml.startTag(null, "INFO_SABSTRACT");
            xml.text(_INFO_SABSTRACT);
            xml.endTag(null, "INFO_SABSTRACT");
        }
        if(_INFO_SATTACHMENT != null) {
            xml.startTag(null, "INFO_SATTACHMENT");
            xml.text(_INFO_SATTACHMENT);
            xml.endTag(null, "INFO_SATTACHMENT");
        }
        if(_INFO_SAUTHOR != null) {
            xml.startTag(null, "INFO_SAUTHOR");
            xml.text(_INFO_SAUTHOR);
            xml.endTag(null, "INFO_SAUTHOR");
        }
        if(_INFO_SBODY != null) {
            xml.startTag(null, "INFO_SBODY");
            xml.text(_INFO_SBODY);
            xml.endTag(null, "INFO_SBODY");
        }
        if(_INFO_SCREATEDATE != null) {
            xml.startTag(null, "INFO_SCREATEDATE");
            xml.text(_INFO_SCREATEDATE);
            xml.endTag(null, "INFO_SCREATEDATE");
        }
        if(_INFO_SCREATEUID != null) {
            xml.startTag(null, "INFO_SCREATEUID");
            xml.text(_INFO_SCREATEUID);
            xml.endTag(null, "INFO_SCREATEUID");
        }
        if(_INFO_SDELDATE != null) {
            xml.startTag(null, "INFO_SDELDATE");
            xml.text(_INFO_SDELDATE);
            xml.endTag(null, "INFO_SDELDATE");
        }
        if(_INFO_SDELFLAG != null) {
            xml.startTag(null, "INFO_SDELFLAG");
            xml.text(_INFO_SDELFLAG);
            xml.endTag(null, "INFO_SDELFLAG");
        }
        if(_INFO_SENBODY != null) {
            xml.startTag(null, "INFO_SENBODY");
            xml.text(_INFO_SENBODY);
            xml.endTag(null, "INFO_SENBODY");
        }
        if(_INFO_SENTITLE != null) {
            xml.startTag(null, "INFO_SENTITLE");
            xml.text(_INFO_SENTITLE);
            xml.endTag(null, "INFO_SENTITLE");
        }
        if(_INFO_SID != null) {
            xml.startTag(null, "INFO_SID");
            xml.text(_INFO_SID);
            xml.endTag(null, "INFO_SID");
        }
        if(_INFO_SINFOCLASS != null) {
            xml.startTag(null, "INFO_SINFOCLASS");
            xml.text(_INFO_SINFOCLASS);
            xml.endTag(null, "INFO_SINFOCLASS");
        }
        if(_INFO_SISSUEDATE != null) {
            xml.startTag(null, "INFO_SISSUEDATE");
            xml.text(_INFO_SISSUEDATE);
            xml.endTag(null, "INFO_SISSUEDATE");
        }
        if(_INFO_SKEYWORD != null) {
            xml.startTag(null, "INFO_SKEYWORD");
            xml.text(_INFO_SKEYWORD);
            xml.endTag(null, "INFO_SKEYWORD");
        }
        if(_INFO_SLOCALE != null) {
            xml.startTag(null, "INFO_SLOCALE");
            xml.text(_INFO_SLOCALE);
            xml.endTag(null, "INFO_SLOCALE");
        }
        if(_INFO_SSOURCE != null) {
            xml.startTag(null, "INFO_SSOURCE");
            xml.text(_INFO_SSOURCE);
            xml.endTag(null, "INFO_SSOURCE");
        }
        if(_INFO_SSTATE != null) {
            xml.startTag(null, "INFO_SSTATE");
            xml.text(_INFO_SSTATE);
            xml.endTag(null, "INFO_SSTATE");
        }
        if(_INFO_STITLE != null) {
            xml.startTag(null, "INFO_STITLE");
            xml.text(_INFO_STITLE);
            xml.endTag(null, "INFO_STITLE");
        }
        if(_INFO_STITLECOLOR != null) {
            xml.startTag(null, "INFO_STITLECOLOR");
            xml.text(_INFO_STITLECOLOR);
            xml.endTag(null, "INFO_STITLECOLOR");
        }
        if(_INFO_STYPE != null) {
            xml.startTag(null, "INFO_STYPE");
            xml.text(_INFO_STYPE);
            xml.endTag(null, "INFO_STYPE");
        }
        if(_INFO_SUODATEUID != null) {
            xml.startTag(null, "INFO_SUODATEUID");
            xml.text(_INFO_SUODATEUID);
            xml.endTag(null, "INFO_SUODATEUID");
        }
        if(_INFO_SUPDATEDATE != null) {
            xml.startTag(null, "INFO_SUPDATEDATE");
            xml.text(_INFO_SUPDATEDATE);
            xml.endTag(null, "INFO_SUPDATEDATE");
        }
        if(_PAGE_URL != null) {
            xml.startTag(null, "PAGE_URL");
            xml.text(_PAGE_URL);
            xml.endTag(null, "PAGE_URL");
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
	            	if("INFO_COVERTFLAG".equals(parser.getName())){
	            		_INFO_COVERTFLAG = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_NORDER".equals(parser.getName())){
	            		_INFO_NORDER = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SABSTRACT".equals(parser.getName())){
	            		_INFO_SABSTRACT = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SATTACHMENT".equals(parser.getName())){
	            		_INFO_SATTACHMENT = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SAUTHOR".equals(parser.getName())){
	            		_INFO_SAUTHOR = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SBODY".equals(parser.getName())){
	            		_INFO_SBODY = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SCREATEDATE".equals(parser.getName())){
	            		_INFO_SCREATEDATE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SCREATEUID".equals(parser.getName())){
	            		_INFO_SCREATEUID = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SDELDATE".equals(parser.getName())){
	            		_INFO_SDELDATE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SDELFLAG".equals(parser.getName())){
	            		_INFO_SDELFLAG = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SENBODY".equals(parser.getName())){
	            		_INFO_SENBODY = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SENTITLE".equals(parser.getName())){
	            		_INFO_SENTITLE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SID".equals(parser.getName())){
	            		_INFO_SID = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SINFOCLASS".equals(parser.getName())){
	            		_INFO_SINFOCLASS = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SISSUEDATE".equals(parser.getName())){
	            		_INFO_SISSUEDATE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SKEYWORD".equals(parser.getName())){
	            		_INFO_SKEYWORD = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SLOCALE".equals(parser.getName())){
	            		_INFO_SLOCALE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SSOURCE".equals(parser.getName())){
	            		_INFO_SSOURCE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SSTATE".equals(parser.getName())){
	            		_INFO_SSTATE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_STITLE".equals(parser.getName())){
	            		_INFO_STITLE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_STITLECOLOR".equals(parser.getName())){
	            		_INFO_STITLECOLOR = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_STYPE".equals(parser.getName())){
	            		_INFO_STYPE = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SUODATEUID".equals(parser.getName())){
	            		_INFO_SUODATEUID = parser.nextText();
	            		break;
	            	}
	            	else if("INFO_SUPDATEDATE".equals(parser.getName())){
	            		_INFO_SUPDATEDATE = parser.nextText();
	            		break;
	            	}
	            	else if("PAGE_URL".equals(parser.getName())){
	            		_PAGE_URL = parser.nextText();
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
