package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class classSubVO implements SOAPObject, Serializable
{

    public String _AFTER_HOURS_REFUND = null;
    public String _AFTER_HOURS_RESCHEDULE = null;
    public String _BEFORE_DAYS_REFUND = null;
    public String _BEFORE_DAYS_RESCHEDULE = null;
    public String _BEFORE_HOURS_REFUND = null;
    public String _BEFORE_HOURS_RESCHEDULE = null;
    public String _BEFORE_WEEK_REFUND = null;
    public String _BEFORE_WEEK_RESCHEDULE = null;
    public String _CLASS_CODE = null;
    public String _DISCOUNT = null;
    public String _EI = null;
    public String _FARE_BASE = null;
    public String _FLIGHT_NO = null;
    public String _MAX_REFUND_RATE = null;
    public String _MAX_RESCHEDULE_RATE = null;
    public String _MIN_REFUND_RATE = null;
    public String _MIN_RESCHEDULE_RATE = null;
    public String _SPOL_CODE = null;
    public java.util.List<taxVO> _TAX_LIST = null;
    public String _TC = null;
    
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
        if(_AFTER_HOURS_REFUND != null) {
            xml.startTag(null, "AFTER_HOURS_REFUND");
            xml.text(_AFTER_HOURS_REFUND);
            xml.endTag(null, "AFTER_HOURS_REFUND");
        }
        if(_AFTER_HOURS_RESCHEDULE != null) {
            xml.startTag(null, "AFTER_HOURS_RESCHEDULE");
            xml.text(_AFTER_HOURS_RESCHEDULE);
            xml.endTag(null, "AFTER_HOURS_RESCHEDULE");
        }
        if(_BEFORE_DAYS_REFUND != null) {
            xml.startTag(null, "BEFORE_DAYS_REFUND");
            xml.text(_BEFORE_DAYS_REFUND);
            xml.endTag(null, "BEFORE_DAYS_REFUND");
        }
        if(_BEFORE_DAYS_RESCHEDULE != null) {
            xml.startTag(null, "BEFORE_DAYS_RESCHEDULE");
            xml.text(_BEFORE_DAYS_RESCHEDULE);
            xml.endTag(null, "BEFORE_DAYS_RESCHEDULE");
        }
        if(_BEFORE_HOURS_REFUND != null) {
            xml.startTag(null, "BEFORE_HOURS_REFUND");
            xml.text(_BEFORE_HOURS_REFUND);
            xml.endTag(null, "BEFORE_HOURS_REFUND");
        }
        if(_BEFORE_HOURS_RESCHEDULE != null) {
            xml.startTag(null, "BEFORE_HOURS_RESCHEDULE");
            xml.text(_BEFORE_HOURS_RESCHEDULE);
            xml.endTag(null, "BEFORE_HOURS_RESCHEDULE");
        }
        if(_BEFORE_WEEK_REFUND != null) {
            xml.startTag(null, "BEFORE_WEEK_REFUND");
            xml.text(_BEFORE_WEEK_REFUND);
            xml.endTag(null, "BEFORE_WEEK_REFUND");
        }
        if(_BEFORE_WEEK_RESCHEDULE != null) {
            xml.startTag(null, "BEFORE_WEEK_RESCHEDULE");
            xml.text(_BEFORE_WEEK_RESCHEDULE);
            xml.endTag(null, "BEFORE_WEEK_RESCHEDULE");
        }
        if(_CLASS_CODE != null) {
            xml.startTag(null, "CLASS_CODE");
            xml.text(_CLASS_CODE);
            xml.endTag(null, "CLASS_CODE");
        }
        if(_DISCOUNT != null) {
            xml.startTag(null, "DISCOUNT");
            xml.text(_DISCOUNT);
            xml.endTag(null, "DISCOUNT");
        }
        if(_EI != null) {
            xml.startTag(null, "EI");
            xml.text(_EI);
            xml.endTag(null, "EI");
        }
        if(_FARE_BASE != null) {
            xml.startTag(null, "FARE_BASE");
            xml.text(_FARE_BASE);
            xml.endTag(null, "FARE_BASE");
        }
        if(_FLIGHT_NO != null) {
            xml.startTag(null, "FLIGHT_NO");
            xml.text(_FLIGHT_NO);
            xml.endTag(null, "FLIGHT_NO");
        }
        if(_MAX_REFUND_RATE != null) {
            xml.startTag(null, "MAX_REFUND_RATE");
            xml.text(_MAX_REFUND_RATE);
            xml.endTag(null, "MAX_REFUND_RATE");
        }
        if(_MAX_RESCHEDULE_RATE != null) {
            xml.startTag(null, "MAX_RESCHEDULE_RATE");
            xml.text(_MAX_RESCHEDULE_RATE);
            xml.endTag(null, "MAX_RESCHEDULE_RATE");
        }
        if(_MIN_REFUND_RATE != null) {
            xml.startTag(null, "MIN_REFUND_RATE");
            xml.text(_MIN_REFUND_RATE);
            xml.endTag(null, "MIN_REFUND_RATE");
        }
        if(_MIN_RESCHEDULE_RATE != null) {
            xml.startTag(null, "MIN_RESCHEDULE_RATE");
            xml.text(_MIN_RESCHEDULE_RATE);
            xml.endTag(null, "MIN_RESCHEDULE_RATE");
        }
        if(_SPOL_CODE != null) {
            xml.startTag(null, "SPOL_CODE");
            xml.text(_SPOL_CODE);
            xml.endTag(null, "SPOL_CODE");
        }
        if(_TAX_LIST != null) {
        	if(_TAX_LIST.size()>0){
        		for(int i=0,len=_TAX_LIST.size();i<len;i++){
                    xml.startTag(null, "TAX_LIST");
                    _TAX_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "TAX_LIST");
        		}
        	}
        }
        if(_TC != null) {
            xml.startTag(null, "TC");
            xml.text(_TC);
            xml.endTag(null, "TC");
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
	            	if("AFTER_HOURS_REFUND".equals(parser.getName())){
	            		_AFTER_HOURS_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("AFTER_HOURS_RESCHEDULE".equals(parser.getName())){
	            		_AFTER_HOURS_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_DAYS_REFUND".equals(parser.getName())){
	            		_BEFORE_DAYS_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_DAYS_RESCHEDULE".equals(parser.getName())){
	            		_BEFORE_DAYS_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_HOURS_REFUND".equals(parser.getName())){
	            		_BEFORE_HOURS_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_HOURS_RESCHEDULE".equals(parser.getName())){
	            		_BEFORE_HOURS_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_WEEK_REFUND".equals(parser.getName())){
	            		_BEFORE_WEEK_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_WEEK_RESCHEDULE".equals(parser.getName())){
	            		_BEFORE_WEEK_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_CODE".equals(parser.getName())){
	            		_CLASS_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("DISCOUNT".equals(parser.getName())){
	            		_DISCOUNT = parser.nextText();
	            		break;
	            	}
	            	else if("EI".equals(parser.getName())){
	            		_EI = parser.nextText();
	            		break;
	            	}
	            	else if("FARE_BASE".equals(parser.getName())){
	            		_FARE_BASE = parser.nextText();
	            		break;
	            	}
	            	else if("FLIGHT_NO".equals(parser.getName())){
	            		_FLIGHT_NO = parser.nextText();
	            		break;
	            	}
	            	else if("MAX_REFUND_RATE".equals(parser.getName())){
	            		_MAX_REFUND_RATE = parser.nextText();
	            		break;
	            	}
	            	else if("MAX_RESCHEDULE_RATE".equals(parser.getName())){
	            		_MAX_RESCHEDULE_RATE = parser.nextText();
	            		break;
	            	}
	            	else if("MIN_REFUND_RATE".equals(parser.getName())){
	            		_MIN_REFUND_RATE = parser.nextText();
	            		break;
	            	}
	            	else if("MIN_RESCHEDULE_RATE".equals(parser.getName())){
	            		_MIN_RESCHEDULE_RATE = parser.nextText();
	            		break;
	            	}
	            	else if("SPOL_CODE".equals(parser.getName())){
	            		_SPOL_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("TAX_LIST".equals(parser.getName())){
	            		if(_TAX_LIST==null){
	            			_TAX_LIST = new java.util.ArrayList<taxVO>();
	            		}
	            		taxVO soapObject = new taxVO();
	                    soapObject.parse(binding, parser);
	                    _TAX_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("TC".equals(parser.getName())){
	            		_TC = parser.nextText();
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
