package com.neusoft.szair.model.flightsearchnew;


import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPEnvelope;
import com.neusoft.szair.model.soap.SOAPFault;
import com.neusoft.szair.model.soap.SOAPObject;

import java.util.HashMap;
import java.util.Map;

public class FlightSearchWebServiceServiceSoapBinding extends SOAPBinding {
    public FlightSearchWebServiceServiceSoapBinding(String endpoint) {
        super(FlightSearchWebServiceServiceSoapBinding.class.getPackage().getName(), endpoint);
    }
    
    @Override
	public Map<String, String> getNamespaces() {
        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("ns3", "http://impl.webservice.booking.shenzhenair.com/");
        namespaces.put("ns4", "http://schemas.xmlsoap.org/soap/http");
        namespaces.put("ns2", "http://com/shenzhenair/mobilewebservice/booking");
        namespaces.put("ns1", "http://www.w3.org/2001/XMLSchema");
        namespaces.put("ns0", "http://schemas.xmlsoap.org/wsdl/");
        namespaces.put("ns5", "http://schemas.xmlsoap.org/wsdl/soap/");

        return namespaces;
    }

    public queryOnwardFlightResponse queryOnwardFlight(queryOnwardFlight parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryOnwardFlight", parameters);
        }

        
        queryOnwardFlightResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);
	        
	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryOnwardFlightResponse) {
	                rtrn = (queryOnwardFlightResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryOnwardFlightResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryOnwardFlightResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryOnwardFlightResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public queryYlywFlightResponse queryYlywFlight(queryYlywFlight parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryYlywFlight", parameters);
        }


        queryYlywFlightResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryYlywFlightResponse) {
	                rtrn = (queryYlywFlightResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryYlywFlightResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryYlywFlightResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryYlywFlightResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public flightSearchDomesticResponse flightSearchDomestic(flightSearchDomestic parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("flightSearchDomestic", parameters);
        }


        flightSearchDomesticResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof flightSearchDomesticResponse) {
	                rtrn = (flightSearchDomesticResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new flightSearchDomesticResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new flightSearchDomesticResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new flightSearchDomesticResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public queryChangeFlightResponse queryChangeFlight(queryChangeFlight parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryChangeFlight", parameters);
        }


        queryChangeFlightResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryChangeFlightResponse) {
	                rtrn = (queryChangeFlightResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryChangeFlightResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryChangeFlightResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryChangeFlightResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public queryFlightResponse queryFlight(queryFlight parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryFlight", parameters);
        }


        queryFlightResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryFlightResponse) {
	                rtrn = (queryFlightResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryFlightResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryFlightResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryFlightResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public querySTFlightResponse querySTFlight(querySTFlight parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("querySTFlight", parameters);
        }


        querySTFlightResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof querySTFlightResponse) {
	                rtrn = (querySTFlightResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new querySTFlightResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new querySTFlightResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new querySTFlightResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

    public queryFlight4FreeExchangeResponse queryFlight4FreeExchange(queryFlight4FreeExchange parameters) {
        Map<String, SOAPObject> bodyElements = new HashMap<String, SOAPObject>();
        if(parameters != null) {
            bodyElements.put("queryFlight4FreeExchange", parameters);
        }


        queryFlight4FreeExchangeResponse rtrn = null;
        try {
	        SOAPEnvelope env = makeRequest(bodyElements);

	        for(Object o : env.bodyElements) {
	            if(o != null && o instanceof queryFlight4FreeExchangeResponse) {
	                rtrn = (queryFlight4FreeExchangeResponse) o;
	                break;
	            }
	            if(o != null && o instanceof SOAPFault) {
	            	rtrn = new queryFlight4FreeExchangeResponse();
	            	SOAPFault fault = (SOAPFault)o;
	            	java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
	            	rtrn.setexception(exception);
	            	break;
	            }
	        }
	        if(rtrn == null){
	        	rtrn = new queryFlight4FreeExchangeResponse();
	            rtrn.setexception(new java.lang.NullPointerException());
	        }
		}
		catch(java.lang.Exception ex){
			rtrn = new queryFlight4FreeExchangeResponse();
			rtrn.setexception(ex);
		}
        return rtrn;
    }

}
