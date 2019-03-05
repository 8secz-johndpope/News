package com.neusoft.szair.model.soap;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;

import com.heaven.model.soap.szair.encrypt.CryptUtility;
import com.heaven.model.soap.szair.encrypt.MD5Util;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class SOAPBinding {
    public static final String PROTOFUL = "PROTOFUL";
    public static final String XML = "XML";
    public static final String DATA_TYPE = "DATA-TYPE";

    private String packageName = null;

    private Map<String, String> namespaces;

    private SOAPObject targetRequest;

    private static final String MD5_KEY = "szair_mobile_app";

    private Map<String, SOAPObject> bodyElements = new HashMap<>();

    private String endpoint = "";

    public SOAPBinding(String packageName, String endpoint) {
        if(packageName != null) {
            this.packageName = packageName;
        }

        if(endpoint != null) {
            this.endpoint = SOAPConstants.getHostUrl() + endpoint;
        }

        namespaces = getNamespaces();
    }

    public SOAPBinding(String packageName, String method, SOAPObject targetRequest) {
        if (packageName != null) {
            this.packageName = packageName;
        }

        if (!TextUtils.isEmpty(method) && targetRequest != null) {
            this.targetRequest = targetRequest;
            bodyElements.put(method, targetRequest);
        }
        namespaces = getNamespaces();
    }

    public String makeRequest()
            throws IOException {
        return buildEnvelope(bodyElements);
    }

    public SOAPEnvelope makeResponse(InputStream response) {
        ResponseParser respParser = new ResponseParser(response);
        SOAPEnvelope respObj = respParser.parse();
        return respObj;
    }

    public String buildEnvelope(Map<String, SOAPObject> bodyElements)
            throws IOException {
        return SOAPEnvelope.getXML(namespaces, null, bodyElements);
    }

    public String getMIMEType() {
        return "text/xml";
    }

    public String getNSPrefix(String namespace) {
        //Note this only works if prefixes map to a single namespace...
        for (Map.Entry<String, String> entry : getNamespaces().entrySet()) {
            if (entry.getValue().equals(namespace)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public String getNS(String prefix) {
        return getNamespaces().get(prefix);
    }

    public Object createObject(String uri, String localName) {
        Object rtrn = null;
        String clsName = packageName + "."
                + (uri != null ? getNSPrefix(uri) + "_" : "")
                + localName;
        try {
            rtrn = Class.forName(clsName).newInstance();
        } catch (Exception e) {
            Log.i("SOAPBinding", "Could not create class '" + clsName + ".");
        }

        return rtrn;
    }


    //将key和加密的xml拼接后发送给服务器
    private byte[] encodeXmlToBytes(String xml) throws IOException {
        String time = String.valueOf(System.currentTimeMillis());
        String md5Enc = MD5Util.MD5Encode(MD5_KEY + time);

        // 使用上面生成的串进行AES加密
        //String aesXml = AESUtil.Encrypt(xml, md5Enc.substring(8, 24));

        // 创建发送的XML存储空间
        //byte[] timeBytes = time.getBytes();
        byte[] aesXmlBytes = null;
        byte[] key = null;
        try {
            key = md5Enc.substring(8, 24).getBytes("UTF-8");
            aesXmlBytes = CryptUtility.encrypt(xml.getBytes("UTF-8"), key);
        } catch (UnsupportedEncodingException e) {
            throw new IOException();
        }

        int transDataLength = key.length + aesXmlBytes.length;
        byte[] transDataBytes = new byte[transDataLength];

        for (int i = 0; i < transDataLength; i++) {
            if (i < key.length) {
                transDataBytes[i] = key[i];
            } else {
                transDataBytes[i] = aesXmlBytes[i - key.length];
            }
        }

        transDataBytes = Base64.encode(transDataBytes, Base64.DEFAULT);
        return transDataBytes;
    }

    public class ResponseParser {
        //private Document doc;
        private XmlPullParser parser;

        //public ResponseParser(String xml)
        public ResponseParser(InputStream xml) {
            try {
                //DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                //dbFactory.setNamespaceAware(true);
                //DocumentBuilder builder = dbFactory.newDocumentBuilder();
                //doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
                parser = Xml.newPullParser();
                parser.setInput(xml, "UTF-8");
            } catch (Exception e) {
                throw new IllegalArgumentException("Bad XML response.", e);
            }
        }

        public SOAPEnvelope parse() {
            //if(doc == null)
            if (parser == null) {
                //TODO throw an exception or something...
                return null;
            }

            //Element root = doc.getDocumentElement();
            SOAPEnvelope rtrn = new SOAPEnvelope();
            //rtrn.parse(SOAPBinding.this, root);
            rtrn.parse(SOAPBinding.this, parser);
            return rtrn;
        }
    }


    public abstract Map<String, String> getNamespaces();

    public SOAPObject getTargetRequest() {
        return targetRequest;
    }
}
