package com.example.wl_softpos_sdk.common.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


/**
 * Created by Ganesh on 7/7/17.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements UICallbacks {

    protected Context mContext;
    protected T mBinding;
    protected V mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        if (isActivityViewModel()) {
            mViewModel = (V) new ViewModelProvider(getBaseActivity()).get(getViewModel());
        } else {
            mViewModel = (V) new ViewModelProvider(this).get(getViewModel());
            mViewModel.setNavigator(getNavigatorReference());
        }
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        if (!isActivityViewModel()) {
            //createDialog();
        }
        onBinding();
    }


    protected abstract boolean isActivityViewModel();

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected void replaceFragmentWithAnimation(Fragment fragment, boolean isAddToStack, int container) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container, fragment);
        if (isAddToStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

}
