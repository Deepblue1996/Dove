package com.prohua.dovedemo.app;

import android.app.Application;

import com.prohua.dove.Dove;
import com.prohua.dove.Nest;
import com.prohua.dovedemo.net.ComDef;
import com.prohua.dovedemo.net.JobTask;

/**
 * 全局
 * Created by Deep on 2018/3/14 0014.
 */

public class Core extends Application {

    private static Core instance;

    // 任务
    private static JobTask jobTask;

    public static JobTask jobTask() {
        return jobTask;
    }

    public static Core instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 先做一个鸟巢，用来养鸽子，让鸽子给你送信
        jobTask = Dove.birth(Core.instance().getApplicationContext(),
                Nest.build().setBaseUrl(ComDef.BASE_COM_URL).setInterfaceClass(JobTask.class));
    }

}
