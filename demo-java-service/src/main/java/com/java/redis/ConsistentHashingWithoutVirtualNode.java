package com.java.redis;

import org.springframework.util.CollectionUtils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by jingchao.zhu on 17/11/29.
 * 不带虚拟节点的一致性hash
 */
public class ConsistentHashingWithoutVirtualNode {

    /**
     * 服务器列表
     */
    private static String[] servers = {"127.0.0.1:6371","127.0.0.1:6372","127.0.0.1:6373","127.0.0.1:6374"};

    /**
     * 获取缓存数据的服务器节点
     * @param key
     * @return
     */
    public String getServer(String key){
        long hashcode = getHash(key);

        TreeMap<Long,String> hashCircleMap = getHashServer();
        SortedMap<Long,String> sortMap = hashCircleMap.tailMap(hashcode);
        Long k = 0L;
        //未命中hash环
        if(CollectionUtils.isEmpty(sortMap)){
            k =hashCircleMap.firstKey();
            return  hashCircleMap.get(k);
        }

        //命中hash环，顺时针取第一个
        k = sortMap.firstKey();
        return sortMap.get(k);

    }

    /**
     * 获取server对应的hash 使用treemap存储
     * @return
     */
    private TreeMap<Long,String> getHashServer(){
        TreeMap<Long,String> serverMap = new TreeMap<Long,String>();
        for (String server: servers) {
            long hashcode = getHash(server);
            serverMap.put(hashcode,server);
        }
        return serverMap;
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    private static long getHash(String str) {
        final long p = 16777619L;
        long hash = 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;
        }

         // 如果算出来的值为负数则取其绝对值
         if (hash < 0) {
             hash = Math.abs(hash);
         }
         return hash;
     }


}
