package com.java.fastjson;

import com.alibaba.fastjson.JSON;

public class JSONDemo {

   private static String str ="{\"reg_ts\":\"2018-05-04 14:23:54\",\"role\":\"normal\",\"status\":\"normal\",\"user_id\":5377866}\n";


    public static void main(String[] args) {

        DubboUserDO dubboUserDO =JSON.parseObject(str,DubboUserDO.class);
        System.out.println(JSON.toJSONString(dubboUserDO));

    }
}
