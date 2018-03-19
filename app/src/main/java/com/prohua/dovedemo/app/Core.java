package com.prohua.dovedemo.app;

import android.app.Application;

import com.prohua.dove.Dove;
import com.prohua.dove.Nest;
import com.prohua.dovedemo.net.ComDef;
import com.prohua.dovedemo.net.JobService;

/**
 * 全局
 * Created by Deep on 2018/3/14 0014.
 */

public class Core extends Application {

    private static Core instance;

    // 任务
    private static JobService box;

    public static JobService box() {
        return box;
    }

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 做一个鸟巢，用来养鸽子，鸽子出生了，它是用来给你干指定的事
        box = (JobService) Dove.birth(
                Nest.build()
                        .setContext(Core.getInstance().getApplicationContext())
                        .setUrl(ComDef.COM_DEF)
                        .setInterfaceClass(JobService.class));
    }

}
