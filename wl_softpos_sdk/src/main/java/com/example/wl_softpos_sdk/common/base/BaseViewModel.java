package com.example.wl_softpos_sdk.common.base;


import android.content.res.Resources;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N extends BaseNavigator> extends ViewModel {
    protected N mNavigator;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected Resources resources;
    protected MutableLiveData<Boolean> dialogVisibility = new MutableLiveData<>();
    protected MutableLiveData<String> dialogMessage = new MutableLiveData<>();
    public MutableLiveData<String> toastMessage = new MutableLiveData<>();


    public void setResource(Resources res){
        resources = res;
    }

    public Resources getResources() {
        return resources;
    }


    public void setNavigator(N mNavigator) {
        this.mNavigator = mNavigator;
    }






}
