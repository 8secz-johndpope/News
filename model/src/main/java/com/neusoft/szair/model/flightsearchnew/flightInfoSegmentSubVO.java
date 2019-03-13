package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPObject;
import com.neusoft.szair.model.soap.UnknownSOAPObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Serializable;

public class flightInfoSegmentSubVO implements SOAPObject, Serializable
{

    public java.util.List<classProductVO> _CLASS_PRODUCT_LIST = null;
    public java.util.List<flightVO> _FLIGHT_LIST = null;
    public String _FLIGHT_NO = null;
    public String _STOP_TIME = null;
    public String _STOP_TIME_BACK = null;
    public java.util.List<zzCityVO> _ZZ_CITY_LIST = null;
	public String _DURATION = null;
	public String _DURATION_BACK = null;

    public String index;

	public flightVO selectedFlightVo;//选择的航班信息
	public flightVO selectedZZFlightVo;//国内中转航班信息

	public classProductVO selectedClassProductVO;//选中 头等/公务 舒适经济 经济
	public classProductSubVO selectedClassProductSubVO;//选中手机特惠 会员特惠 飞行达人
	public classVO selectedClassVO;// 选中 A U M等仓位

	public String flightType = "DC";
	public boolean isSellOff;//是否售罄
	public String _CRM_YJYD;//常客会员，一年内未订票 1:一年内未订票 其它：订票

	/**************国际行程**************/
	public flightVO mITGoFlight;//国际去程
	public flightVO mITGoZZFlight;//国际去程中转

	public flightVO mITBackFlight;//国际回程
	public flightVO mITBackZZFlight;//国际回程中转
	/**************国际行程**************/

	/**************多段使用**************/
	public String oilTaxAdult;
	public String oiltaxChild;
	public String oiltaxBaby;
	public String airportDuty;
	/**************多段使用**************/

	//国深互售需要的字段
	public String caSearchId = null;

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
        if(_CLASS_PRODUCT_LIST != null) {
        	if(_CLASS_PRODUCT_LIST.size()>0){
        		for(int i=0,len=_CLASS_PRODUCT_LIST.size();i<len;i++){
                    xml.startTag(null, "CLASS_PRODUCT_LIST");
                    _CLASS_PRODUCT_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "CLASS_PRODUCT_LIST");
        		}
        	}
        }
        if(_FLIGHT_LIST != null) {
        	if(_FLIGHT_LIST.size()>0){
        		for(int i=0,len=_FLIGHT_LIST.size();i<len;i++){
                    xml.startTag(null, "FLIGHT_LIST");
                    _FLIGHT_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "FLIGHT_LIST");
        		}
        	}
        }
        if(_FLIGHT_NO != null) {
            xml.startTag(null, "FLIGHT_NO");
            xml.text(_FLIGHT_NO);
            xml.endTag(null, "FLIGHT_NO");
        }
        if(_STOP_TIME != null) {
            xml.startTag(null, "STOP_TIME");
            xml.text(_STOP_TIME);
            xml.endTag(null, "STOP_TIME");
        }
        if(_STOP_TIME_BACK != null) {
            xml.startTag(null, "STOP_TIME_BACK");
            xml.text(_STOP_TIME_BACK);
            xml.endTag(null, "STOP_TIME_BACK");
        }
        if(_ZZ_CITY_LIST != null) {
        	if(_ZZ_CITY_LIST.size()>0){
        		for(int i=0,len=_ZZ_CITY_LIST.size();i<len;i++){
                    xml.startTag(null, "ZZ_CITY_LIST");
                    _ZZ_CITY_LIST.get(i).addElementsToNode(xml);
                    xml.endTag(null, "ZZ_CITY_LIST");
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
	            	if("CLASS_PRODUCT_LIST".equals(parser.getName())){
	            		if(_CLASS_PRODUCT_LIST==null){
	            			_CLASS_PRODUCT_LIST = new java.util.ArrayList<classProductVO>();
	            		}
	            		classProductVO soapObject = new classProductVO();
	                    soapObject.parse(binding, parser);
	                    _CLASS_PRODUCT_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_LIST".equals(parser.getName())){
	            		if(_FLIGHT_LIST==null){
	            			_FLIGHT_LIST = new java.util.ArrayList<flightVO>();
	            		}
	            		flightVO soapObject = new flightVO();
	                    soapObject.parse(binding, parser);
	                    _FLIGHT_LIST.add(soapObject);
	            		break;
	            	}
	            	else if("FLIGHT_NO".equals(parser.getName())){
	            		_FLIGHT_NO = parser.nextText();
	            		break;
	            	}
	            	else if("STOP_TIME".equals(parser.getName())){
	            		_STOP_TIME = parser.nextText();
	            		break;
	            	}
	            	else if("STOP_TIME_BACK".equals(parser.getName())){
	            		_STOP_TIME_BACK = parser.nextText();
	            		break;
	            	}
	            	else if("ZZ_CITY_LIST".equals(parser.getName())){
	            		if(_ZZ_CITY_LIST==null){
	            			_ZZ_CITY_LIST = new java.util.ArrayList<zzCityVO>();
	            		}
	            		zzCityVO soapObject = new zzCityVO();
	                    soapObject.parse(binding, parser);
	                    _ZZ_CITY_LIST.add(soapObject);
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
