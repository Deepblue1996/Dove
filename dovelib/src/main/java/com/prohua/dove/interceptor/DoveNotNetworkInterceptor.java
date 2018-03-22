package com.prohua.dove.interceptor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.prohua.dove.utils.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * 没网络拦截器
 * Created by Deep on 2018/3/21 0021.
 */

public class DoveNotNetworkInterceptor implements Interceptor {

    private Context context;
    private long timeOut;

    public DoveNotNetworkInterceptor(Context context, long timeOut) {
        this.context = context;
        this.timeOut = timeOut;
    }

    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        // 离线的时候为7天的缓存。
        if (!NetUtils.isNetworkAvalible(context)
                && !NetUtils.isWifiConnected(context)) {
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + timeOut)
                    .build();
        }
        return chain.proceed(request);
    }
}
