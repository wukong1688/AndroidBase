package com.jack.androidbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jack.androidbase.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1, bt2;
        bt1 = (Button) findViewById(R.id.btn1);
        bt2 = (Button) findViewById(R.id.btn2);

        /*三种绑定方式,
        // 参考：https://www.cnblogs.com/diigu/p/3599183.html
        //1)第1种 */
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UIMainActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //2)第2种
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntentMainActivity.class);
                startActivityForResult(intent, 2);
            }
        };
        bt2.setOnClickListener(listener);


    }

    //3)第3种
    public void btn3Click(View v) {
        Intent intent = new Intent(MainActivity.this, ServicesMainActivity.class);
        startActivityForResult(intent, 3);
    }

    public void btn4Click(View v) {
        Intent intent = new Intent(MainActivity.this, MediaMainActivity.class);
        startActivityForResult(intent, 4);
    }

    public void btn5Click(View v) {
        Intent intent = new Intent(MainActivity.this, MainOtherActivity.class);
        startActivityForResult(intent, 5);
    }
}
