package com.prohua.dovedemo.net;

import com.prohua.dovedemo.bean.ImgBean;
import com.prohua.dovedemo.bean.NetBase;
import com.prohua.dovedemo.bean.TestBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 接口
 * Created by Deep on 2018/3/14 0014.
 */

public interface JobTask {

    /**
     * GET 请求
     *
     * @return
     */
    @GET("appgoods/index_all_list")
    Observable<NetBase<TestBean>> getTestDataGet();

    /**
     * POST 请求
     *
     * @param hasNull
     * @return
     */
    @FormUrlEncoded
    @POST("appgoods/index_all_list")
    Observable<NetBase<TestBean>> getTestDataPost(@Field("hasNull") String hasNull);

    /**
     * POST 方式上传
     *
     * @param files
     * @return
     */
    @Multipart
    @POST("appuser/upload_img")
    Observable<NetBase<ImgBean>> uploadImageAvator(@Part MultipartBody.Part[] files);

    @Streaming
    @GET
    Call<ResponseBody> downFile(@Url String url);

    /**
     * 上传
     *
     * @return
     */
    @Multipart
    @POST("tcpservice_war/uploadUserBackgroundImage")
    Observable<ResponseBody> uploadUserBackgroundImage(@Header("token") String token, @Part MultipartBody.Part file);
}
