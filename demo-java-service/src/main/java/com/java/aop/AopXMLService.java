package com.java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by iss on 18/1/17.
 * 基于XML文件配置
 */
public class AopXMLService {

    /**
     * 后置通知
     * @param jp
     */
    public void doAfter(JoinPoint jp) {
        System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        System.out.println();
    }

    /**
     * 环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("log befor around: " + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
        System.out.println();
        Object retVal = pjp.proceed();
        System.out.println("log after around: " + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
        System.out.println();
        return retVal;
    }

    /**
     * 前置通知
     * @param jp
     */
    public void doBefore(JoinPoint jp) {
        System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        System.out.println();
    }

    /**
     * 异常通知
     * @param jp
     * @param ex
     */
    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
        System.out.println();
    }

    /**
     * 正常返回通知
     * @param jp
     */
    public void afterReturn(JoinPoint jp){
        System.out.println("log method returning: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        System.out.println();
    }

}
