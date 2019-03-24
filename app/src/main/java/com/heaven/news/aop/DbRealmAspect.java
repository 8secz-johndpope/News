package com.heaven.news.aop;

import com.heaven.data.manager.DataSource;
import com.heaven.news.engine.AppEngine;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * 作者:Heaven
 * 时间: on 2017/3/28 10:31
 * 邮箱:heavenisme@aliyun.com
 * 数据库存储
 */
@Aspect
public class DbRealmAspect {

    @Pointcut("execution(@com.heaven.annotation.aspect.DbRealm * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();//执行原方法
        Realm realm = Realm.getDefaultInstance();
        final Disposable subscribe = Flowable.fromArray(joinPoint.getArgs())
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(Object obj) throws Exception {
                        return obj instanceof RealmObject || obj instanceof List;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof List) {
                            AppEngine.instance().cacheData(DataSource.DB, null, o);
                        } else {
                            AppEngine.instance().cacheData(DataSource.DB, null, o);
                        }
                    }
                });
    }
}
