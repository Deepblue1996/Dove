package com.prohua.dove.base;

import com.prohua.dove.Dove;

/**
 * 鸟巢 Nest
 * Created by Deep on 2018/3/19 0019.
 */

public class Nest {

    // BaseUrl Application Server Url.
    private String baseUrl;
    // InterfaceClass Your own custom interface.
    private Class interfaceClass;
    // cacheSize
    private long cacheSize = DefaultConfig.SIZE_OF_CACHE;
    private long disconnectTime = DefaultConfig.TIMEOUT_DISCONNECT;
    private long connectTime = DefaultConfig.TIMEOUT_CONNECT;

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

    public Nest newBuild() {
        return build();
    }

    public Nest setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return nest;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

    public Nest setInterfaceClass(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
        return nest;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public Nest setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
        return nest;
    }

    public long getConnectTime() {
        return connectTime;
    }

    public Nest setConnectTime(long connectTime) {
        this.connectTime = connectTime;
        return nest;
    }

    public long getDisconnectTime() {
        return disconnectTime;
    }

    public Nest setDisconnectTime(long disconnectTime) {
        this.disconnectTime = disconnectTime;
        return nest;
    }
}