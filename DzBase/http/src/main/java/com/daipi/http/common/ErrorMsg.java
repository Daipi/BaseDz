package com.daipi.http.common;

/**
 * author:daijs
 * time:2021/10/19 11:29
 * details:
 */
public class ErrorMsg {
    public String code;
    public String msg;

    public ErrorMsg(Object code, String msg) {
        this.code = String.valueOf(code);
        this.msg = msg;
    }
}
