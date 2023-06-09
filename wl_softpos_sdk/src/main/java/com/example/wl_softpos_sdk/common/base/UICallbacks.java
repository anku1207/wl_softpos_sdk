package com.example.wl_softpos_sdk.common.base;

public interface UICallbacks {
    int getLayoutID();

    void onBinding();

    Class getViewModel();

    BaseNavigator getNavigatorReference();

    String getScreenName();

}
