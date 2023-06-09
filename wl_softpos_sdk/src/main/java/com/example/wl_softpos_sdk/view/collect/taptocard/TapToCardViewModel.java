package com.example.wl_softpos_sdk.view.collect.taptocard;

import android.content.res.Resources;
import android.text.TextUtils;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.App;
import com.example.wl_softpos_sdk.common.base.BaseViewModel;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.common.model.CollectionErrorModel;
import com.example.wl_softpos_sdk.common.utils.Utils;
import com.google.gson.Gson;

public class TapToCardViewModel extends BaseViewModel<TapToCardNavigator> {

    private CollectionErrorModel collectionErrorModel;

    private CollectModel collectModel;

    void init(CollectModel collectModel, Resources resources) {
        setResource(resources);
        this.collectModel = collectModel;
        collectionErrorModel = new CollectionErrorModel();

    }
    public CollectModel getCollectModel(){
        return collectModel;
    }
    public CollectionErrorModel getCollectionErrorModel() {
        return collectionErrorModel;
    }

    public void requestCardPayment() {

        toastMessage.setValue(new Gson().toJson(collectModel));
    }

    public enum TaptoPhoneState {
        WAITING_FOR_CARD, PROCESSING, SUCCESS, RECEIVING
    }

    public void onProceed(CollectModel collectModel) {

        if (collectionErrorModel == null) {
            collectionErrorModel = new CollectionErrorModel();
        }
        collectionErrorModel.clearAllMsg();
        if (isValid(collectModel)) {
            mNavigator.checkPermission();
//            if(SpConnect.isSmartPesaInstalled(App.getInstance().getApplicationContext()) ){
//                mNavigator.checkLastTransactionDetails();
//                mNavigator.checkPermission();
//            }else {
//                mNavigator.showInstallAPP();
//            }
        }
    }

    private boolean isValid(CollectModel collectModel) {
        boolean flag = true;
        if (!TextUtils.isEmpty(collectModel.getMobileNo()) && !Utils.getInstance().isMobileNumberValid(collectModel.getMobileNo())) {
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
