package com.example.wl_softpos_sdk.view.collect.cash;


import android.media.MediaPlayer;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.databinding.ActivityCollectCashBinding;

public class CollectCashActivity extends BaseActivity<ActivityCollectCashBinding, CollectCashViewmodel> implements CollectCashNavigator {

    private static final String TAG = CollectCashActivity.class.getSimpleName();

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onNoInternetConnection() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_collect_cash;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, true, getString(R.string.toolbar_title_collect_cash), false);
        getIntentData();

        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mViewModel.getCollectModel());

    }

    private void getIntentData() {
        if (getIntent().hasExtra("data")) {
            mViewModel.init((CollectModel) getIntent().getSerializableExtra("data"),getResources());
        }
    }

    @Override
    public Class getViewModel() {
        return CollectCashViewmodel.class;
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
    public void onCashCollect() {
        //showCashCollectedtDialog();
    }

    public void showCashCollectedtDialog() {
        MediaPlayer mp = MediaPlayer.create(CollectCashActivity.this, R.raw.receipt); // sound is inside res/raw/mysound
        mp.start();

//        TransactionBottomSheetDialog bottomSheetDialog = TransactionBottomSheetDialog.getInstance(id,false,mViewModel.isFromLogin ? LoginActivity.class : HomeActivity.class,true);
//        bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());

    }
}