package com.daipi.http.common;

/**
 * author:daijs
 * time:2021/10/19 11:30
 * details:
 */
public class BaseResponse {
    private String msg;
    private boolean success;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
