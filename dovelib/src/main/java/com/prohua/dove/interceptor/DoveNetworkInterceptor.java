package com.prohua.dove.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;

/**
 * 网络拦截器
 * Created by Deep on 2018/3/21 0021.
 */

public class DoveNetworkInterceptor implements Interceptor {

    private long timeOut;

    public DoveNetworkInterceptor(long timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");
        // 如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察
        if (cacheControl == null) {
            originalResponse = originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + timeOut)
                    .build();
            return originalResponse;
        } else {
            return originalResponse;
        }
    }
}
