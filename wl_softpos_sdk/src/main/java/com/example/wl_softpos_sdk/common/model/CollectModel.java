package com.example.wl_softpos_sdk.common.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.wl_softpos_sdk.BR;

import java.io.Serializable;

import kotlin.Suppress;

public class CollectModel extends BaseObservable implements Serializable {

    private String mid;
    private String tid;
    private String bankCode;
    private String aggregatorCode;
    private String aggregatorKey;
    private String merchantKey;

    private String totalAmount;
    private String customerName;
    private String notes;
    private String emailId;
    private String mobileNo;


    @Bindable
    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        notifyPropertyChanged(BR.totalAmount);
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAggregatorCode() {
        return aggregatorCode;
    }

    public void setAggregatorCode(String aggregatorCode) {
        this.aggregatorCode = aggregatorCode;
    }

    public String getAggregatorKey() {
        return aggregatorKey;
    }

    public void setAggregatorKey(String aggregatorKey) {
        this.aggregatorKey = aggregatorKey;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Bindable
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
        notifyPropertyChanged(BR.notes);
    }

    @Bindable
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
        notifyPropertyChanged(BR.emailId);
    }

    @Bindable
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        notifyPropertyChanged(BR.mobileNo);
    }
}
