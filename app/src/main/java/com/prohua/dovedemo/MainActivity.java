package com.prohua.dovedemo;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prohua.dove.Dove;
import com.prohua.dovedemo.app.Core;

import okhttp3.MultipartBody;

public class MainActivity extends AppCompatActivity {

    private TextView testData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        testData = findViewById(R.id.testData);

        Dove.workInit(this);

        // 放信到鸽子里，呼唤它飞到收信者那里
//        Dove.flyLifeOnlyNet(Core.jobTask().getTestDataPost(null),
//                new Dover<NetBase<TestBean>>() {
//
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void don(Disposable d, @NonNull NetBase<TestBean> testBeanNetBase) {
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

//
//        Dove.flyLifeDownload(
//                Core.jobTask()
//                        .downFile("http://192.168.0.112:8080/tcpservice_war/file/Smart-1.0.0.apk"),
//                Environment.getExternalStorageDirectory() + "/Smart-1.0.0.apk",
//                new Dove.DownloadListener() {
//
//                    @Override
//                    public void onStart() {
//                        Log.i("Dove", "开始下载");
//                    }
//
//                    @Override
//                    public void onProgress(int progress) {
//                        Log.i("Dove", "下载中:" + progress);
//                    }
//
//                    @Override
//                    public void onFinish(String path) {
//                        Log.i("Dove", "下载完成:" + path);
//                    }
//
//                    @Override
//                    public void onFail(String errorInfo) {
//                        Log.i("Dove", "下载失败:" + errorInfo);
//                    }
//                });

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
