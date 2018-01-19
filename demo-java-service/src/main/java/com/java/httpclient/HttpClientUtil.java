package com.java.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by iss on 17/12/25.
 */
public class HttpClientUtil {

    public static String get(String url) throws IOException {
        HttpGet getRequest = new HttpGet(url);

        HttpClient clinet = new DefaultHttpClient();

        HttpResponse response = clinet.execute(getRequest);

//        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//
//
//        }

        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }


}
