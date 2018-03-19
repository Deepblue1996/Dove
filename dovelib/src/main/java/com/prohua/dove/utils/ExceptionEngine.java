package com.prohua.dove.utils;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.prohua.dove.base.ErrorCode;
import com.prohua.dove.exception.ApiException;

import org.json.JSONException;

import java.net.ConnectException;

/**
 * 异常分类
 * Created by Deep on 2018/3/15 0015.
 */

public class ExceptionEngine {
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ErrorCode.PARSE_ERROR);
            ex.message = "解析错误";  // 均视为解析错误
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ErrorCode.NETWORD_ERROR);
            ex.message = "连接失败";  // 均视为网络错误
            return ex;
        } else {
            ex = new ApiException(e, ErrorCode.UNKNOWN);
            ex.message = "未知错误";  // 未知错误
            return ex;
        }
    }
}
