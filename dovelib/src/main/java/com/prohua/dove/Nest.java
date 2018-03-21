package com.prohua.dove;

/**
 * 鸟巢 Nest
 * Created by Deep on 2018/3/19 0019.
 */

public class Nest {

    // BaseUrl Application Server Url.
    private String baseUrl;
    // InterfaceClass Your own custom interface.
    private Class interfaceClass;

    // Only One.
    private static Nest nest;

    private Nest() {
    }

    /**
     * Build a nest Global singleton static.
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
