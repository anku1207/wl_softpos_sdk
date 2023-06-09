package com.example.wl_softpos_sdk.view.collect.send_link;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.common.utils.Constants;
import com.example.wl_softpos_sdk.databinding.ActivitySendPaymentLinkBinding;
import com.example.wl_softpos_sdk.view.collect.menu.CollectMenuActivity;
import com.example.wl_softpos_sdk.view.collect.transactionStatus.TransactionStatusActivity;

public class SendPaymentLinkActivity extends BaseActivity<ActivitySendPaymentLinkBinding, SendPaymentLinkViewModel> implements SendPaymentLinkNavigator {

    private static final String TAG = SendPaymentLinkActivity.class.getSimpleName();

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onNoInternetConnection() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_send_payment_link;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, true, getString(R.string.toolbar_title_payment_link), false);
        getIntentData();

        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mViewModel.getCollectModel());
        mBinding.setErrorModel(mViewModel.getCollectionErrorModel());
    }

    private void getIntentData() {
        if (getIntent().hasExtra("data")) {
            mViewModel.init((CollectModel) getIntent().getSerializableExtra("data"),getResources());
        }
    }

    @Override
    public Class getViewModel() {
        return SendPaymentLinkViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return this;
    }

    @Override
    public String getScreenName() {
        return TAG;
    }

    @Override
    public void onLinkSend() {
        mBinding.btnProceed.setEnabled(false);
        mBinding.btnProceed.setTextColor(getResources().getColor(R.color.bg_btn_text_non_selected, getTheme()));
        mBinding.btnProceed.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_disable, getTheme()));

        Intent transactionStatusIntent = new Intent(mContext,TransactionStatusActivity.class);
        startActivity(transactionStatusIntent);
        Log.e("send","activity");

    }

}