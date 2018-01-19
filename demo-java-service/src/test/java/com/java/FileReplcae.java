package com.java;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by iss on 17/12/20.
 */
public class FileReplcae {
    public static void main(String[] args) throws Exception {
        List<File> fileList = new ArrayList<File>();
        File file = new File("/Users/iss/idea-project");
        File[] fs = file.listFiles();

        String name = "/src/main/conf/prod.properties";


        //代码根目录
        for (File f:fs){
            if(f.getName().contains("service")){
                fileList.add(f);
            }
        }

        List<File> fileList1 = new ArrayList<File>();
        //代码存储目录
        for(File f:fileList){
            for(File f1:f.listFiles()){
                if(f1.getName().contains("-service")){
                    fileList1.add(f1);
                    break;
                }
            }

        }

        //代码子目录
        List<File> fileList2 = new ArrayList<File>();
        for(File f2:fileList1){
            for(File f3:f2.listFiles()){
                if(f3.getName().endsWith("-service")){
//                    System.out.println(f3.getPath());
                    fileList2.add(f3);
                    break;
                }
            }

        }

        for (File f: fileList2) {
            String path = f.getPath()+name;
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(path)));


            String cacheObjHost = (String) properties.get("cacheObjHost");
            String cacheObjPort = (String) properties.get("cacheObjPort");

            String cacheListHost = (String) properties.get("cacheListHost");
            String cacheListPort = (String) properties.get("cacheListPort");

            String cacheMapHost = (String) properties.get("cacheMapHost");
            String cacheMapPort = (String) properties.get("cacheMapPort");

            String cacheDefaultHost = (String) properties.get("cacheDefaultHost");
            String cacheDefaultPort = (String) properties.get("cacheDefaultPort");

            System.out.println(f.getName()+" - "+cacheObjHost+":"+cacheObjPort);
//            System.out.println(f.getName()+" - "+cacheListHost+":"+cacheListPort);
//            System.out.println(f.getName()+" - "+cacheMapHost+":"+cacheMapPort);
//            System.out.println(f.getName()+" - "+cacheDefaultHost+":"+cacheDefaultPort);
        }


        System.out.println();
    }
}
