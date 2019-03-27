package com.jack.androidbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jack.androidbase.R;

public class UIMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_main);

        bindView();
    }

    private void bindView() {
        Button btn_back = (Button) findViewById(R.id.btn_back1);
        btn_back.setOnClickListener(btn_back_listener);
    }

    /*从Intent中返回上一个Activity
    * https://www.cnblogs.com/silentteen/p/6046617.html
    */
    View.OnClickListener btn_back_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent); //这里RESULT_CANCELED或RESULT_OK都是可以的，只要接收端能对上就行
            finish();
        }
    };

    //"Toast显示"
    protected void showToast(View v) {
        Toast.makeText(getApplicationContext(), "Toast显示", Toast.LENGTH_LONG).show();
    }

    //Notification 跳转
    protected void jumpNotification(View v) {
        startActivity(new Intent(this, UINotiActivity.class));
    }

    //alertDialog 跳转
    protected void alertDialog(View v) {
        startActivity(new Intent(this, UIDialogActivity.class));
    }

    //进度条和时间日期选择器
    protected void progressDialog(View v) {
        startActivity(new Intent(this, UIProgressActivity.class));
    }

    //网格布局
    protected void gridLayout(View v) {
        startActivity(new Intent(this, UIGridLayoutActivity.class));
    }

    //表格布局
    protected void tableLayout(View v) {
        startActivity(new Intent(this, UITableLayoutActivity.class));
    }

    //登陆界面
    protected void loginPage(View v) {
        startActivity(new Intent(this, UIUserLoginActivity.class));
    }

    //注册界面
    protected void regPage(View v) {
        startActivity(new Intent(this, UIUserRegActivity.class));
    }

}
