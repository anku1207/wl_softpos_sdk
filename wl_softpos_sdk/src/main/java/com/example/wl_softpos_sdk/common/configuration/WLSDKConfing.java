package com.example.wl_softpos_sdk.common.configuration;

import android.content.Context;
import android.content.Intent;

import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.view.collect.menu.CollectMenuActivity;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

public class WLSDKConfing extends WLServiceManager{
    Context context;


    public WLSDKConfing(Context context) {
        this.context = context;
    }

    @Override
    public void setNetworkSetting() {

    }

    @Override
    public Intent startTransaction(Context context, CollectModel collectModel) {
        Gson gson = new Gson();
        Intent intent;
        intent = new Intent(context, CollectMenuActivity.class);
        intent.putExtra("data", collectModel);
        return intent;
    }
}
