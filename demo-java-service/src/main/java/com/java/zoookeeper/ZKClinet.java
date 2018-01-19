package com.java.zoookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by iss on 17/11/27.
 */
public class ZKClinet {

    public static void main(String[] args) throws Exception {
        String host="localhost:2181";
        int sessionTime = 50000;
        Watcher watcher = new MyWatcher();
        ZooKeeper zk = new ZooKeeper(host,sessionTime,watcher);
        String path = zk.create("/test","zookeeper test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        byte[] res= zk.getData(path,true,null);
//        zk.exists()


        System.out.println(new String(res));
    }

}
