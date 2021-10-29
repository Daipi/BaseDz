package com.daipi.base.base;

import java.io.Serializable;

public class BaseEntity<T> implements Serializable {
    public String msg;
    public int code;
    public boolean success;
    public T data;
}
