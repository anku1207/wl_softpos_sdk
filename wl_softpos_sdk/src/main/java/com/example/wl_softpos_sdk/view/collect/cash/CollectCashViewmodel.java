package com.example.wl_softpos_sdk.view.collect.cash;

import android.content.res.Resources;

import com.example.wl_softpos_sdk.common.base.BaseViewModel;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.google.gson.Gson;

public class CollectCashViewmodel extends BaseViewModel<CollectCashNavigator> {

    CollectModel collectModel;

    void init(CollectModel collectModel, Resources resources) {
        setResource(resources);
        this.collectModel = collectModel;
    }
    public CollectModel getCollectModel(){
        return collectModel;
    }

    public void onProceed(CollectModel collectModel) {

        collectCashTransaction();
    }

    public void collectCashTransaction() {
        mNavigator.onCashCollect();
        toastMessage.setValue(new Gson().toJson(collectModel));

    }
}
