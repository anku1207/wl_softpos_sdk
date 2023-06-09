package com.example.wl_softpos_sdk.view.collect.menu;

import com.example.wl_softpos_sdk.common.base.BaseViewModel;
import com.example.wl_softpos_sdk.common.model.CollectModel;

public class CollectMenuViewmodel extends BaseViewModel<CollectMenuNavigator> {
    boolean isSettleCredit;

    public CollectModel model;

    public void onCollectCash() {
        mNavigator.onCollectCash();
    }

    public void onQRCodeScan() {
        mNavigator.onQRCodeScan(isSettleCredit);
    }

    public void onSendLink() {
        mNavigator.onSendLink(isSettleCredit);
    }

    public void onTapToCard() {
            mNavigator.onTapToCard(isSettleCredit);
    }

    void init(CollectModel model) {
        this.model = model;
    }
    public CollectModel getCollectModel(){
        return model;
    }

}
