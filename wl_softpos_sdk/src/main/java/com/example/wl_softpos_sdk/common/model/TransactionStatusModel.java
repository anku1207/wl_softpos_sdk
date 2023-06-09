package com.example.wl_softpos_sdk.common.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.wl_softpos_sdk.BR;

public class TransactionStatusModel extends BaseObservable {


    private String mobileNumber;
    private String transactionID;
    private String transactionStatus;
    private String customerName;
    private String amount = "--";
    private String email;


    @Bindable
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(BR.mobileNumber);
    }

    @Bindable
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
        notifyPropertyChanged(BR.transactionID);

    }

    @Bindable
    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
        notifyPropertyChanged(BR.transactionStatus);

    }

    @Bindable
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
        notifyPropertyChanged(BR.customerName);

    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}
