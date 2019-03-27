package com.jack.androidbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.androidbase.R;

public class IntentTransferDataActivity extends AppCompatActivity {
    private EditText data_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_tranfer_data);

        bindView();
    }

    private void bindView() {
        Button intent_ret_last = (Button) findViewById(R.id.intent_ret_last);
        intent_ret_last.setOnClickListener(ret_last_listener);

        //提取传递过来的值
        Intent intent = getIntent();
        TextView intent_ret_request_value = (TextView) findViewById(R.id.intent_ret_request_value);
        String request_val = intent.getStringExtra("name");
        intent_ret_request_value.setText("name:" + request_val);
        data_edit = (EditText)findViewById(R.id.data_edit);

    }

    View.OnClickListener ret_last_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            String to_ret = data_edit.getText().toString();
            intent.putExtra("ret", to_ret);
//            setResult(RESULT_CANCELED, intent);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
