package com.example.wl_softpos_sdk.common.base;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.wl_softpos_sdk.common.utils.Utils;
import com.google.android.material.textfield.TextInputLayout;


/**
 * Created by Ganesh on 3/5/2018.
 */

public class BindingAdapters {

    //TODO Add a private constructor to hide the implicit public one.

    private static final String TAG = BaseActivity.class.getSimpleName();

    private BindingAdapters() {
        throw new IllegalStateException("BindingAdapters class");
    }

    @BindingAdapter("isVisible")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imsResourceId")
    public static void imageIcon(ImageView imageView, int res) {
        imageView.setImageResource(res);
    }


    @BindingAdapter("errorText")
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        if (TextUtils.isEmpty(errorMessage)) {
            view.setErrorEnabled(false);
        } else {
            view.setErrorEnabled(true);
            try {
                EditText editText = view.getEditText();
                editText.requestFocus();

            } catch (Exception ex) {
                Utils.getInstance().log(TAG, ex.getMessage());
            }

        }
        view.setError(errorMessage);
    }





}

