package com.prohua.dovedemo.net;

import com.prohua.dovedemo.bean.TestBean;
import com.prohua.dovedemo.bean.NetBase;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 接口
 * Created by Deep on 2018/3/14 0014.
 */

public interface JobTask {

    @GET("appgoods/index_all_list")
    Observable<NetBase<TestBean>> getTestDataGet();

    @FormUrlEncoded
    @POST("appgoods/index_all_list")
    Observable<NetBase<TestBean>> getTestDataPost(@Field("hasNull") String hasNull);
}
