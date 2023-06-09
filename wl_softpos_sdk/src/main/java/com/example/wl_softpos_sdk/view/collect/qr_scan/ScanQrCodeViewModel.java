package com.example.wl_softpos_sdk.view.collect.qr_scan;

import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import androidx.core.util.Consumer;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseViewModel;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.common.repository.DataRepository;
import com.google.gson.Gson;

public class ScanQrCodeViewModel extends BaseViewModel<ScanQrCodeNavigator> {
    CollectModel model;

    void init(CollectModel model, Resources resources) {
        setResource(resources);
        this.model = model;
    }

    public CollectModel getCollectModel() {
        return model;
    }

    public void onQrCodeGenerate(CollectModel collectModel) {
        genereateQrCode(collectModel);
    }

    public void genereateQrCode(CollectModel collectModel) {
       // dialogMessage.setValue(getResources().getString(R.string.please_wait));
       // dialogVisibility.setValue(true);
        toastMessage.setValue(new Gson().toJson(collectModel));

    }
}
