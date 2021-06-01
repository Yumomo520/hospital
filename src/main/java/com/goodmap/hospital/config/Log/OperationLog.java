package com.goodmap.hospital.config.Log;

import java.lang.annotation.*;

/**
 * @Author 李美泉
 * @Data 2020/9/23 time
 * @Description
 **/
@Documented
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
public @interface OperationLog {
    String value() default "";
    OperationType type() default OperationType.OTHERS;
}
