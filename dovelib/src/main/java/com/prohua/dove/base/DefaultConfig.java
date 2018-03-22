package com.prohua.dove.base;

/**
 * 默认配置
 * Created by Deep on 2018/3/21 0021.
 */

public class DefaultConfig {
    public static final long SIZE_OF_CACHE = 10 * 1024 * 1024;     // 10m
    public static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; // 7天
    public static final int TIMEOUT_CONNECT = 5;                   // 5秒
}
