package com.prohua.dove;

import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.prohua.dove.base.Nest;
import com.prohua.dove.interceptor.DoveLoggingInterceptor;
import com.prohua.dove.interceptor.DoveNetworkInterceptor;
import com.prohua.dove.interceptor.DoveNotNetworkInterceptor;
import com.prohua.dove.utils.HttpResponseFunc;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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
                    mInstance = new Dove(context, nest);
                }
            }
        }
        return (T) doveMission;
    }

    /**
     * Dove structure
     */
    private Dove(final Context context, final Nest nest) {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("Dove")            // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        // 缓存路径
        String cacheFile = context.getCacheDir() + "/http";

        Cache cache = new Cache(new File(cacheFile), nest.getCacheSize());

        Retrofit retrofit = new Retrofit.Builder()
                // 设置网络请求的Url地址
                .baseUrl(nest.getBaseUrl())
                // 设置OkHttp
                .client(getDoveHttpClient(context, cache, nest))
                // 设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava平台
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 建造
                .build();

        doveMission = retrofit.create(nest.getInterfaceClass());
    }

    /**
     * OkHttpClient
     *
     * @param context 上下文
     * @param cache   缓存
     * @return OkHttp
     */
    private OkHttpClient getDoveHttpClient(final Context context, final Cache cache, final Nest nest) {

        return new OkHttpClient.Builder()
                .connectTimeout(nest.getConnectTime(), TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                .addNetworkInterceptor(new DoveNetworkInterceptor(nest.getConnectTime()))
                .addInterceptor(new DoveNotNetworkInterceptor(context, nest.getDisconnectTime()))
                .cache(cache)
                .build();
    }

    /**
     * Print interceptor
     *
     * @return interceptor
     */
    private Interceptor getLoggingInterceptor() {
        return new DoveLoggingInterceptor();
    }

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
