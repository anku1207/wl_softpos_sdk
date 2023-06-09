package com.example.wl_softpos_sdk.common.utils;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.wl_softpos_sdk.R;
import com.example.wl_softpos_sdk.common.base.BaseActivity;
import com.example.wl_softpos_sdk.common.widget.CustomDialog;

import java.util.regex.Pattern;

public class Utils {

    private static final String TAG = Context.class.getSimpleName();
    private static Utils instance;

    private Utils() {

    }

    public static Utils getInstance() {
        if (instance == null) {
            synchronized (Utils.class) {
                instance = new Utils();
            }
        }
        return instance;
    }


    public boolean isValidEmail(String email) {


        String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean isMobileNumberValid(String phone) {
        boolean check = false;
        if (!TextUtils.isEmpty(phone)) {
            if (Pattern.matches("[6789][0-9]{9}", phone)) {
                check = phone.length() == 10;
            } else {
                check = false;
            }
        }
        return check;
    }

    public void log(String TAG, String message) {
        Log.d(TAG, message);
    }

    public void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public boolean isNFSEnable(Context mContext){

        NfcManager manager = (NfcManager) mContext.getSystemService(Context.NFC_SERVICE);
        if (manager != null) {
            NfcAdapter adapter = manager.getDefaultAdapter();

            if (adapter == null) {
                if(mContext instanceof BaseActivity) {
                    ( (BaseActivity)mContext).showMessageDialog(mContext.getString(R.string.nfc_not_supported_title), mContext.getString(R.string.nfc_not_supported_message), Constants.AlertDialogRequestCode.NFC_NOT_SUPPORTED);
                }else {
                    showMessage(mContext,mContext.getString(R.string.nfc_not_supported_message));
                }
                return false;
            }

            if (!adapter.isEnabled()) {
                if(mContext instanceof  BaseActivity) {
                    ((BaseActivity)mContext).showConfirmationDialog(
                            mContext.getString(R.string.nfc_not_enabled_title),
                            mContext.getString(R.string.nfc_not_enabled_message),
                            mContext.getString(R.string.enable_nfc),
                            mContext.getResources().getString(R.string.cancel_small),
                            String.valueOf(new CustomDialog.CustomDialogListener() {
                                @Override
                                public void onPositiveButtonClick() {
                                    mContext.startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                                }

                                @Override
                                public void onNegativeButtonClick() {

                                }
                            }));
                }else {
                    showMessage(mContext,mContext.getString(R.string.nfc_not_enabled_message));
                }

                return false;
            }
        }else {
            showMessage(mContext,mContext.getString(R.string.nfc_not_supported_message));
            return false;
        }
        return true;
    }
}
