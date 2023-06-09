package com.example.wl_softpos_sdk.common.widget;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatDialog;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.databinding.DialogCustomBinding;

public class CustomDialog extends AppCompatDialog {

    private final Context mContext;
    private final DialogCustomBinding dialogCustomBinding;
    private CustomDialogListener customDialogListener;

    public CustomDialog(Context context) {
        super(context, R.style.Theme_Dialog);
        mContext = context;
        dialogCustomBinding = DialogCustomBinding.inflate(LayoutInflater.from(mContext));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogCustomBinding.getRoot());
        getWindow().setLayout(MATCH_PARENT, WRAP_CONTENT);
        setCancelable(false);

        dialogCustomBinding.txtNo.setOnClickListener(view -> {
            dismiss();
            if (customDialogListener != null) {
                customDialogListener.onNegativeButtonClick();
            }
        });
        dialogCustomBinding.txtYes.setOnClickListener(v -> {
            dismiss();
            if (customDialogListener != null) {
                customDialogListener.onPositiveButtonClick();
            }

        });

    }

    public void setNegativeButton(boolean isVisible) {
        if (isVisible) {
            dialogCustomBinding.txtNo.setVisibility(View.VISIBLE);
        } else {
            dialogCustomBinding.txtNo.setVisibility(View.GONE);
        }
    }

    public void setNegativeButtonText(String buttonText) {
        if(TextUtils.isEmpty(buttonText)){
            dialogCustomBinding.txtNo.setVisibility(View.GONE);
        }else {
            dialogCustomBinding.txtNo.setText(buttonText);
            dialogCustomBinding.txtNo.setVisibility(View.VISIBLE);
        }

    }

    public void setPositiveButtonText(String buttonText) {
        dialogCustomBinding.txtYes.setText(buttonText);
    }


    public void setTitle(String dialogTitle) {
        if (!TextUtils.isEmpty(dialogTitle))
            dialogCustomBinding.title.setText(dialogTitle);
        else
            dialogCustomBinding.title.setVisibility(View.GONE);
    }


    public void setMessage(String message) {
        if (!TextUtils.isEmpty(message))
            dialogCustomBinding.message.setText(message);
        else
            dialogCustomBinding.message.setVisibility(View.GONE);
    }

    public void setCustomDialogListener(CustomDialogListener customDialogListener) {
        this.customDialogListener = customDialogListener;
    }

    public interface CustomDialogListener {
        void onPositiveButtonClick();

        void onNegativeButtonClick();
    }
}
