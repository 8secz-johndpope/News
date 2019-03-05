package com.neusoft.szair.model.soap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public interface SOAPObject {
    public void toXml(XmlSerializer xml, String name, String namespace) throws IOException;

    public String getNamespace();

    public void addAttributesToNode(XmlSerializer xml) throws IOException;

    public void addElementsToNode(XmlSerializer xml) throws IOException;
    
    public void parse(SOAPBinding binding, XmlPullParser el);
    
    public void setexception(Exception _exception);

    public Exception getexception();

}
