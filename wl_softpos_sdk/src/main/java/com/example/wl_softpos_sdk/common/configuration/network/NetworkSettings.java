package com.example.wl_softpos_sdk.common.configuration.network;

public class NetworkSettings {
    private String url;
    static NetworkSettings networkSettings;

    public static  NetworkSettings getInstance() {
        if (networkSettings == null) {
            synchronized (NetworkSettings.class) {
                networkSettings = new NetworkSettings();
            }
        }
        return networkSettings;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
