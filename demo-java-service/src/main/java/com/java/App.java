package com.java;

import com.java.img.ImgUtile;
import org.joda.time.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dt1 = (new DateTime("2017-12-20"));
        DateTime dt2 = (new DateTime("2018-3-21"));

        Years years = Years.yearsBetween(dt1,dt2);
        int year = years.getYears();
        Months months =Months.monthsBetween(dt1,dt2);
        int month = months.getMonths()-year*12;


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse("2017-12-20"));
        calendar.add(Calendar.YEAR,year);
        calendar.add(Calendar.MONTH,month);
        Date date = calendar.getTime();
        String dateStr = sdf.format(date);

        Days days = Days.daysBetween(dt1,dt2);

        int day =days.getDays();
        byte[] imgByte = ImgUtile.getImageFromNetByUrl("http://www.ishansong.com/static/homePage/image/index/ssLogo.png");
        ImgUtile.writeImageToDisk(imgByte,"ssLogo");
    }



}
