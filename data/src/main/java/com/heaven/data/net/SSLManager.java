package com.heaven.data.net;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * 作者:Heaven
 * 时间: on 2017/8/8 11:47
 * 邮箱:heavenisme@aliyun.com
 */

public class SSLManager {

    protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        SSLSocketFactory sslSocketFactory = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    certificate.close();
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, getTrustManager(context,certificates), new SecureRandom());

            sslSocketFactory = sslContext.getSocketFactory();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

    protected static TrustManager[] getTrustManager(Context context, int[] certificates) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        TrustManager[] trustManager = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    certificate.close();
                }
            }

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            trustManager = trustManagerFactory.getTrustManagers();

        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
            e.printStackTrace();
        }
        return trustManager;
    }

    protected static HostnameVerifier getHostnameVerifier(final String[] hostUrls) {
        return (hostname, session) -> {
            boolean ret = false;
            for (String host : hostUrls) {
                if (host.equalsIgnoreCase(hostname)) {
                    ret = true;
                }
            }
            return ret;
        };
    }
}
