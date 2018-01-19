package com.java.aop;

import com.java.annotation.AopAnnotation;
import org.springframework.stereotype.Service;

/**
 * Created by iss on 18/1/17.
 */
@Service
public class AopService {
    @AopAnnotation
    public void targetMethod(String message){

    }

    public void targetMethod(){

    }
}
