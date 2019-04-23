package com.shenzhenair.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {
	/**
     * 默认转换值
     */
    public String value() default "";
    
	/**
     * 声明描述
     */
    public String description() default "";
}
