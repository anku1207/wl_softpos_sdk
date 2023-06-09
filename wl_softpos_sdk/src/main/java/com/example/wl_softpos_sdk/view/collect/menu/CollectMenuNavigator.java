package com.example.wl_softpos_sdk.view.collect.menu;

import com.example.wl_softpos_sdk.common.base.BaseNavigator;

public interface CollectMenuNavigator extends BaseNavigator {
    void onCollectCash();
    void onQRCodeScan(boolean isSettle);
    void onSendLink(boolean isSettle);
    void onTapToCard(boolean issettle);
}
