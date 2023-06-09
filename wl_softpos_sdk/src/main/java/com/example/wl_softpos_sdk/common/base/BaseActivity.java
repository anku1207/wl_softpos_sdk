package com.example.wl_softpos_sdk.common.base;


import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.utils.Constants;
import com.example.wl_softpos_sdk.common.widget.CustomDialog;
import com.example.wl_softpos_sdk.common.widget.LoadingDialog;
import com.example.wl_softpos_sdk.databinding.ToolbarBinding;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements UICallbacks {
    protected T mBinding;
    protected V mViewModel;
    protected Context mContext;
    private CustomDialog customDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);

        overridePendingTransitionEnter();
        mBinding = DataBindingUtil.setContentView(BaseActivity.this, getLayoutID());
        mBinding.setLifecycleOwner(this);
        mViewModel = (V) new ViewModelProvider(BaseActivity.this).get(getViewModel());
        mViewModel.setNavigator(getNavigatorReference());

        onBinding();
        createDialog();

        mViewModel.toastMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.anim_slide_in_from_right_enter, R.anim.anim_slide_in_from_right_exit);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.anim_slide_in_from_left_enter, R.anim.anim_slide_in_from_left_exit);
    }


    private void createDialog() {
        LoadingDialog loadingDialog = new LoadingDialog(BaseActivity.this);
        loadingDialog.setCancelable(false);

        mViewModel.dialogMessage.observe(BaseActivity.this, msg -> {
            if (loadingDialog != null) {
                loadingDialog.setDialogMessage(String.valueOf(msg));
            }
        });

        mViewModel.dialogVisibility.observe(this, showDialog -> {

            if ((Boolean) showDialog) {
                if (loadingDialog != null)
                    loadingDialog.showDialog();
            } else {
                if (loadingDialog != null && loadingDialog.isShowing())
                    loadingDialog.dismissDialog();
            }
        });
    }

    protected void replaceFragmentWithAnimation(Fragment fragment, boolean isAddToStack, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment, fragment.getClass().getSimpleName());
        if (isAddToStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    protected void setToolbar(ToolbarBinding toolbarBinding, boolean showBackArrow, String title, boolean isCustom) {
        setSupportActionBar(toolbarBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(showBackArrow);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow));
        setTitle(title);

        if (isCustom) {
            toolbarBinding.customToolbarLayout.setVisibility(View.VISIBLE);
        } else {
            toolbarBinding.customToolbarLayout.setVisibility(GONE);
        }
    }

    protected void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showConfirmationDialog(String dialogTitle, String message, String positiveButtontext, String negativeButtonText, String listener) {

        customDialog.setTitle(dialogTitle);
        customDialog.setMessage(message);
        customDialog.setNegativeButton(true);
        customDialog.setNegativeButtonText(negativeButtonText);
        customDialog.setPositiveButtonText(positiveButtontext);
        customDialog.show();
    }

    public void showConfirmationDialogSingleBtn(String dialogTitle, String message, String positiveButtontext, CustomDialog.CustomDialogListener listener) {

        customDialog.setTitle(dialogTitle);
        customDialog.setMessage(message);
        customDialog.setNegativeButton(false);
        customDialog.setPositiveButtonText(positiveButtontext);
        customDialog.setCustomDialogListener(listener);
        customDialog.show();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
        overridePendingTransitionExit();
    }


    public void showMessageDialog(String dialogTitle, String message, int requestCode) {
        customDialog.setTitle(dialogTitle);
        customDialog.setMessage(message);
        customDialog.setNegativeButton(false);
        customDialog.setNegativeButtonText("");
        customDialog.setPositiveButtonText(getString(R.string.btn_ok_custom_dialog));

        if (requestCode == Constants.AlertDialogRequestCode.NO_INTERNET) {
            customDialog.setPositiveButtonText(getString(R.string.btn_positive_no_internet));
        }
        customDialog.setCustomDialogListener(new CustomDialog.CustomDialogListener() {
            @Override
            public void onPositiveButtonClick() {
                finish();
            }

            @Override
            public void onNegativeButtonClick() {
                finish();
            }
        });
        customDialog.show();
    }

}
