package com.prohua.dovedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.prohua.dove.Dove;
import com.prohua.dove.Dover;
import com.prohua.dovedemo.app.Core;
import com.prohua.dovedemo.bean.ImgBean;
import com.prohua.dovedemo.bean.TestBean;
import com.prohua.dovedemo.bean.NetBase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

public class MainActivity extends AppCompatActivity {

    private TextView testData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        testData = findViewById(R.id.testData);

        // 放信到鸽子里，呼唤它飞到收信者那里
        Dove.flyLife(this, Core.jobTask().getTestDataPost(null),
                new Dover<NetBase<TestBean>>() {

                    @Override
                    public void don(Disposable d, @NonNull NetBase<TestBean> testBeanNetBase) {
                        // 接收/收到
                        Gson gson = new Gson();
                        String txt = gson.toJson(testBeanNetBase);
                        testData.setText(testData.getText() + "\n" + txt);
                    }

                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        // 失败/中断
                        testData.setText(throwable.getMessage() + "\n" + "中断");
                    }

                });

        // key名称
        List<String> keyList = new ArrayList<>();
        // 路径名称
        List<String> pathList = new ArrayList<>();

        // 返回封装
        MultipartBody.Part[] filePartList = Dove.filesPart(keyList, pathList);

        // 上传提交
        Dove.flyLife(this, Core.jobTask().uploadImageAvator(filePartList),
                new Dover<NetBase<ImgBean>>() {

                    @Override
                    public void don(Disposable d, @NonNull NetBase<ImgBean> testBeanNetBase) {
                        // 接收/收到
                        Gson gson = new Gson();
                        String txt = gson.toJson(testBeanNetBase);
                        testData.setText(testData.getText() + "\n" + txt);
                    }

                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        // 失败/中断
                        testData.setText(throwable.getMessage() + "\n" + "中断");
                    }

                });

    }
}
