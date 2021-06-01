package com.goodmap.hospital.config.dataSources;


import java.lang.annotation.*;

/**
 * 自定义注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {
    String value() default "indoormap";
}
