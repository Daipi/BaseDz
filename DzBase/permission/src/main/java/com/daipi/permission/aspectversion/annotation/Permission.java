package com.daipi.permission.aspectversion.annotation;

import com.derry.premissionstudy.permission.MyPermissionActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限申请注解
 */
@Target(ElementType.METHOD)  // 方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时
public @interface Permission {

    String[] value(); // 具体申请的权限

    // 默认的  权限码是为了以后扩展的，目前用不到，备用的，为了扩展的
    int requestCode() default MyPermissionActivity.PARAM_PERMSSION_CODE_DEFAULT;

}
