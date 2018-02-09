package com.java.shansong;

/**
 * Created by iss on 18/1/23.
 */
public class CityMain {
    public static void main(String[] args) {
        String c1Str = "北京,天津,石家庄,唐山,太原,沈阳,大连,长春,哈尔滨,上海,南京,无锡,苏州,杭州,宁波,温州,金华,合肥,福州,厦门,济南,青岛,淄博,郑州,武汉,宜昌,长沙,广州,深圳,东莞,南宁,重庆,成都,泸州,贵阳,遵义,昆明,西安,乌鲁木齐";
        String c2Str = "乐山,南昌,惠州,衡阳,南阳,洛阳,常州,泉州,漳州,莆田,襄阳,岳阳,镇江,嘉兴,芜湖,常德,珠海,汕头,佛山,湛江,肇庆,清远,中山,梅州,河源,阳江,云浮,南通,扬州,茂名,南充,海口,汕尾,潮州,揭阳,泰州,绍兴,台州,江门,泰州,绍兴,江门,台州,晋中,九江,伊犁,安阳";

        String[] c1 = c1Str.split(",");
        String[] c2 = c2Str.split(",");
        for(String c:c1){
            if(c2Str.contains(c)){
                System.out.println(c);
            }
        }

    }
}
