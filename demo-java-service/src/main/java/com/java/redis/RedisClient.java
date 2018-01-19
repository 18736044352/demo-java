package com.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by iss on 17/6/6.
 */
public class RedisClient {

    private static JedisPool pool = null;


    public static void main(String[] args) {
        //连接redis服务器，127.0.0.1:6371
        Jedis jedis = new Jedis("127.0.0.1", 6371);
        jedis.set("test","0000");
        System.out.println(jedis.set("test","0000"));
    }



}
