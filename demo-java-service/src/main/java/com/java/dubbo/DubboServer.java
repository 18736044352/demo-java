package com.java.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by iss on 18/1/18.
 */
public class DubboServer {

    private static ClassPathXmlApplicationContext context;

    /**
     * 启动服务
     * @throws InterruptedException
     */
    public void run() throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            // add hook here
        });

        context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring-context.xml" ,"classpath:/dubbo-context.xml"});
        context.start();
        while (true) {
            Thread.sleep(Long.MAX_VALUE);
        }
    }

    public static void main(String[] args) throws Exception {

        DubboServer server = new DubboServer();
        try {
            server.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
