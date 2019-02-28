package com.prohua.dove.base;

import com.prohua.dove.Dove;

import java.util.HashMap;

/**
 * 鸟巢 Nest
 * Created by Deep on 2018/3/19 0019.
 */

public class Nest {

    // BaseUrl Application Server Url.
    private String baseUrl;
    // InterfaceClass Your own custom interface.
    private Class interfaceClass;
    // Global parameters
    private HashMap<String, String> params;
    // Global headers
    private HashMap<String, String> headers;

    // cacheSize
    private long disconnectTime = DefaultConfig.TIMEOUT_DISCONNECT;
    private long connectTime = DefaultConfig.TIMEOUT_CONNECT;

    // Only One.
    private static Nest nest;

    private Nest() {
        params = new HashMap<>();
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

    public Nest addGlobalParam(String key, String value) {
        params.put(key, value);
        return nest;
    }

    public Nest addGlobalParams(HashMap<String, String> maps) {
        params.putAll(maps);
        return nest;
    }

    public HashMap<String, String> getGlobalParams() {
        return params;
    }

    public void clearParams() {
        params.clear();
    }

    public Nest addHeader(String key, String value) {
        headers.put(key, value);
        return nest;
    }

    public Nest addHeaders(HashMap<String, String> maps) {
        headers.putAll(maps);
        return nest;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void clearHeaders() {
        headers.clear();
    }
}
