package com.neusoft.szair.model.noticelist;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPEnvelope;
import com.neusoft.szair.model.soap.SOAPFault;
import com.neusoft.szair.model.soap.SOAPObject;

import java.util.HashMap;
import java.util.Map;

public class NoticeListWebServiceServiceSoapBinding extends SOAPBinding {
    public NoticeListWebServiceServiceSoapBinding(String endpoint) {
        super(NoticeListWebServiceServiceSoapBinding.class.getPackage().getName(), endpoint);
    }

	public NoticeListWebServiceServiceSoapBinding(String method,SOAPObject request) {
		super(NoticeListWebServiceServiceSoapBinding.class.getPackage().getName(), method, request);
	}
    
    public Map<String, String> getNamespaces() {
        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("ns2", "http://www.w3.org/2001/XMLSchema");
        namespaces.put("ns5", "http://schemas.xmlsoap.org/wsdl/soap/");
        namespaces.put("ns4", "http://impl.webservice.notice.shenzhenair.com/");
        namespaces.put("ns1", "http://schemas.xmlsoap.org/wsdl/");
        namespaces.put("ns0", "http://com/shenzhenair/mobilewebservice/notice");
        namespaces.put("ns3", "http://schemas.xmlsoap.org/soap/http");

        return namespaces;
    }

    public queryNoticeListResponse queryNoticeList(queryNoticeList parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryNoticeList", parameters);
        }

        
        queryNoticeListResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);
	        
	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryNoticeListResponse) {
	                rtrn = (queryNoticeListResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryNoticeListResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryNoticeListResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryNoticeListResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public queryNoticeCountResponse queryNoticeCount(queryNoticeCount parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryNoticeCount", parameters);
        }


        queryNoticeCountResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryNoticeCountResponse) {
	                rtrn = (queryNoticeCountResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryNoticeCountResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryNoticeCountResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryNoticeCountResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

}
