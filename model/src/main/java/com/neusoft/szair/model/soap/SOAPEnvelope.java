package com.neusoft.szair.model.soap;

import android.util.Xml;


import com.heaven.model.soap.szair.encrypt.KeyGenerator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SOAPEnvelope implements SOAPObject {
    public static final String NS_SOAP_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
    private static final String NS_XSL_TRANSFORM = "http://www.w3.org/1999/XSL/Transform";
    private static final String NS_XML_SCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String NS_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    private static final String TAG_ENVELOPE = "Envelope";
    private static final String TAG_BODY = "Body";
    private static final String TAG_HEADER = "Header";
    private static final String TAG_FAULT = "Fault";
    
    private Exception _exception  = null;

    public List<Object> bodyElements = new ArrayList<Object>();

    public static String getXML(Map<String, String> namespaces,
                                Map<String, SOAPObject> headerElements,
                                Map<String, SOAPObject> bodyElements) throws IOException {
        StringWriter sw = new StringWriter();

        try {
            //Build the XML envelope
            XmlSerializer xml = Xml.newSerializer();
            xml.setOutput(sw);
            xml.startDocument("UTF-8", Boolean.valueOf(true));

            xml.setPrefix("soap", NS_SOAP_ENVELOPE);
//            xml.setPrefix("xsl", NS_XSL_TRANSFORM);
//            xml.setPrefix("xsi", NS_XML_SCHEMA_INSTANCE);
//            xml.setPrefix("xsd", NS_XML_SCHEMA);

            xml.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            xml.startTag(NS_SOAP_ENVELOPE, TAG_ENVELOPE);

            xml.startTag(NS_SOAP_ENVELOPE, TAG_HEADER);
            xml.attribute(null, "MOBILE_TYPE", new KeyGenerator().getKey());
            
            if(headerElements != null) {
                //TODO Add header elements
            }
            xml.endTag(NS_SOAP_ENVELOPE, TAG_HEADER);

            xml.startTag(NS_SOAP_ENVELOPE, TAG_BODY);

            if(bodyElements != null) {
                //Add body elements
                for(String key : bodyElements.keySet()) {
                    SOAPObject bodyEl = bodyElements.get(key);

                    if(namespaces != null) {
                        for(String prefix : namespaces.keySet()) {
                        	if(namespaces.get(prefix).equals(bodyEl.getNamespace())){
                        		xml.setPrefix(prefix, namespaces.get(prefix));
                        	}
                        }
                    }

                    bodyEl.toXml(xml, key, bodyEl.getNamespace());
                }
            }

            xml.endTag(NS_SOAP_ENVELOPE, TAG_BODY);

            xml.endTag(NS_SOAP_ENVELOPE, TAG_ENVELOPE);
            xml.endDocument();
            xml.flush();

            return sw.toString();
        }
        finally {
            sw.close();
        }
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
    public String getNamespace(){
    	return NS_SOAP_ENVELOPE;
    }

    @Override
    public void addAttributesToNode(XmlSerializer xml) throws IOException {
    
    }

    @Override
    public void addElementsToNode(XmlSerializer xml) throws IOException {
    
    }
    
    @Override
    public void parse(SOAPBinding binding, XmlPullParser parser) {
    	
        int event = 0;
        try {
			event = parser.getEventType();
        
	        while(event!= XmlPullParser.END_DOCUMENT){
	            switch(event){  
	            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件
	                break;  
	            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件
	            	if(TAG_ENVELOPE.equals(parser.getName())){
	            	}
	            	else if(TAG_HEADER.equals(parser.getName())){
	            	}
	            	else if(TAG_BODY.equals(parser.getName())){
	            	}
	            	else if(TAG_FAULT.equals(parser.getName())){
	            		SOAPFault fault = new SOAPFault();
	            		fault.parse(binding, parser);
	            		bodyElements.add(fault);
	            	}
	            	else{
	                    Object o = binding.createObject(null, parser.getName());
	
	                    //Parse the nodes.
	                    if(o instanceof SOAPObject) {
	                        ((SOAPObject) o).parse(binding, parser);
	                    }
	                    bodyElements.add(o);
	            	}
	                break;
	            }  
				event = parser.next();
	        }//end while
        } catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void setexception(Exception _exception){
    	this._exception = _exception;
    }
    
    @Override
    public Exception getexception(){
    	return this._exception;
    }
}
