package com.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/mongodb.xml"})
public class MongoTest{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        System.out.println();
    }


    public static void main(String[] args) {
       new  ClassPathXmlApplicationContext("classpath:/mongodb.xml");
    }
}
