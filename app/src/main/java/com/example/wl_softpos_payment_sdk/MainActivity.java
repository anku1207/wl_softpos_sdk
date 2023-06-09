package com.example.wl_softpos_payment_sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.wl_softpos_sdk.common.configuration.WLServiceManager;
import com.example.wl_softpos_sdk.common.configuration.network.NetworkSettings;
import com.example.wl_softpos_sdk.common.model.CollectModel;
import com.example.wl_softpos_sdk.view.collect.menu.CollectMenuActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    CollectModel collectModel;

    EditText totalAmt;
    EditText edt_mid, edt_tid, edt_bank_code, edt_aggregator_code, edt_aggregator_key, edt_merchant_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkSettings networkSettings = new NetworkSettings().getInstance();
        networkSettings.setUrl("test.html");
        WLServiceManager.init(networkSettings);


        edt_mid = findViewById(R.id.edt_mid);
        edt_tid = findViewById(R.id.edt_tid);
        edt_bank_code = findViewById(R.id.edt_bank_code);
        edt_aggregator_code = findViewById(R.id.edt_aggregator_code);
        edt_aggregator_key = findViewById(R.id.edt_aggregator_key);
        edt_merchant_key = findViewById(R.id.edt_merchant_key);

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectModel.setTotalAmount(totalAmt.getText().toString());
                collectModel.setMid(edt_mid.getText().toString());
                collectModel.setTid(edt_tid.getText().toString());
                collectModel.setBankCode(edt_bank_code.getText().toString());
                collectModel.setAggregatorCode(edt_aggregator_code.getText().toString());
                collectModel.setAggregatorKey(edt_aggregator_key.getText().toString());
                collectModel.setMerchantKey(edt_merchant_key.getText().toString());

                Intent intent = WLServiceManager.get(MainActivity.this).startTransaction(MainActivity.this, collectModel);
                startActivityForResult(intent, 200);

            }
        });

        totalAmt = findViewById(R.id.edt_amt);
        collectModel = new CollectModel();

    }

}