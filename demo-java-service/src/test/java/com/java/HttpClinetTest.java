package com.java;

import com.java.httpclient.HttpClientUtil;
import org.junit.Test;

import java.net.URLEncoder;

/**
 * Created by iss on 17/12/25.
 */
public class HttpClinetTest {
    @Test
    public void testGet()  {
        try{
            String mobile = "13788882222";
            String date = URLEncoder.encode("2017-12-27 00:00:00","UTF-8");
            String url = "http://admin.sf.bingex.com/admin/tools/enterprise/repetEnterpriseConsumer?mobile=%s&date=%s";

            url = String.format(url,mobile,date);
            String res = HttpClientUtil.get(url);
            System.out.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
