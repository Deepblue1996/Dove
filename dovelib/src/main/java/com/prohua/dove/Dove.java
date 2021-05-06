package com.prohua.dove;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.prohua.dove.base.Nest;
import com.prohua.dove.interceptor.DoveLoggingInterceptor;
import com.prohua.dove.interceptor.DoveNetworkInterceptor;
import com.prohua.dove.interceptor.DoveNotNetworkInterceptor;
import com.prohua.dove.utils.HttpResponseFunc;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zchu.rxcache.RxCache;
import com.zchu.rxcache.data.CacheResult;
import com.zchu.rxcache.diskconverter.GsonDiskConverter;
import com.zchu.rxcache.stategy.CacheStrategy;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 鸽子 Dove
 * Created by Deep on 2018/3/14 0014.
 */
@SuppressWarnings("all")
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

    private static RxCache rxCache;

    private OkHttpClient okHttpClient;

    private Activity activity;

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
     * 释放内存
     * @param <T>
     */
    public static <T> void destory() {
        mInstance.okHttpClient.dispatcher().executorService().shutdown();   //清除并关闭线程池
        mInstance.okHttpClient.connectionPool().evictAll();                 //清除并关闭连接池
        mInstance.okHttpClient = null;
        mInstance = null;
    }

    /**
     * Dove structure
     */
    @SuppressWarnings("unchecked")
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
        String cacheFile = context.getCacheDir() + File.separator + "http";

        //Cache cache = new Cache(new File(cacheFile), nest.getCacheSize());

        rxCache = new RxCache.Builder()
                // 当版本号改变,缓存路径下存储的所有数据都会被清除掉
                .appVersion(1)
                .diskDir(new File(cacheFile + File.separator + "data-cache"))
                // 支持Serializable、Json(GsonDiskConverter)
                .diskConverter(new GsonDiskConverter())
                .memoryMax(2 * 1024 * 1024)
                .diskMax(20 * 1024 * 1024)
                .build();

        okHttpClient = getDoveHttpClient(context, nest);

        Retrofit retrofit = new Retrofit.Builder()
                // 设置网络请求的Url地址
                .baseUrl(nest.getBaseUrl())
                // 设置OkHttp
                .client(okHttpClient)
                // 设置Json数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava平台
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 建造
                .build();

        doveMission = retrofit.create(nest.getInterfaceClass());
    }

    /**
     * 简化作用，全局（适用于单Activity）
     *
     * @param activity 活动
     */
    public static void workInit(Activity activity) {
        if (null == mInstance) {
            return;
        }
        mInstance.activity = activity;
    }

    /**
     * OkHttpClient
     *
     * @param context 上下文
     * @return OkHttp
     */
    private OkHttpClient getDoveHttpClient(final Context context, final Nest nest) {

        return new OkHttpClient.Builder()
                .connectTimeout(nest.getConnectTime(), TimeUnit.SECONDS)
                .addInterceptor(addParamsInterceptor(nest))
                .addNetworkInterceptor(new DoveNetworkInterceptor(nest.getConnectTime()))
                .addInterceptor(new DoveNotNetworkInterceptor(context, nest.getDisconnectTime()))
                .addInterceptor(getLoggingInterceptor())
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
     * @param headers    headers
     * @return new request
     */
    private Request addGetParams(Request oldRequest, HashMap<String, String> maps, HashMap<String, String> headers) {
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

        Request.Builder newBuilder = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(urlBuilder.build());

        for (Object o : headers.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            newBuilder.header(key, val);
        }

        //生成新的请求
        return newBuilder.build();
    }

    /**
     * POST request to add parameters
     *
     * @param oldRequest oldRequest
     * @param maps       maps
     * @param headers    headers
     * @return new request
     */
    private Request addPostParams(Request oldRequest, HashMap<String, String> maps, HashMap<String, String> headers) {
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
        String postBodyString = bodyToString(oldRequest.body());
        // 合并String
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);

        Request.Builder newBuilder = oldRequest.newBuilder()
                .post(RequestBody.create(postBodyString, MediaType.parse(NET_URL_ENCODED_L)));

        for (Object o : headers.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            newBuilder.header(key, val);
        }

        // 创建新的请求体
        return newBuilder.build();
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
    // Single-interface proxy creation guarded by parameter safety.
    private Interceptor addParamsInterceptor(final Nest nest) {

        return chain -> {
            Request oldRequest = chain.request();

            Request newRequest;
            // 判断请求类型 - POST
            if (NET_POST.equals(oldRequest.method()) && NET_URL_ENCODED.equals(
                    Objects.requireNonNull(Objects.requireNonNull(oldRequest.body()).contentType()).subtype())) {
                newRequest = addPostParams(oldRequest, nest.getGlobalParams(), nest.getHeaders());
            } else if (NET_GET.equals(oldRequest.method())) {
                newRequest = addGetParams(oldRequest, nest.getGlobalParams(), nest.getHeaders());
            } else {
                newRequest = oldRequest;
            }

            return chain.proceed(newRequest);
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
     * clearHeaders
     */
    public static void clearHeaders() {
        nest.clearHeaders();
    }

    /**
     * addGlobalHeader
     */
    public static void addGlobalHeader(String key, String value) {
        nest.addHeader(key, value);
    }

    /**
     * addGlobalHeaders
     *
     * @param maps HashMap
     */
    public static void addGlobalHeaders(HashMap<String, String> maps) {
        nest.addHeaders(maps);
    }

    /**
     * @param observable Interface method
     * @param observer   Listen method
     * @param <T>        void
     * @Deprecated Encapsulation method provided by default T
     */
    @Deprecated
    public static <T> void fly(Observable<T> observable, Dover<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new HttpResponseFunc<T>())
                .subscribe(observer);
    }

    /**
     * Encapsulation method provided by default T
     * Before do workInit(Activity activity)
     *
     * @param observable Interface method
     * @param observer   Listen method
     * @param <T>        void
     */
    public static <T> void flyLife(Observable<T> observable, Dover<T> observer) {

        if (mInstance.activity == null) {
            return;
        }

        // get T class type info
        Type type = ((ParameterizedType) Objects.requireNonNull(observer.getClass().getGenericSuperclass())).getActualTypeArguments()[0];

        observable
                .compose(rxCache.transformObservable(type.toString(), type, CacheStrategy.firstRemote()))
                .map(new CacheResult.MapFunc<>())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                // HttpResponseFunc（）为拦截onError事件的拦截器
                .onErrorResumeNext(new HttpResponseFunc<>())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mInstance.activity)))
                .subscribe(observer);
    }

    /**
     * Encapsulation method provided by default T
     * Before do workInit(Activity activity)
     *
     * @param observable Interface method
     * @param observer   Listen method
     * @param <T>        void
     */
    public static <T> void flyLifeOnlyNet(Observable<T> observable, Dover<T> observer) {

        if (mInstance.activity == null) {
            return;
        }

        observable
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                // HttpResponseFunc（）为拦截onError事件的拦截器
                .onErrorResumeNext(new HttpResponseFunc<>())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mInstance.activity)))
                .subscribe(observer);
    }

    /**
     * Encapsulation method provided by default T
     * Before do workInit(Activity activity)
     *
     * @param observable Interface method
     * @param observer   Listen method
     * @param <T>        void
     */
    public static void flyLifeDownload(Observable<ResponseBody> observable, String path, DownloadListener downloadListener) {

        if (mInstance.activity == null) {
            return;
        }

        observable
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                // HttpResponseFunc（）为拦截onError事件的拦截器
                .onErrorResumeNext(new HttpResponseFunc<>())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mInstance.activity)))
                .subscribe(new Dover<ResponseBody>() {

                    @Override
                    public void don(Disposable d, @NonNull ResponseBody responseBody) {
                        writeResponseToDisk(path, responseBody, downloadListener);
                    }

                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        downloadListener.onFail("网络错误");
                    }
                });
    }

    /**
     * Encapsulation method provided by default T
     *
     * @param activity   Activity context
     * @param observable Interface method
     * @param observer   Listen method
     * @param <T>        void
     */
    public static <T> void flyLife(Activity activity, Observable<T> observable, Dover<T> observer) {

        // get T class type info
        Type type = ((ParameterizedType) Objects.requireNonNull(observer.getClass().getGenericSuperclass())).getActualTypeArguments()[0];

        observable
                .compose(rxCache.transformObservable(type.toString(), type, CacheStrategy.firstRemote()))
                .map(new CacheResult.MapFunc<>())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                // HttpResponseFunc（）为拦截onError事件的拦截器
                .onErrorResumeNext(new HttpResponseFunc<>())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
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

    private static void writeResponseToDisk(String path, ResponseBody response, DownloadListener downloadListener) {
        //从response获取输入流以及总大小
        writeFileFromIS(new File(path), response.byteStream(), response.contentLength(), downloadListener);
    }

    private static int sBufferSize = 8192;

    //将输入流写入文件
    private static void writeFileFromIS(File file, InputStream is, long totalLength, DownloadListener downloadListener) {

        //开始下载
        downloadListener.onStart();

        //创建文件
        if (!file.exists()) {
            if (!Objects.requireNonNull(file.getParentFile()).exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
                Logger.d("保存文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFail("createNewFile IOException");
                Logger.d("保存文件创建失败");
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("IOException");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface DownloadListener {

        /**
         * 下载开始
         */
        void onStart();

        /**
         * 下载进度
         *
         * @param progress
         */
        void onProgress(int progress);

        /**
         * 下载完成
         *
         * @param path
         */
        void onFinish(String path);

        /**
         * 下载失败
         *
         * @param errorInfo
         */
        void onFail(String errorInfo);
    }

    public interface ProgressUploadListener {

        /**
         * 上传开始
         */
        void onStart();

        /**
         * @param bytesWriting 已经写的字节数
         * @param totalBytes   文件的总字节数
         */
        void onProgress(long bytesWriting, long totalBytes);

        /**
         * 上传完成
         *
         * @param path
         */
        void onFinish();

        /**
         * 上传失败
         *
         * @param errorInfo
         */
        void onFail();
    }
}
