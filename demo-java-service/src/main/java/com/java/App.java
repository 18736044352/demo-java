package com.java;

import com.java.img.ImgUtile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        byte[] imgByte = ImgUtile.getImageFromNetByUrl("http://www.ishansong.com/static/homePage/image/index/ssLogo.png");
        ImgUtile.writeImageToDisk(imgByte,"ssLogo");
    }
}
