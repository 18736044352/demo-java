package com.java.thread;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *wait和notify实现阻塞队列
 */
public class BlockingQueue {

    //1、需要一个承装元素的集合
    private final LinkedList<Object> list = new LinkedList<Object>();
    //2、需要一个计数器
    private final AtomicInteger count = new AtomicInteger(0);
    //3、需要指定上限和下限
    private final int maxSize = 5;
    private final int minSize = 0;
    //5、初始化锁对象
    private final Object lock = new Object();

    public String get() throws InterruptedException {
        String p;
        synchronized (lock){
            if(count.get()==minSize){
                lock.wait();
            }
             p = (String) list.removeFirst();
            count.getAndIncrement();
            System.out.println(p+" 被消费");
            lock.notify();
        }

        return p;
    }

    public void put(String param) throws InterruptedException {
        synchronized (lock){
            if(count.get()==maxSize){
                lock.wait();
            }
            list.add(param);
            System.out.println(param+ " 被添加 ");
            count.getAndIncrement();
            lock.notify();
        }


    }



    public void  initQueue(){
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println("初始化队列 容量 5");
    }

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue queue = new BlockingQueue();
        queue.initQueue();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.put("h");
                    queue.put("i");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    queue.get();
                    Thread.sleep(2000);
                    queue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
