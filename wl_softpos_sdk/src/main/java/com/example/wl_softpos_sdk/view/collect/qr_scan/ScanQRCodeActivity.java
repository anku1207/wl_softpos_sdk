package com.example.wl_softpos_sdk.view.collect.qr_scan;

import android.widget.TextView;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.databinding.ActivityScanQrcodeBinding;

public class ScanQRCodeActivity extends BaseActivity<ActivityScanQrcodeBinding, ScanQrCodeViewModel> implements ScanQrCodeNavigator {

    private static final String TAG = ScanQRCodeActivity.class.getSimpleName();

    @Override
    public int getLayoutID() {
        return R.layout.activity_scan_qrcode;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, true, getString(R.string.toolbar_title_scan_qr), false);
        getIntentData();

        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mViewModel.getCollectModel());
        replaceFragmentWithAnimation(ScanQRCodeGenerateFragment.getInstance(), false, R.id.qr_Code_container);
    }

    private void getIntentData() {
        if (getIntent().hasExtra("data")) {
            mViewModel.init((CollectModel) getIntent().getSerializableExtra("data"),getResources());
        }
    }

    @Override
    public Class getViewModel() {
        return ScanQrCodeViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return null;
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
}