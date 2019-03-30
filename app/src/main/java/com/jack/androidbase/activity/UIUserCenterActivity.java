package com.jack.androidbase.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jack.androidbase.R;

public class UIUserCenterActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences login_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_user_center);

        TextView user_show = (TextView)findViewById(R.id.user_show);
        login_sp = getSharedPreferences("userInfo", 0);
        String user_name = login_sp.getString("USER_NAME", "");
        user_show.setText(user_name + ",欢迎回来");

        Button btn_logout = (Button)findViewById(R.id.logout);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                SharedPreferences.Editor editor = login_sp.edit();
                editor.remove("USER_NAME");
                editor.remove("PASSWORD");
                editor.remove("mRememberCheck");
                editor.commit();

                Intent intent = new Intent(UIUserCenterActivity.this, UIUserLoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
