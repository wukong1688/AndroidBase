package com.jack.androidbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jack.androidbase.R;

public class ServicesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_main);

        bindView();
    }

    private void bindView() {
        Button btn_back = (Button) findViewById(R.id.btn_back3);
        btn_back.setOnClickListener(btn_back_listener);
    }

    View.OnClickListener btn_back_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        }
    };
}
