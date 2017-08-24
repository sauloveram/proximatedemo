package com.example.saulovera.proximatedemo.wsclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPSWSClient {

    private static final String TAG = HTTPSWSClient.class.getName();
    private HttpsURLConnection connection = null;
    private static String UTF8 = "UTF-8";
    public static final int GET = 1;
    public static final int POST = 2;
    private static final int READ_TIME_OUT = 30000;
    private static final int CONNECTION_TIME_OUT = 30000;
    private static final String URL_SERVER = "https://serveless.proximateapps-services.com.mx";


    public Object WSClientConsumerBasic(String url, String params, int method)
            throws Exception {
        String dataBack = "";
        String urlConnect = "";
        int methodo = method;
        InputStream mInputStream = null;
        url = URL_SERVER + url;

        Log.wtf(TAG, "ejecutando " + url
                + params);


        try {

            urlConnect = url;
            connection = (HttpsURLConnection) new URL(urlConnect)
                    .openConnection();
            //connection.addRequestProperty("JSESSIONATG", tokentosend);
            connection.setReadTimeout(READ_TIME_OUT);
            connection.setConnectTimeout(CONNECTION_TIME_OUT);
            connection.addRequestProperty("Content-type", "application/json");
            connection.setDoInput(true);


            switch (methodo) {
                case GET:
                    connection.setRequestMethod("GET");
                    connection.connect();
                    Log.d(TAG, "PULL CONNECTION");
                    if (connection.getResponseCode() > 199 && 300 > connection.getResponseCode()) {
                        mInputStream = connection.getInputStream();
                    } else {
                        dataBack = otherMethodToRead(connection.getErrorStream());
                        break;
                    }
                    dataBack = otherMethodToRead(mInputStream);
                    break;
                case POST:

                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    DataOutputStream writer = new DataOutputStream(
                            connection.getOutputStream());
                    byte[] utf8JsonString = params.getBytes("UTF8");
                    writer.write(utf8JsonString, 0, utf8JsonString.length);
                    writer.flush();
                    writer.close();
                    if (connection.getResponseCode() > 199 && 300 > connection.getResponseCode()) {
                        mInputStream = connection.getInputStream();
                    } else {
                        dataBack = otherMethodToRead(connection.getErrorStream());
                        break;
                    }
                    dataBack = otherMethodToRead(mInputStream);
                    break;
                default:
                    Log.d(TAG, "BAD PARAMETER SENDED FOR SEND METHOD");
                    break;
            }

            return dataBack;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (mInputStream != null) {
                mInputStream.close();
            }
        }
    }


    private String otherMethodToRead(InputStream stream) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}