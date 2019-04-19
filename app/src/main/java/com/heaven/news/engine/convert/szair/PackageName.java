package com.heaven.news.engine.convert.szair;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * FileName: com.heaven.news.engine.convert.szair.PackageName.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-04 13:59
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface PackageName {
    String value() default "";
}
