package com.trainoo.aop;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class APIProxy {

    private final static Logger LOGGER = LogManager.getLogger(APIProxy.class);

    //切面应用范围是在com.mj.spring.aop.api下面所有的接口函数
    @Around("execution(* com.trainoo.aop..*.*(..))")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Signature signature = proceedingJoinPoint.getSignature();
        String args = Arrays.toString(proceedingJoinPoint.getArgs());

        long start = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                LOGGER.warn(e.getMessage());
            }
            LOGGER.error(String.format("Method:%s call failed  parameter input:%s",
                    signature,
                    args), e);
        } finally {
            LOGGER.info(String.format("method:%s  parameter input:%s carry_out_time:%s ms",
                    signature, args, System.currentTimeMillis() - start));
        }
    }
}