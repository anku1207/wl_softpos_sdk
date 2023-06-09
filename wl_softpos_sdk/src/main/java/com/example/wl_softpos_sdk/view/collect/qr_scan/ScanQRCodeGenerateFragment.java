package com.example.wl_softpos_sdk.view.collect.qr_scan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseFragment;
import com.example.wl_softpos_sdk.common.base.BaseNavigator;
import com.example.wl_softpos_sdk.databinding.FragmentScanQrCodeGenerateBinding;


public class ScanQRCodeGenerateFragment extends BaseFragment<FragmentScanQrCodeGenerateBinding, ScanQrCodeViewModel> {
    private static final String TAG = ScanQRCodeGenerateFragment.class.getSimpleName();


    public static ScanQRCodeGenerateFragment getInstance() {
        return new ScanQRCodeGenerateFragment();
    }


    @Override
    protected boolean isActivityViewModel() {
        return true;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_scan_qr_code_generate;
    }

    @Override
    public void onBinding() {

        mBinding.setViewModel(mViewModel);
        mBinding.setModel(mViewModel.getCollectModel());

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
}