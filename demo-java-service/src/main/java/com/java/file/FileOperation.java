package com.java.file;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperation {

    public static void readFile(){
        String sql ="INSERT INTO city (id,type,`name`,long_name,active,province,`order`,dropdownorder,idverifyorder) VALUES(%s,'%s','%s','%s','%s',%s,%s,%s,%s);";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/jingchao/Desktop/china_location_utf8.csv"));//换成你的文件名
            FileWriter writer=new FileWriter("/Users/jingchao/Desktop/insert.sql");

            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            reader.readLine();
            reader.readLine();

            String line = null;
            int i=0;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                Long id = new Long(item[0]);
                String type = item[1];
                String name = item[2];
                String longName = item[3];
                String active = item[4];
                Long province = StringUtils.isEmpty(item[5])?0L:new Long(item[5]);
                Integer order = StringUtils.isEmpty(item[6])?0:new Integer(item[6]);
                Integer dropdownorder = new Integer(item[8]);
                Integer idverifyorder = new Integer(item[9]);
                String insertSql = String.format(sql,id,type,name,longName,active,province,order,dropdownorder,idverifyorder);

                writer.write(insertSql);
                System.out.println(insertSql);
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        readFile();
    }
}
