package com.example.wl_softpos_sdk.view.collect.menu;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.databinding.ActivityCollectMenuBinding;
import com.example.wl_softpos_sdk.view.collect.cash.CollectCashActivity;
import com.example.wl_softpos_sdk.view.collect.qr_scan.ScanQRCodeActivity;
import com.example.wl_softpos_sdk.view.collect.send_link.SendPaymentLinkActivity;
import com.example.wl_softpos_sdk.view.collect.taptocard.TapToCardActivity;


public class CollectMenuActivity extends BaseActivity<ActivityCollectMenuBinding, CollectMenuViewmodel> implements CollectMenuNavigator {

    private static final String TAG = CollectMenuActivity.class.getSimpleName();

    @Override
    public int getLayoutID() {
        return R.layout.activity_collect_menu;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar,true,getString(R.string.toolbar_title_collect_amount),false);
        getIntentData();

        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mViewModel.getCollectModel());

    }

    private void getIntentData() {
        if (getIntent().hasExtra("data")) {
            boolean validData=true;
            CollectModel collectModel = (CollectModel) getIntent().getSerializableExtra("data");
            if(TextUtils.isEmpty(collectModel.getMid())){
                validData=false;
            }else if(TextUtils.isEmpty(collectModel.getTid())){
                validData=false;
            }else if(TextUtils.isEmpty(collectModel.getBankCode())){
                validData=false;
            }else if(TextUtils.isEmpty(collectModel.getAggregatorCode())){
                validData=false;
            }else if(TextUtils.isEmpty(collectModel.getAggregatorKey())){
                validData=false;
            }else if(TextUtils.isEmpty(collectModel.getMerchantKey())){
                validData=false;
            }else if(TextUtils.isEmpty(collectModel.getTotalAmount())){
                validData=false;
            }

            if(!validData){
                Toast.makeText(mContext, "Required data is missing!", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                mViewModel.init (collectModel);
            }
        }
    }

    @Override
    public Class getViewModel() {
        return CollectMenuViewmodel.class;
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
    public void onError(String errorMessage) {

    }

    @Override
    public void onNoInternetConnection() {

    }

    @Override
    public void onCollectCash() {
        Intent intent = new Intent(mContext, CollectCashActivity.class);
        intent.putExtra("data", mViewModel.getCollectModel());
        startActivity(intent);
    }

    @Override
    public void onQRCodeScan(boolean isSettle) {
        Intent intent = new Intent(mContext, ScanQRCodeActivity.class);
        intent.putExtra("data", mViewModel.getCollectModel());
        startActivity(intent);
    }

    @Override
    public void onSendLink(boolean isSettle) {
        Intent intent = new Intent(mContext, SendPaymentLinkActivity.class);
        intent.putExtra("data", mViewModel.getCollectModel());
        startActivity(intent);
    }

    @Override
    public void onTapToCard(boolean issettle) {
        Intent intent = new Intent(mContext, TapToCardActivity.class);
        intent.putExtra("data", mViewModel.getCollectModel());
        startActivity(intent);
    }
}