package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class classInfoVO implements SOAPObject
{

    public String _AFTER_COUPON_PRICE = null;
    public String _AFTER_HOURS_REFUND = null;
    public String _AFTER_HOURS_REFUND_MONEY = null;
    public String _AFTER_HOURS_RESCHEDULE = null;
    public String _AFTER_HOURS_RESCHEDULE_MONEY = null;
    public String _AIRPORT_SHUTTLE = null;
    public String _BASE_DISCOUNT = null;
    public String _BEFORE_DAYS_REFUND = null;
    public String _BEFORE_DAYS_REFUND_MONEY = null;
    public String _BEFORE_DAYS_RESCHEDULE = null;
    public String _BEFORE_DAYS_RESCHEDULE_MONEY = null;
    public String _BEFORE_HOURS_REFUND = null;
    public String _BEFORE_HOURS_REFUND_MONEY = null;
    public String _BEFORE_HOURS_RESCHEDULE = null;
    public String _BEFORE_HOURS_RESCHEDULE_MONEY = null;
    public String _BEFORE_WEEK_REFUND = null;
    public String _BEFORE_WEEK_REFUND_MONEY = null;
    public String _BEFORE_WEEK_RESCHEDULE = null;
    public String _BEFORE_WEEK_RESCHEDULE_MONEY = null;
    public String _CA_INDEX = null;
    public String _CHANGE_REFUND = null;
    public String _CHOSE_FLIGHT_SHOW = null;
    public String _CLASS_CODE = null;
    public String _CLASS_NAME = null;
    public String _CLASS_PRICE = null;
    public String _CLASS_TYPE = null;
    public String _CUT_PRICE = null;
    public String _DELAY_INSURE = null;
    public String _DISCOUNT = null;
    public String _GIVECOUPON = null;
    public String _IS_NEW_REFUND_RESCHEDULE = null;
    public String _LOWEST_ACC = null;
    public String _MAXREFUND_MONEY = null;
    public String _MAXRESCHEDULE_MONEY = null;
    public String _MILE_ACC_PRO = null;
    public java.util.List<mileageDeductionVO> _MILEAGE_DEDUCTION_LIST = null;
    public Integer _MILEAGE_POINTS = null;
    public String _MILEAGE_TYPE = null;
    public String _MINREFUND_MONEY = null;
    public String _MINRESCHEDULE_MONEY = null;
    public String _PASSENGER_INFO_SHOW = null;
    public String _PRODUCT_VALUE = null;
    public String _PUBLIC_CLASS_PRICE = null;
    public String _REAL_PRODUCT_VALUE = null;
    public String _SAVE_MONEY = null;
    public Integer _SEG_MILEAGE_POINTS = null;
    public String _SPOLCODE = null;
    public String _STORGE = null;
    public String _TICKET_INFO_SHOW = null;
    public String _BABY_TICKET_PRICE = null;
    public String _CHILD_TICKET_PRICE = null;
    
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
        if(_AFTER_COUPON_PRICE != null) {
            xml.startTag(null, "AFTER_COUPON_PRICE");
            xml.text(_AFTER_COUPON_PRICE);
            xml.endTag(null, "AFTER_COUPON_PRICE");
        }
        if(_AFTER_HOURS_REFUND != null) {
            xml.startTag(null, "AFTER_HOURS_REFUND");
            xml.text(_AFTER_HOURS_REFUND);
            xml.endTag(null, "AFTER_HOURS_REFUND");
        }
        if(_AFTER_HOURS_REFUND_MONEY != null) {
            xml.startTag(null, "AFTER_HOURS_REFUND_MONEY");
            xml.text(_AFTER_HOURS_REFUND_MONEY);
            xml.endTag(null, "AFTER_HOURS_REFUND_MONEY");
        }
        if(_AFTER_HOURS_RESCHEDULE != null) {
            xml.startTag(null, "AFTER_HOURS_RESCHEDULE");
            xml.text(_AFTER_HOURS_RESCHEDULE);
            xml.endTag(null, "AFTER_HOURS_RESCHEDULE");
        }
        if(_AFTER_HOURS_RESCHEDULE_MONEY != null) {
            xml.startTag(null, "AFTER_HOURS_RESCHEDULE_MONEY");
            xml.text(_AFTER_HOURS_RESCHEDULE_MONEY);
            xml.endTag(null, "AFTER_HOURS_RESCHEDULE_MONEY");
        }
        if(_AIRPORT_SHUTTLE != null) {
            xml.startTag(null, "AIRPORT_SHUTTLE");
            xml.text(_AIRPORT_SHUTTLE);
            xml.endTag(null, "AIRPORT_SHUTTLE");
        }
        if(_BASE_DISCOUNT != null) {
            xml.startTag(null, "BASE_DISCOUNT");
            xml.text(_BASE_DISCOUNT);
            xml.endTag(null, "BASE_DISCOUNT");
        }
        if(_BEFORE_DAYS_REFUND != null) {
            xml.startTag(null, "BEFORE_DAYS_REFUND");
            xml.text(_BEFORE_DAYS_REFUND);
            xml.endTag(null, "BEFORE_DAYS_REFUND");
        }
        if(_BEFORE_DAYS_REFUND_MONEY != null) {
            xml.startTag(null, "BEFORE_DAYS_REFUND_MONEY");
            xml.text(_BEFORE_DAYS_REFUND_MONEY);
            xml.endTag(null, "BEFORE_DAYS_REFUND_MONEY");
        }
        if(_BEFORE_DAYS_RESCHEDULE != null) {
            xml.startTag(null, "BEFORE_DAYS_RESCHEDULE");
            xml.text(_BEFORE_DAYS_RESCHEDULE);
            xml.endTag(null, "BEFORE_DAYS_RESCHEDULE");
        }
        if(_BEFORE_DAYS_RESCHEDULE_MONEY != null) {
            xml.startTag(null, "BEFORE_DAYS_RESCHEDULE_MONEY");
            xml.text(_BEFORE_DAYS_RESCHEDULE_MONEY);
            xml.endTag(null, "BEFORE_DAYS_RESCHEDULE_MONEY");
        }
        if(_BEFORE_HOURS_REFUND != null) {
            xml.startTag(null, "BEFORE_HOURS_REFUND");
            xml.text(_BEFORE_HOURS_REFUND);
            xml.endTag(null, "BEFORE_HOURS_REFUND");
        }
        if(_BEFORE_HOURS_REFUND_MONEY != null) {
            xml.startTag(null, "BEFORE_HOURS_REFUND_MONEY");
            xml.text(_BEFORE_HOURS_REFUND_MONEY);
            xml.endTag(null, "BEFORE_HOURS_REFUND_MONEY");
        }
        if(_BEFORE_HOURS_RESCHEDULE != null) {
            xml.startTag(null, "BEFORE_HOURS_RESCHEDULE");
            xml.text(_BEFORE_HOURS_RESCHEDULE);
            xml.endTag(null, "BEFORE_HOURS_RESCHEDULE");
        }
        if(_BEFORE_HOURS_RESCHEDULE_MONEY != null) {
            xml.startTag(null, "BEFORE_HOURS_RESCHEDULE_MONEY");
            xml.text(_BEFORE_HOURS_RESCHEDULE_MONEY);
            xml.endTag(null, "BEFORE_HOURS_RESCHEDULE_MONEY");
        }
        if(_BEFORE_WEEK_REFUND != null) {
            xml.startTag(null, "BEFORE_WEEK_REFUND");
            xml.text(_BEFORE_WEEK_REFUND);
            xml.endTag(null, "BEFORE_WEEK_REFUND");
        }
        if(_BEFORE_WEEK_REFUND_MONEY != null) {
            xml.startTag(null, "BEFORE_WEEK_REFUND_MONEY");
            xml.text(_BEFORE_WEEK_REFUND_MONEY);
            xml.endTag(null, "BEFORE_WEEK_REFUND_MONEY");
        }
        if(_BEFORE_WEEK_RESCHEDULE != null) {
            xml.startTag(null, "BEFORE_WEEK_RESCHEDULE");
            xml.text(_BEFORE_WEEK_RESCHEDULE);
            xml.endTag(null, "BEFORE_WEEK_RESCHEDULE");
        }
        if(_BEFORE_WEEK_RESCHEDULE_MONEY != null) {
            xml.startTag(null, "BEFORE_WEEK_RESCHEDULE_MONEY");
            xml.text(_BEFORE_WEEK_RESCHEDULE_MONEY);
            xml.endTag(null, "BEFORE_WEEK_RESCHEDULE_MONEY");
        }
        if(_CA_INDEX != null) {
            xml.startTag(null, "CA_INDEX");
            xml.text(_CA_INDEX);
            xml.endTag(null, "CA_INDEX");
        }
        if(_CHANGE_REFUND != null) {
            xml.startTag(null, "CHANGE_REFUND");
            xml.text(_CHANGE_REFUND);
            xml.endTag(null, "CHANGE_REFUND");
        }
        if(_CHOSE_FLIGHT_SHOW != null) {
            xml.startTag(null, "CHOSE_FLIGHT_SHOW");
            xml.text(_CHOSE_FLIGHT_SHOW);
            xml.endTag(null, "CHOSE_FLIGHT_SHOW");
        }
        if(_CLASS_CODE != null) {
            xml.startTag(null, "CLASS_CODE");
            xml.text(_CLASS_CODE);
            xml.endTag(null, "CLASS_CODE");
        }
        if(_CLASS_NAME != null) {
            xml.startTag(null, "CLASS_NAME");
            xml.text(_CLASS_NAME);
            xml.endTag(null, "CLASS_NAME");
        }
        if(_CLASS_PRICE != null) {
            xml.startTag(null, "CLASS_PRICE");
            xml.text(_CLASS_PRICE);
            xml.endTag(null, "CLASS_PRICE");
        }
        if(_CLASS_TYPE != null) {
            xml.startTag(null, "CLASS_TYPE");
            xml.text(_CLASS_TYPE);
            xml.endTag(null, "CLASS_TYPE");
        }
        if(_CUT_PRICE != null) {
            xml.startTag(null, "CUT_PRICE");
            xml.text(_CUT_PRICE);
            xml.endTag(null, "CUT_PRICE");
        }
        if(_DELAY_INSURE != null) {
            xml.startTag(null, "DELAY_INSURE");
            xml.text(_DELAY_INSURE);
            xml.endTag(null, "DELAY_INSURE");
        }
        if(_DISCOUNT != null) {
            xml.startTag(null, "DISCOUNT");
            xml.text(_DISCOUNT);
            xml.endTag(null, "DISCOUNT");
        }
        if(_GIVECOUPON != null) {
            xml.startTag(null, "GIVECOUPON");
            xml.text(_GIVECOUPON);
            xml.endTag(null, "GIVECOUPON");
        }
        if(_IS_NEW_REFUND_RESCHEDULE != null) {
            xml.startTag(null, "IS_NEW_REFUND_RESCHEDULE");
            xml.text(_IS_NEW_REFUND_RESCHEDULE);
            xml.endTag(null, "IS_NEW_REFUND_RESCHEDULE");
        }
        if(_LOWEST_ACC != null) {
            xml.startTag(null, "LOWEST_ACC");
            xml.text(_LOWEST_ACC);
            xml.endTag(null, "LOWEST_ACC");
        }
        if(_MAXREFUND_MONEY != null) {
            xml.startTag(null, "MAXREFUND_MONEY");
            xml.text(_MAXREFUND_MONEY);
            xml.endTag(null, "MAXREFUND_MONEY");
        }
        if(_MAXRESCHEDULE_MONEY != null) {
            xml.startTag(null, "MAXRESCHEDULE_MONEY");
            xml.text(_MAXRESCHEDULE_MONEY);
            xml.endTag(null, "MAXRESCHEDULE_MONEY");
        }
        if(_MILE_ACC_PRO != null) {
            xml.startTag(null, "MILE_ACC_PRO");
            xml.text(_MILE_ACC_PRO);
            xml.endTag(null, "MILE_ACC_PRO");
        }
        if(_MILEAGE_DEDUCTION_LIST != null) {
        	if(_MILEAGE_DEDUCTION_LIST.size()>0){
        		for(int i=0,len=_MILEAGE_DEDUCTION_LIST.size();i<len;i++){
                    xml.startTag(null, "MILEAGE_DEDUCTION_LIST");
                    _MILEAGE_DEDUCTION_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "MILEAGE_DEDUCTION_LIST");
        		}
        	}
        }
        if(_MILEAGE_POINTS != null) {
            xml.startTag(null, "MILEAGE_POINTS");
            xml.text(String.valueOf(_MILEAGE_POINTS));
            xml.endTag(null, "MILEAGE_POINTS");
        }
        if(_MILEAGE_TYPE != null) {
            xml.startTag(null, "MILEAGE_TYPE");
            xml.text(_MILEAGE_TYPE);
            xml.endTag(null, "MILEAGE_TYPE");
        }
        if(_MINREFUND_MONEY != null) {
            xml.startTag(null, "MINREFUND_MONEY");
            xml.text(_MINREFUND_MONEY);
            xml.endTag(null, "MINREFUND_MONEY");
        }
        if(_MINRESCHEDULE_MONEY != null) {
            xml.startTag(null, "MINRESCHEDULE_MONEY");
            xml.text(_MINRESCHEDULE_MONEY);
            xml.endTag(null, "MINRESCHEDULE_MONEY");
        }
        if(_PASSENGER_INFO_SHOW != null) {
            xml.startTag(null, "PASSENGER_INFO_SHOW");
            xml.text(_PASSENGER_INFO_SHOW);
            xml.endTag(null, "PASSENGER_INFO_SHOW");
        }
        if(_PRODUCT_VALUE != null) {
            xml.startTag(null, "PRODUCT_VALUE");
            xml.text(_PRODUCT_VALUE);
            xml.endTag(null, "PRODUCT_VALUE");
        }
        if(_PUBLIC_CLASS_PRICE != null) {
            xml.startTag(null, "PUBLIC_CLASS_PRICE");
            xml.text(_PUBLIC_CLASS_PRICE);
            xml.endTag(null, "PUBLIC_CLASS_PRICE");
        }
        if(_REAL_PRODUCT_VALUE != null) {
            xml.startTag(null, "REAL_PRODUCT_VALUE");
            xml.text(_REAL_PRODUCT_VALUE);
            xml.endTag(null, "REAL_PRODUCT_VALUE");
        }
        if(_SAVE_MONEY != null) {
            xml.startTag(null, "SAVE_MONEY");
            xml.text(_SAVE_MONEY);
            xml.endTag(null, "SAVE_MONEY");
        }
        if(_SEG_MILEAGE_POINTS != null) {
            xml.startTag(null, "SEG_MILEAGE_POINTS");
            xml.text(String.valueOf(_SEG_MILEAGE_POINTS));
            xml.endTag(null, "SEG_MILEAGE_POINTS");
        }
        if(_SPOLCODE != null) {
            xml.startTag(null, "SPOLCODE");
            xml.text(_SPOLCODE);
            xml.endTag(null, "SPOLCODE");
        }
        if(_STORGE != null) {
            xml.startTag(null, "STORGE");
            xml.text(_STORGE);
            xml.endTag(null, "STORGE");
        }
        if(_TICKET_INFO_SHOW != null) {
            xml.startTag(null, "TICKET_INFO_SHOW");
            xml.text(_TICKET_INFO_SHOW);
            xml.endTag(null, "TICKET_INFO_SHOW");
        }
        if(_BABY_TICKET_PRICE != null) {
            xml.startTag(null, "BABY_TICKET_PRICE");
            xml.text(_BABY_TICKET_PRICE);
            xml.endTag(null, "BABY_TICKET_PRICE");
        }
        if(_CHILD_TICKET_PRICE != null) {
            xml.startTag(null, "CHILD_TICKET_PRICE");
            xml.text(_CHILD_TICKET_PRICE);
            xml.endTag(null, "CHILD_TICKET_PRICE");
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
	            	if("AFTER_COUPON_PRICE".equals(parser.getName())){
	            		_AFTER_COUPON_PRICE = parser.nextText();
	            		break;
	            	}
	            	else if("AFTER_HOURS_REFUND".equals(parser.getName())){
	            		_AFTER_HOURS_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("AFTER_HOURS_REFUND_MONEY".equals(parser.getName())){
	            		_AFTER_HOURS_REFUND_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("AFTER_HOURS_RESCHEDULE".equals(parser.getName())){
	            		_AFTER_HOURS_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("AFTER_HOURS_RESCHEDULE_MONEY".equals(parser.getName())){
	            		_AFTER_HOURS_RESCHEDULE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("AIRPORT_SHUTTLE".equals(parser.getName())){
	            		_AIRPORT_SHUTTLE = parser.nextText();
	            		break;
	            	}
	            	else if("BASE_DISCOUNT".equals(parser.getName())){
	            		_BASE_DISCOUNT = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_DAYS_REFUND".equals(parser.getName())){
	            		_BEFORE_DAYS_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_DAYS_REFUND_MONEY".equals(parser.getName())){
	            		_BEFORE_DAYS_REFUND_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_DAYS_RESCHEDULE".equals(parser.getName())){
	            		_BEFORE_DAYS_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_DAYS_RESCHEDULE_MONEY".equals(parser.getName())){
	            		_BEFORE_DAYS_RESCHEDULE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_HOURS_REFUND".equals(parser.getName())){
	            		_BEFORE_HOURS_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_HOURS_REFUND_MONEY".equals(parser.getName())){
	            		_BEFORE_HOURS_REFUND_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_HOURS_RESCHEDULE".equals(parser.getName())){
	            		_BEFORE_HOURS_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_HOURS_RESCHEDULE_MONEY".equals(parser.getName())){
	            		_BEFORE_HOURS_RESCHEDULE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_WEEK_REFUND".equals(parser.getName())){
	            		_BEFORE_WEEK_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_WEEK_REFUND_MONEY".equals(parser.getName())){
	            		_BEFORE_WEEK_REFUND_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_WEEK_RESCHEDULE".equals(parser.getName())){
	            		_BEFORE_WEEK_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("BEFORE_WEEK_RESCHEDULE_MONEY".equals(parser.getName())){
	            		_BEFORE_WEEK_RESCHEDULE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("CA_INDEX".equals(parser.getName())){
	            		_CA_INDEX = parser.nextText();
	            		break;
	            	}
	            	else if("CHANGE_REFUND".equals(parser.getName())){
	            		_CHANGE_REFUND = parser.nextText();
	            		break;
	            	}
	            	else if("CHOSE_FLIGHT_SHOW".equals(parser.getName())){
	            		_CHOSE_FLIGHT_SHOW = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_CODE".equals(parser.getName())){
	            		_CLASS_CODE = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_NAME".equals(parser.getName())){
	            		_CLASS_NAME = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_PRICE".equals(parser.getName())){
	            		_CLASS_PRICE = parser.nextText();
	            		break;
	            	}
	            	else if("CLASS_TYPE".equals(parser.getName())){
	            		_CLASS_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("CUT_PRICE".equals(parser.getName())){
	            		_CUT_PRICE = parser.nextText();
	            		break;
	            	}
	            	else if("DELAY_INSURE".equals(parser.getName())){
	            		_DELAY_INSURE = parser.nextText();
	            		break;
	            	}
	            	else if("DISCOUNT".equals(parser.getName())){
	            		_DISCOUNT = parser.nextText();
	            		break;
	            	}
	            	else if("GIVECOUPON".equals(parser.getName())){
	            		_GIVECOUPON = parser.nextText();
	            		break;
	            	}
	            	else if("IS_NEW_REFUND_RESCHEDULE".equals(parser.getName())){
	            		_IS_NEW_REFUND_RESCHEDULE = parser.nextText();
	            		break;
	            	}
	            	else if("LOWEST_ACC".equals(parser.getName())){
	            		_LOWEST_ACC = parser.nextText();
	            		break;
	            	}
	            	else if("MAXREFUND_MONEY".equals(parser.getName())){
	            		_MAXREFUND_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("MAXRESCHEDULE_MONEY".equals(parser.getName())){
	            		_MAXRESCHEDULE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("MILE_ACC_PRO".equals(parser.getName())){
	            		_MILE_ACC_PRO = parser.nextText();
	            		break;
	            	}
	            	else if("MILEAGE_DEDUCTION_LIST".equals(parser.getName())){
	            		if(_MILEAGE_DEDUCTION_LIST==null){
	            			_MILEAGE_DEDUCTION_LIST = new java.util.ArrayList<mileageDeductionVO>();
	            		}
	            		mileageDeductionVO soapObject = new mileageDeductionVO();
	                    soapObject.parse(binding, parser);
	                    _MILEAGE_DEDUCTION_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("MILEAGE_POINTS".equals(parser.getName())){
	            		_MILEAGE_POINTS = Integer.valueOf(parser.nextText());
	            		break;
	            	}
	            	else if("MILEAGE_TYPE".equals(parser.getName())){
	            		_MILEAGE_TYPE = parser.nextText();
	            		break;
	            	}
	            	else if("MINREFUND_MONEY".equals(parser.getName())){
	            		_MINREFUND_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("MINRESCHEDULE_MONEY".equals(parser.getName())){
	            		_MINRESCHEDULE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("PASSENGER_INFO_SHOW".equals(parser.getName())){
	            		_PASSENGER_INFO_SHOW = parser.nextText();
	            		break;
	            	}
	            	else if("PRODUCT_VALUE".equals(parser.getName())){
	            		_PRODUCT_VALUE = parser.nextText();
	            		break;
	            	}
	            	else if("PUBLIC_CLASS_PRICE".equals(parser.getName())){
	            		_PUBLIC_CLASS_PRICE = parser.nextText();
	            		break;
	            	}
	            	else if("REAL_PRODUCT_VALUE".equals(parser.getName())){
	            		_REAL_PRODUCT_VALUE = parser.nextText();
	            		break;
	            	}
	            	else if("SAVE_MONEY".equals(parser.getName())){
	            		_SAVE_MONEY = parser.nextText();
	            		break;
	            	}
	            	else if("SEG_MILEAGE_POINTS".equals(parser.getName())){
	            		_SEG_MILEAGE_POINTS = Integer.valueOf(parser.nextText());
	            		break;
	            	}
	            	else if("SPOLCODE".equals(parser.getName())){
	            		_SPOLCODE = parser.nextText();
	            		break;
	            	}
	            	else if("STORGE".equals(parser.getName())){
	            		_STORGE = parser.nextText();
	            		break;
	            	}
	            	else if("TICKET_INFO_SHOW".equals(parser.getName())){
	            		_TICKET_INFO_SHOW = parser.nextText();
	            		break;
	            	}
	            	else if("BABY_TICKET_PRICE".equals(parser.getName())){
	            		_BABY_TICKET_PRICE = parser.nextText();
	            		break;
	            	}
	            	else if("CHILD_TICKET_PRICE".equals(parser.getName())){
	            		_CHILD_TICKET_PRICE = parser.nextText();
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
