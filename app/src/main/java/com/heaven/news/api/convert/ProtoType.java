package com.heaven.news.api.convert;

/**
 * FileName: com.heaven.news.api.convert.ProtoType.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-06 13:21
 *
 * @version V1.0 TODO <描述当前版本功能>
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 接口请求返回的数据格式，当前限定取值：{@link #JSON}或{@link #XML}
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface ProtoType {
    int JSON = 1;
    int XML = 2;
    int PROTO = 3;

    int reqType() default XML;

    int resType() default XML;;

}
