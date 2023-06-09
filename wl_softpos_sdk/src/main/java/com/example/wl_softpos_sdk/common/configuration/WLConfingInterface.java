package com.example.wl_softpos_sdk.common.configuration;

import android.content.Context;
import android.content.Intent;

import com.example.wl_softpos_sdk.common.model.CollectModel;

import org.json.JSONObject;

public interface WLConfingInterface {
    void setNetworkSetting();
    Intent startTransaction(Context context , CollectModel collectModel);
}
