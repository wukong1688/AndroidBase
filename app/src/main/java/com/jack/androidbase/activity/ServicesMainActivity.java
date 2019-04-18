package com.jack.androidbase.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.tools.ServiceDemo2;

public class ServicesMainActivity extends AppCompatActivity {
    private Button btn_back, srv_start_srv_1, srv_stop_srv_1;
    private Button srv_start_srv_2, srv_stop_srv_2, srv_status_srv_2;
    private Intent intent, intent2;

    ServiceDemo2.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("------Service Connected-------");
            binder = (ServiceDemo2.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("------Service DisConnected-------");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_main);

        initIntent();
        bindView();
    }

    private void initIntent() {
        intent = new Intent();
        intent.setAction("com.jack.androidbase.tools.SERVICE_DEMO");

        intent2 = new Intent();
        intent2.setAction("com.jack.androidbase.tools.SERVICE_DEMO2");
    }

    private void bindView() {
        btn_back = (Button) findViewById(R.id.btn_back);
        srv_start_srv_1 = (Button) findViewById(R.id.srv_start_srv_1);
        srv_stop_srv_1 = (Button) findViewById(R.id.srv_stop_srv_1);

        srv_start_srv_2 = (Button) findViewById(R.id.srv_start_srv_2);
        srv_stop_srv_2 = (Button) findViewById(R.id.srv_stop_srv_2);
        srv_status_srv_2 = (Button) findViewById(R.id.srv_status_srv_2);

        btn_back.setOnClickListener(bindListener1);
        srv_start_srv_1.setOnClickListener(bindListener1);
        srv_stop_srv_1.setOnClickListener(bindListener1);

        srv_start_srv_2.setOnClickListener(bindListener2);
        srv_stop_srv_2.setOnClickListener(bindListener2);
        srv_status_srv_2.setOnClickListener(bindListener2);
    }

    View.OnClickListener bindListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_back:
                    setResult(RESULT_CANCELED, new Intent());
                    finish();
                    break;

                //StartService方式
                case R.id.srv_start_srv_1:
                    startService(intent);
                    break;
                case R.id.srv_stop_srv_1:
                    stopService(intent);
                    break;
            }
        }
    };

    View.OnClickListener bindListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //BindService方式
                case R.id.srv_start_srv_2:
                    bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                    break;
                case R.id.srv_stop_srv_2:
                    unbindService(conn);
                    break;
                case R.id.srv_status_srv_2:
                    Toast.makeText(getApplicationContext(), "Service的count的值为:"
                            + binder.getCount(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}
