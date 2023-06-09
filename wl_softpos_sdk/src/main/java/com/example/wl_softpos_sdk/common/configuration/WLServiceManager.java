package com.example.wl_softpos_sdk.common.configuration;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.wl_softpos_sdk.common.configuration.network.NetworkSettings;
import com.example.wl_softpos_sdk.common.model.CollectModel;

import org.json.JSONObject;

public abstract class WLServiceManager implements WLConfingInterface{
    private static WLServiceManager wlServiceManager;
    public WLServiceManager() {
    }

    public static void init(NetworkSettings param0)  {
        // $FF: Couldn't be decompiled
    }
    public static WLServiceManager get(Context context) {
        if (wlServiceManager == null) {
            wlServiceManager = new WLSDKConfing(context);
        }
        return wlServiceManager;
    }

    public abstract Intent startTransaction(Context context , CollectModel collectModel);
}
