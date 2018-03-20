package com.prohua.dove;

/**
 * 鸟巢
 * Created by Deep on 2018/3/19 0019.
 */

public class Nest {

    private String baseUrl;
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

    public Nest setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return nest;
    }

    Class getInterfaceClass() {
        return interfaceClass;
    }

    public Nest setInterfaceClass(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
        return nest;
    }

    String getBaseUrl() {
        return baseUrl;
    }

}
