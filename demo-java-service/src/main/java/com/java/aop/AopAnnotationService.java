package com.java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by iss on 18/1/17.
 * 基于注解的aop
 */
@Service
@Aspect
public class AopAnnotationService {

    @Pointcut("(execution(* com.java.aop.AopAnnotationTargetMethod.*(..))) || @annotation(com.java.annotation.AopAnnotation)")
    private void inspection(){

    }

    /**
     * 后置通知
     * @param
     */
    @After(value = "inspection() && args(message)",argNames = "jp,message")
    private void doAfter(JoinPoint jp,String message) {
        System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        System.out.println();
    }

    /**
     * 环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "inspection() && args(message)",argNames = "pjp,message")
    private Object doAround(ProceedingJoinPoint pjp,String message) throws Throwable {
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
    @Before(value = "inspection() && args(message)",argNames = "jp,message")
    private void doBefore(JoinPoint jp,String message) {
        System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        System.out.println();
    }

    /**
     * 异常通知
     * @param jp
     * @param ex
     */
    @AfterThrowing(value = "inspection() && args(ex,message)",argNames = "jp,ex,message")
    public void doThrowing(JoinPoint jp, Throwable ex,String message) {
        System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
        System.out.println();
    }


    /**
     * 正常返回通知
     * @param jp
     */
    @AfterReturning@Before(value = "inspection() && args(message)",argNames = "jp,message")
    private void afterReturn(JoinPoint jp,String message){
        System.out.println("log method returning: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        System.out.println();
    }
}
