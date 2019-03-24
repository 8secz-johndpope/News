package com.heaven.news.aop;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.heaven.annotation.aspect.AuthPayment;
import com.heaven.news.engine.AppEngine;
import com.orhanobut.logger.Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * FileName: com.heaven.flybetter.aop.AuthPaymentAspect.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-09-05 19:52
 *
 * @version V1.0 分享功能
 */
@Aspect
public class AuthPaymentAspect {
    private static final int THUMB_SIZE = 150;

    @Pointcut("call(@com.heaven.annotation.aspect.AuthPayment * *(..))")
    public void callMethod() {
    }


    @Pointcut("execution(@com.heaven.annotation.aspect.AuthPayment * *(..))")
    public void executionMethod() {
    }


    /**
     * handler
     * 不支持@After、@Around
     */
    @Before("handler(java.lang.ArithmeticException)")
    public void handler() {
        Logger.i("handler");
    }

    /**
     * @param throwable
     *
     * @AfterThrowing
     */
    @AfterThrowing(pointcut = "call(* *..*(..))", throwing = "throwable")
    public void anyFuncThrows(Throwable throwable) {
        Logger.i("hurtThrows: " + throwable);
    }


    @AfterReturning(value = "executionMethod() && @annotation(payment)", returning = "authPaymentInfo")
    public void payMoney(@NonNull AuthPaymentInfo authPaymentInfo, @NonNull AuthPayment authPayment) {
        Activity context = AppEngine.instance().getCurActivity();
        AuthPaymentUtil.authPayMoney(context, authPaymentInfo, authPayment);
    }
}
