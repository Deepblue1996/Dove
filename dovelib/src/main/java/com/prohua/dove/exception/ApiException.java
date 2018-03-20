package com.prohua.dove.exception;

/**
 * 捉异常
 * Created by Deep on 2018/3/15 0015.
 */

public class ApiException extends Exception {

    // 异常代码
    public int code;
    // 提示信息
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}