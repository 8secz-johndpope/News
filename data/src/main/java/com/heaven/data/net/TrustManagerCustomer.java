package com.heaven.data.net;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * 作者:Heaven
 * 时间: on 2017/8/8 13:12
 * 邮箱:heavenisme@aliyun.com
 */

public class TrustManagerCustomer implements X509TrustManager {
    //证书中的公钥
    public String PUB_KEY;

    public TrustManagerCustomer(String PUB_KEY) {
        this.PUB_KEY = PUB_KEY;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null) {
            throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
        }

        if (!(chain.length > 0)) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        }

        if (!(null != authType && authType.equalsIgnoreCase("RSA"))) {
            throw new CertificateException("checkServerTrusted: AuthType is not RSA");
        }

        // Perform customary SSL/TLS checks
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init((KeyStore) null);
            for (TrustManager trustManager : tmf.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
            }
        } catch (Exception e) {
            throw new CertificateException(e);
        }
        // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
        // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
        RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();

        String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
        // Pin it!
        final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

        if (!expected) {
            throw new CertificateException("checkServerTrusted: Expected public key: "
                    + PUB_KEY + ", got public key:" + encoded);
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
