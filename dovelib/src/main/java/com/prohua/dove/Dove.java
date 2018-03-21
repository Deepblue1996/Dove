package com.prohua.dove;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.prohua.dove.utils.HttpResponseFunc;
import com.prohua.dove.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 鸽子 Dove
 * Created by Deep on 2018/3/14 0014.
 */

public class Dove {

    // Dove object single case
    private static Dove mInstance;
    // Mission service interface singleton
    private static Object doveMission;

    /**
     * Birth a nest Global singleton static.
     */
    @SuppressWarnings("unchecked") // Single-interface proxy creation guarded by parameter safety.
    public static <T> T birth(Context context, Nest nest) {
        if (null == mInstance) {
            synchronized (Dove.class) {
                if (null == mInstance) {
                    mInstance = new Dove(context, nest.getBaseUrl(), nest.getInterfaceClass());
                }
            }
        }
        return (T) doveMission;
    }

    /**
     * Dove structure
     */
    private Dove(final Context context, final String baseUrl, Class doveClass) {

        // 缓存容量
        long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
        // 缓存路径
        String cacheFile = context.getCacheDir() + "/http";
        Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                // 有网络时的拦截器
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        okhttp3.Response originalResponse = chain.proceed(chain.request());
                        String cacheControl = originalResponse.header("Cache-Control");
                        // 如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察
                        if (cacheControl == null) {
                            originalResponse = originalResponse.newBuilder()
                                    .header("Cache-Control", "public, max-age=" + TIMEOUT_CONNECT)
                                    .build();
                            return originalResponse;
                        } else {
                            return originalResponse;
                        }
                    }
                })
                // 没网络时的拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();
                        // 离线的时候为7天的缓存。
                        if (!NetUtils.isNetworkAvalible(context)
                                && !NetUtils.isWifiConnected(context)) {
                            request = request.newBuilder()
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT)
                                    .build();
                        }
                        return chain.proceed(request);
                    }
                })
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                // 设置网络请求的Url地址
                .baseUrl(baseUrl)
                // 设置OkHttp
                .client(okHttpClient)
                // 设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava平台
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 建造
                .build();

        doveMission = retrofit.create(doveClass);
    }

    /**
     * Print interceptor
     *
     * @return interceptor
     */
    private Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("Dove", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private static final int TIMEOUT_CONNECT = 5;                   //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    /**
     * Encapsulation method provided by default
     *
     * @param observable Interface method
     * @param observer   Listen method
     * @param <T>        void
     */
    public static <T> void fly(Observable<T> observable, Dover<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //HttpResponseFunc（）为拦截onError事件的拦截器
                .onErrorResumeNext(new HttpResponseFunc<T>())
                .subscribe(observer);
    }
}
