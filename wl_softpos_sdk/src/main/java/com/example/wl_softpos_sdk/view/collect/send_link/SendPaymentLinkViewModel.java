package com.example.wl_softpos_sdk.view.collect.send_link;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseViewModel;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.common.model.CollectionErrorModel;
import com.example.wl_softpos_sdk.common.utils.Utils;
import com.google.gson.Gson;

public class SendPaymentLinkViewModel extends BaseViewModel<SendPaymentLinkNavigator> {

    CollectModel model;
    private CollectionErrorModel collectionErrorModel;
    public Boolean isFromLogin;
    private boolean isSettle = false;
    private String tempMobileNo;

    void init(CollectModel model, Resources resources) {
        setResource(resources);
        this.model = model;
        collectionErrorModel = new CollectionErrorModel();
    }

    public CollectionErrorModel getCollectionErrorModel() {
        return collectionErrorModel;
    }

    public CollectModel getCollectModel() {
        return model;
    }

    public void onProceed(CollectModel collectModel) {
        if (collectionErrorModel == null) {
            collectionErrorModel = new CollectionErrorModel();
        }
        collectionErrorModel.clearAllMsg();
        if (isValid(collectModel)) {
            toastMessage.setValue(new Gson().toJson(collectModel));
            mNavigator.onLinkSend();
            Log.e("Click_btn", "1st");
        }
    }

    private boolean isValid(CollectModel collectModel) {

        boolean flag = true;

        if (TextUtils.isEmpty(collectModel.getMobileNo())) {
            collectionErrorModel.setMobileNoErrorMsg(getResources().getString(R.string.mobile_number_blank_error));
            flag = false;
        } else if (!Utils.getInstance().isMobileNumberValid(collectModel.getMobileNo())) {
            collectionErrorModel.setMobileNoErrorMsg(getResources().getString(R.string.mobile_number_invalid_error));
            flag = false;
        }

        if (!TextUtils.isEmpty(collectModel.getEmailId()) && !Utils.getInstance().isValidEmail(collectModel.getEmailId())) {
            collectionErrorModel.setEmailIdErrorMsg(getResources().getString(R.string.email_invalid_error));
            flag = false;
        }

        return flag;
    }
}
