package com.jack.androidbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jack.androidbase.R;

public class UINotiDetailActivity extends AppCompatActivity {
    TextView tv_title, tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_noti_detail);

        bindView();
    }

    protected void bindView() {
        tv_title = (TextView) findViewById(R.id.ui_noti_title);
        tv_content = (TextView) findViewById(R.id.ui_noti_content);

        tv_title.setText("这是通知正文标题");
        tv_title.setTextSize(26);

        tv_content.setText("这是通知正文内容,这是通知正文内容,这是通知正文内容,这是通知正文内容");
        tv_content.setTextSize(20);
    }
}
