package com.java.aop;

import org.springframework.stereotype.Service;

/**
 * Created by iss on 18/1/17.
 */
@Service
public class AopXmlTargetMethod {

    public AopXmlTargetMethod() {
    }

    public void targetMethod(){
        System.out.println("aop target method");
        System.out.println();
    }

}
