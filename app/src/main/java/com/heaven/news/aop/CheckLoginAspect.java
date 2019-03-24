package com.heaven.news.aop;

import android.support.design.widget.Snackbar;
import android.view.View;


import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.activity.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 作者:Heaven
 * 时间: on 2017/3/28 10:36
 * 邮箱:heavenisme@aliyun.com
 * 登录检查
 */
@Aspect
public class CheckLoginAspect {

    @Pointcut("execution(@com.heaven.annotation.aspect.CheckLogin * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        if (null == AppEngine.instance().getUserInfo()) {
            Snackbar.make(AppEngine.instance().getCurActivity().getWindow().getDecorView(), "请先登录!", Snackbar.LENGTH_LONG)
                    .setAction("登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppEngine.Router(LoginActivity.class);
                        }
                    }).show();
            return;
        }
        joinPoint.proceed();//执行原方法
    }
}

