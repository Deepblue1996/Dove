package com.prohua.dovedemo.net;

import com.prohua.dovedemo.bean.IndexAllDataBean;
import com.prohua.dovedemo.bean.NetBase;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 接口
 * Created by Deep on 2018/3/14 0014.
 */

public interface JobService {

    @GET("appgoods/index_all_list")
    Observable<NetBase<IndexAllDataBean>> getTest();
}
