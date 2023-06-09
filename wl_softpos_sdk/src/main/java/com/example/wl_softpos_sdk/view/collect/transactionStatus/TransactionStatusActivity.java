package com.example.wl_softpos_sdk.view.collect.transactionStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.databinding.ActivityTransactionStatusBinding;

public class TransactionStatusActivity extends BaseActivity<ActivityTransactionStatusBinding, TransactionStatusViewModel> implements TransactionStatusNavigator {

    @Override
    public int getLayoutID() {
        return R.layout.activity_transaction_status;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, true, getString(R.string.toolbar_title_transaction_Status), false);
        getIntentData();

    }

    @Override
    public Class getViewModel() {
        return TransactionStatusViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return this;
    }

    @Override
    public String getScreenName() {
        return null;
    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onNoInternetConnection() {

    }
    private void getIntentData() {
        if (getIntent().hasExtra("data")) {
           // mViewModel.init((CollectModel) getIntent().getSerializableExtra("data"),getResources());
        }
    }
}