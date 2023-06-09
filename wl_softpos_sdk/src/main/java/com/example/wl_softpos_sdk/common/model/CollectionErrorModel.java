package com.example.wl_softpos_sdk.common.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class CollectionErrorModel extends BaseObservable {

    private String mobileNoErrorMsg;
    private String emailIdErrorMsg;
    private String customerNameErrorMsg;

    @Bindable
    public String getCustomerNameErrorMsg() {
        return customerNameErrorMsg;
    }

    public void setCustomerNameErrorMsg(String customerNameErrorMsg) {
        this.customerNameErrorMsg = customerNameErrorMsg;
        notifyPropertyChanged(com.example.wl_softpos_sdk.BR.customerNameErrorMsg);
    }

    @Bindable
    public String getMobileNoErrorMsg() {
        return mobileNoErrorMsg;
    }

    public void setMobileNoErrorMsg(String mobileNoErrorMsg) {
        this.mobileNoErrorMsg = mobileNoErrorMsg;
        notifyPropertyChanged(com.example.wl_softpos_sdk.BR.mobileNoErrorMsg);

    }

    @Bindable
    public String getEmailIdErrorMsg() {
        return emailIdErrorMsg;
    }

    public void setEmailIdErrorMsg(String emailIdErrorMsg) {
        this.emailIdErrorMsg = emailIdErrorMsg;
        notifyPropertyChanged(com.example.wl_softpos_sdk.BR.emailIdErrorMsg);
    }

    public void clearAllMsg(){
        setMobileNoErrorMsg("");
        setCustomerNameErrorMsg("");
        setEmailIdErrorMsg("");

    }
}
