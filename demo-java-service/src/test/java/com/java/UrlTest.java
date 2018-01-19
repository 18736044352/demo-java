package com.java;

/**
 * Created by iss on 18/1/18.
 */
public class UrlTest {
    public static void main(String[] args) {
        String host="pay.bingex.com";
        boolean sessionUser = true;
        String url="recharge";


        if (host.startsWith("es") && sessionUser) {
            System.out.println();
        } else if ((host.contains("ishansong.com") || host.contains("bingex.com")) && !host.startsWith("es") && !host.startsWith("pay")) {
            System.out.println();
            if(!url.contains("/recharge/") && !url.contains("/static/") && !url.contains("/userlevelinfo/")){
                System.out.println();
            }

        }
    }
}
