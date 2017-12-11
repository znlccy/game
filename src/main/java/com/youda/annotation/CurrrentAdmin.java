package com.youda.annotation;

import java.lang.annotation.*;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-30
 * @introduce 定义判断是否是当前用户的注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrrentAdmin {
}
