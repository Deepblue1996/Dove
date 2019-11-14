package com.prohua.dovedemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.prohua.dove.Dove;
import com.prohua.dove.Dover;
import com.prohua.dovedemo.app.Core;
import com.prohua.dovedemo.bean.NetBase;
import com.prohua.dovedemo.bean.TestBean;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private TextView testData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        testData = findViewById(R.id.testData);

        Dove.workInit(this);

        // 放信到鸽子里，呼唤它飞到收信者那里
        Dove.flyLifeOnlyNet(Core.jobTask().getTestDataPost(null),
                new Dover<NetBase<TestBean>>() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void don(Disposable d, @NonNull NetBase<TestBean> testBeanNetBase) {
                        // 接收/收到
                        Gson gson = new Gson();
                        String txt = gson.toJson(testBeanNetBase);
                        testData.setText(testData.getText() + "\n" + txt);
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        // 失败/中断
                        testData.setText(throwable.getMessage() + "\n" + "中断");
                    }

                });

//
//        // key名称
//        List<String> keyList = new ArrayList<>();
//        // 路径名称
//        List<String> pathList = new ArrayList<>();
//
//        // 返回封装
//        MultipartBody.Part[] filePartList = Dove.filesPart(keyList, pathList);
//
//        // 上传提交
//        Dove.flyLife(Core.jobTask().uploadImageAvator(filePartList),
//                new Dover<NetBase<ImgBean>>() {
//
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void don(Disposable d, @NonNull NetBase<ImgBean> testBeanNetBase) {
//                        // 接收/收到
//                        Gson gson = new Gson();
//                        String txt = gson.toJson(testBeanNetBase);
//                        testData.setText(testData.getText() + "\n" + txt);
//                    }
//
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void die(Disposable d, @NonNull Throwable throwable) {
//                        // 失败/中断
//                        testData.setText(throwable.getMessage() + "\n" + "中断");
//                    }
//
//                });

    }
}
