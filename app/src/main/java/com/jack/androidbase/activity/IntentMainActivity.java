package com.jack.androidbase.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jack.androidbase.R;

public class IntentMainActivity extends AppCompatActivity {
    private final static int REQUESTCODE = 999;
    private static final String ACTIVITY_TAG = "LogDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        bindView();
    }

    /*
    Intent的基本使用
    http://www.runoob.com/w3cnote/android-tutorial-intent-base.html
    */
    private void bindView() {
        Button btn_back = (Button) findViewById(R.id.btn_back2);
        btn_back.setOnClickListener(btn_back_listener);

        Button intent_open_browser = (Button) findViewById(R.id.intent_open_browser);
        intent_open_browser.setOnClickListener(open_browser_listener);

        Button intent_transfer_data = (Button) findViewById(R.id.intent_transfer_data);
        intent_transfer_data.setOnClickListener(transfer_data_listener);

        Button intent_show_map = (Button) findViewById(R.id.intent_show_map);
        intent_show_map.setOnClickListener(show_map_listener);

        Button intent_wireless_setting = (Button) findViewById(R.id.intent_wireless_setting);
        intent_wireless_setting.setOnClickListener(wireless_setting_listener);


    }

    View.OnClickListener btn_back_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        }
    };

    //打开浏览器,然后打开网页
    View.OnClickListener open_browser_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent();
            it.setAction(Intent.ACTION_VIEW);
            it.setData(Uri.parse("http://www.baidu.com"));
            startActivity(it);
        }
    };

    View.OnClickListener transfer_data_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(IntentMainActivity.this, IntentTransferDataActivity.class);
            it.putExtra("name", "china");//传递数据到下一个activity
            startActivityForResult(it, REQUESTCODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE) {
                Log.d(IntentMainActivity.ACTIVITY_TAG, requestCode + ":" + resultCode);

                String ret = data.getStringExtra("ret");//获取返回值
                String show_str = "返回值: " + ret;
                Log.d(IntentMainActivity.ACTIVITY_TAG, show_str);
            }
        }

    }

    //打开map
    View.OnClickListener show_map_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("geo:39.9,116.3");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    };

    //打开无线wifi设置
    View.OnClickListener wireless_setting_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
            startActivityForResult(intent, 100);
        }
    };
}
