package com.java;

import com.java.redis.ConsistentHashingWithVirtualNode;
import com.java.redis.ConsistentHashingWithoutVirtualNode;
import com.java.redis.JedisPooolUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * Created by iss on 17/6/7.
 */
public class RedisTest {
    @Test
    public void redisPoolTest(){
        ApplicationContext contex = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        JedisPool pool = JedisPooolUtil.getJedisPool();
        Jedis jedis = pool.getResource();
        jedis.get("test");
        pool.returnResource(jedis);
        System.out.println(jedis.get("test"));
    }

    @Test
    public void consistentHashingWithoutVirtualNode(){
        ConsistentHashingWithoutVirtualNode hashing = new ConsistentHashingWithoutVirtualNode();

        Random random = new Random(System.currentTimeMillis());
        for(int i=0;i<10000;i++){
            String key = random.nextLong()+"";
            String server =  hashing.getServer(key.hashCode()+"");
            System.out.println(server);
        }

    }

    @Test
    public void ConsistentHashingWithVirtualNode(){
        ConsistentHashingWithVirtualNode hashingWithVirtualNode = new ConsistentHashingWithVirtualNode();
        Random random = new Random(System.currentTimeMillis());
        int a =0;
        int b =0;
        int c =0;
        int d =0;
        for(int i=0;i<100000;i++){
            String key = random.nextLong()+"";
            key = key.hashCode()+"";
            String server =  hashingWithVirtualNode.getServer(key);

            String host = server.split("#")[0];
            if("127.0.0.1:6371".equals(host)){
                a++;
            }else if("127.0.0.1:6372".equals(host)){
                b++;
            }else if("127.0.0.1:6373".equals(host)){
                c++;
            }else if("127.0.0.1:6374".equals(host)){
                d++;
            }
        }
        System.out.println("127.0.0.1:6371 = "+a);
        System.out.println("127.0.0.1:6372 = "+b);
        System.out.println("127.0.0.1:6373 = "+c);
        System.out.println("127.0.0.1:6374 = "+d);
    }
}
