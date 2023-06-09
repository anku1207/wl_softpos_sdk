package com.example.wl_softpos_sdk.common.repository;


import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.wl_softpos_sdk.common.base.App;
import com.example.wl_softpos_sdk.common.repository.network.ApiClient;
import com.example.wl_softpos_sdk.common.repository.network.ApiEncpInterface;
import com.example.wl_softpos_sdk.common.repository.network.ApiInterface;
import com.example.wl_softpos_sdk.common.repository.network.RemoteDataProvider;
import com.example.wl_softpos_sdk.common.utils.Environment;
import com.example.wl_softpos_sdk.common.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class DataRepository implements RemoteDataProvider {

    private static final String TAG = DataRepository.class.getSimpleName();


    private static DataRepository mINSTANCE;
    private ApiInterface mServices;
    private ApiEncpInterface mEncrpServices;
    private final Utils utils;



    private DataRepository() {
        if (App.environment.equals(Environment.ENVIRONMENT_ENCRYPTION)) {
            mEncrpServices = ApiClient.getEncClient();
        } else {
            mServices = ApiClient.getClient();
        }
        utils = Utils.getInstance();
    }

    public static DataRepository getInstance() {
        if (mINSTANCE == null) {
            synchronized (DataRepository.class) {
                mINSTANCE = new DataRepository();
            }
        }
        return mINSTANCE;
    }


}



