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

    //用户系统-登陆
    protected void loginPage(View v) {
        startActivity(new Intent(this, UIUserLoginActivity.class));
    }

    //九图
    protected void nineDraw(View v) {
        startActivity(new Intent(this, UINineDrawActivity.class));
    }

    //基础控件
    protected void basicWidget(View v) {
        startActivity(new Intent(this, UIBasicWidgetActivity.class));
    }

    //基础listView
    protected void baseListView(View v) {
        startActivity(new Intent(this, UIBaseListViewActivity.class));
    }

    //基础gridView-封装adapter
    protected void baseListViewCustom(View v) {
        startActivity(new Intent(this, UIBaseListViewCustomActivity.class));
    }

    //基础gridView-recyclerView1
    protected void recyclerView1(View v) {
        startActivity(new Intent(this, UIRecyclerView1Activity.class));
    }

    //基础gridView-recyclerView2
    protected void recyclerView2(View v) {
        startActivity(new Intent(this, UIRecyclerView2Activity.class));
    }

    //基础gridView
    protected void baseGridView(View v) {
        startActivity(new Intent(this, UIBaseGridViewActivity.class));
    }

    //基础Spinner-弹窗
    protected void baseSpinner(View v) {
        startActivity(new Intent(this, UISpinnerActivity.class));
    }

    //Fragment
    protected void fragCase1(View v) {
        startActivity(new Intent(this, UIFragment1Activity.class));
    }

    //Fragment+ViewPaper实现底部四个菜单
    protected void fragCase2(View v) {
        startActivity(new Intent(this, UIFragment2Activity.class));
    }

    //其他-EditText输入监听
    protected void editTextWatcher(View v) {
        startActivity(new Intent(this, UIEditTextWatchActivity.class));
    }
}
