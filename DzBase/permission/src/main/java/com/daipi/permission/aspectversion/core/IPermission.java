package com.daipi.permission.aspectversion.core;

// 告诉外界
public interface IPermission {

    void ganted(); // 已经授权

    void cancel(); // 取消权限

    void denied(); // 拒绝权限
}
