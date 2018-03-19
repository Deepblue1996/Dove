package com.prohua.dovedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.prohua.dove.Dove;
import com.prohua.dove.Dover;
import com.prohua.dovedemo.app.Core;
import com.prohua.dovedemo.bean.IndexAllDataBean;
import com.prohua.dovedemo.bean.NetBase;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 放一只鸽子，给它发信任务，打听收信者
        Dove.fly(Core.box().getTest(),
                new Dover<NetBase<IndexAllDataBean>>() {
                    @Override
                    public void call(@NonNull Disposable d) {
                        // 开始/通知
                    }

                    @Override
                    public void don(Disposable d, @NonNull NetBase<IndexAllDataBean> indexAllDataBeanNetBase) {
                        // 接收/收到
                    }

                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        // 失败/中断
                    }

                    @Override
                    public void end(Disposable d) {
                        // 结束/完成
                    }
                });
    }
}
