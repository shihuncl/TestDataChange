package com.yshu.annotation;

import java.lang.annotation.*;

/**
 * Created by chenlei on 2020/4/8.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeforeTransactional {

    String value() default "master";
}
