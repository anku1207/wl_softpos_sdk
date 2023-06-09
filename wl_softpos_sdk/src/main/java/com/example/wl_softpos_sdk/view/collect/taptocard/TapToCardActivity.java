package com.example.wl_softpos_sdk.view.collect.taptocard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.common.utils.Utils;
import com.example.wl_softpos_sdk.databinding.ActivityTapToCardBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class TapToCardActivity extends BaseActivity<ActivityTapToCardBinding, TapToCardViewModel> implements TapToCardNavigator {

    private BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior;

    @Override
    public int getLayoutID() {
        return R.layout.activity_tap_to_card;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, true, getString(R.string.toolbar_title_tap_to_card), false);
        getIntentData();

        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mViewModel.getCollectModel());
        mBinding.setErrorModel(mViewModel.getCollectionErrorModel());
        setupBottomSheetListener();
    }

    private void getIntentData() {
        if (getIntent().hasExtra("data")) {
            mViewModel.init((CollectModel) getIntent().getSerializableExtra("data"),getResources());
        }
    }

    @Override
    public Class getViewModel() {
        return TapToCardViewModel.class;
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

    private void setupBottomSheetListener() {
        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomsheetTapCard.bottomSheet);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(0, true);
        bottomSheetBehavior.setFitToContents(true);
    }

    @Override
    public void checkPermission() {
        mViewModel.requestCardPayment();
//        if (Utils.getInstance().isNFSEnable(mContext)) {
//
//            mViewModel.requestCardPayment();
//
//        }

    }
}