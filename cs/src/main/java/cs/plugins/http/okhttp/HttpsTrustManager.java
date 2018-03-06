package cs.plugins.http.okhttp;

import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by shiwx on 2016/10/1.
 */
public class HttpsTrustManager implements X509TrustManager {

    private static final String TAG = "HttpsTrustManager";
    private static TrustManager[] trustManagers = new TrustManager[]{new HttpsTrustManager()};

    @Override
    public void checkClientTrusted(
            X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {

    }

    @Override
    public void checkServerTrusted(
            X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }

    /**
     * for Volley
     */
    public static void allowAllSSL() {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }

        });

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }

    /**
     * for OkHttp
     */
    public static OkHttpClient allowSSL() {
        OkHttpClient okHttpClient;

        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustManagers[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            okHttpClient = builder.build();
        }catch(Exception e){
            e.printStackTrace();
            okHttpClient = new OkHttpClient();
        }

        return okHttpClient;
    }

}
