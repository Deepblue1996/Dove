package com.prohua.dove;

import android.content.Context;

/**
 * Created by Deep on 2018/3/19 0019.
 */

public class Nest {

    private String url;
    private Context context;
    private Class interfaceClass;

    private static Nest nest;

    private Nest() {

    }

    /**
     * 出生 全局初始化
     */
    public static Nest build() {
        if (null == nest) {
            synchronized (Dove.class) {
                if (null == nest) {
                    nest = new Nest();
                }
            }
        }
        return nest;
    }

    public Nest setUrl(String url) {
        this.url = url;
        return nest;
    }

    public Nest setContext(Context context) {
        this.context = context;
        return nest;
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

    public Nest setInterfaceClass(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
        return nest;
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }
}
