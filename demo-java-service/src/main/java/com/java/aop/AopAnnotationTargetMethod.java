package com.java.aop;

import org.springframework.stereotype.Service;

/**
 * Created by iss on 18/1/17.
 */
@Service
public class AopAnnotationTargetMethod {

    public AopAnnotationTargetMethod() {
    }

    public void targetMethod(String message){
        System.out.println("aop target method");
        System.out.println();
    }

}
