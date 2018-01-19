package com.java;

import com.java.aop.AopAnnotationTargetMethod;
import com.java.aop.AopService;
import com.java.aop.AopXmlTargetMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by iss on 18/1/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-context.xml"})
public class AopTest {
    @Autowired
    private AopXmlTargetMethod aopXmlTargetMethod;
    @Autowired
    private AopAnnotationTargetMethod aopAnnotationTargetMethod;
    @Autowired
    private AopService aopService;
    @Test
    public void aopXmlTest(){
//        aopXmlTargetMethod.targetMethod();
        aopService.targetMethod();
        aopService.targetMethod("hello");
    }

//    @Test
    public void aopAnnotationTargetMethod(){
//        aopAnnotationTargetMethod.targetMethod("hello");
//        aopService.targetMethod();
//        aopService.targetMethod("hello");
    }


}
