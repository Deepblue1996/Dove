package com.prohua.dove;

import android.content.Context;
import android.support.annotation.NonNull;

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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 鸽子 Dove
 * Created by Deep on 2018/3/14 0014.
 */

public class Dove {

    private final static String NET_POST = "POST";
    private final static String NET_GET = "GET";

    private final static String NET_UP_ENCODED = "multipart/form-data";
    private final static String NET_URL_ENCODED = "x-www-form-urlencoded";
    private final static String NET_URL_ENCODED_L = "application/x-www-form-urlencoded;charset=UTF-8";

    // Dove object single case
    private static Dove mInstance;
    // Mission service interface singleton
    private static Object doveMission;

    private static Nest nest;

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

        Dove.nest = nest;

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
                // 设置Json数据解析器
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
                .addInterceptor(addParamsInterceptor(nest))
                .addNetworkInterceptor(new DoveNetworkInterceptor(nest.getConnectTime()))
                .addInterceptor(new DoveNotNetworkInterceptor(context, nest.getDisconnectTime()))
                .addInterceptor(getLoggingInterceptor())
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
     * GET request to add parameters
     *
     * @param oldRequest request
     * @param maps       maps
     * @return new request
     */
    private Request addGetParams(Request oldRequest, HashMap<String, String> maps) {
        //添加固定参数
        HttpUrl.Builder urlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        for (Object o : maps.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            urlBuilder.addQueryParameter(key, val);
        }

        //生成新的请求
        return oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(urlBuilder.build())
                .build();
    }

    /**
     * POST request to add parameters
     *
     * @param request request
     * @param maps    maps
     * @return new request
     */
    private Request addPostParams(Request request, HashMap<String, String> maps) {
        // 创建新的请求体
        FormBody.Builder builder = new FormBody.Builder();

        for (Object o : maps.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            builder.add(key, val);
        }

        RequestBody formBody = builder.build();

        // 转换结构
        String postBodyString = bodyToString(request.body());
        // 合并String
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
        // 创建新的请求体
        return request.newBuilder()
                .post(RequestBody.create(MediaType.parse(NET_URL_ENCODED_L),
                        postBodyString))
                .build();
    }

    /**
     * Conversion of String type output
     *
     * @param request request
     * @return String
     */
    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * Add global parameters
     *
     * @return Interceptor
     */
    private Interceptor addParamsInterceptor(final Nest nest) {

        return new Interceptor() {

            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request oldRequest = chain.request();

                Request newRequest = null;
                // 判断请求类型 - POST
                if (NET_POST.equals(oldRequest.method()) && NET_URL_ENCODED.equals(oldRequest.body().contentType().subtype())) {
                    newRequest = addPostParams(oldRequest, nest.getGlobalParams());
                } else if (NET_GET.equals(oldRequest.method())) {
                    newRequest = addGetParams(oldRequest, nest.getGlobalParams());
                } else {
                    newRequest = oldRequest;
                }
                return chain.proceed(newRequest);
            }
        };
    }

    /**
     * clearParams
     */
    public static void clearParams() {
        nest.clearParams();
    }

    /**
     * addGlobalParam
     */
    public static void addGlobalParam(String key, String value) {
        nest.addGlobalParam(key, value);
    }

    /**
     * addGlobalParams
     *
     * @param maps HashMap
     */
    public static void addGlobalParams(HashMap<String, String> maps) {
        nest.addGlobalParams(maps);
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

    /**
     * UpFile MultipartBody.Part
     * <p>
     * * for example use to
     * * @Part MultipartBody.Part name
     *
     * @param key  String Key
     * @param path String Path
     * @return MultipartBody.Part
     */
    public static MultipartBody.Part filePart(String key, String path) {
        File file = new File(path);
        return MultipartBody.Part.createFormData(key, file.getName(),
                RequestBody.create(MediaType.parse(NET_UP_ENCODED), file));
    }

    /**
     * UpFiles MultipartBody.Part[]
     * <p>
     * * for example use to
     * * @Part MultipartBody.Part[] names
     *
     * @param key  List<String> Key
     * @param path List<String> Path
     * @return MultipartBody.Part[]
     */
    public static MultipartBody.Part[] filesPart(List<String> key, List<String> path) {

        MultipartBody.Part[] partList = new MultipartBody.Part[key.size()];

        for (int i = 0; i < key.size(); i++) {
            File file = new File(path.get(i));
            partList[i] = MultipartBody.Part.createFormData(key.get(i), file.getName(),
                    RequestBody.create(MediaType.parse(NET_UP_ENCODED), file));
        }

        return partList;
    }

    /**
     * UpFileParam MultipartBody.Part
     * <p>
     * * for example use to
     * * @Part MultipartBody.Part key
     *
     * @param key   String key
     * @param value String value
     * @return MultipartBody.Part
     */
    public static MultipartBody.Part paramPart(String key, String value) {
        return MultipartBody.Part.createFormData(key, value);
    }

    /**
     * UpFileParams MultipartBody.Part[]
     * <p>
     * * for example use to
     * * @Part MultipartBody.Part[] key
     *
     * @param key   List<String> key
     * @param value List<String> value
     * @return MultipartBody.Part[]
     */
    public static MultipartBody.Part[] paramsPart(List<String> key, List<String> value) {

        MultipartBody.Part[] partList = new MultipartBody.Part[key.size()];

        for (int i = 0; i < key.size(); i++) {
            partList[i] = MultipartBody.Part.createFormData(key.get(i), value.get(i));
        }

        return partList;
    }

}
