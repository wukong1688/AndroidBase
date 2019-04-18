package com.jack.androidbase.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.tools.DatabaseUtil;
import com.jack.androidbase.tools.OkHttpUtils.CallBackUtil;
import com.jack.androidbase.tools.OkHttpUtils.OkhttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainOtherActivity extends AppCompatActivity {
    private WebView webView;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_other);
    }

    //Webview打开网页
    protected void btnOpenWeb(View v) {
        webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            @SuppressLint("NewApi")
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webView.loadUrl("http://www.runoob.com/");          //调用loadUrl方法为WebView加入链接
        setContentView(webView);                           //调用Activity提供的setContentView将webView显示出来

        getSupportActionBar().hide();
    }

    /**
     * 短信操作
     *
     * @param v
     */
    protected void btnOptSms(View v) {
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();

        switch (v.getId()) {
            case R.id.other_btn_read_sms:
                Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
                while (cursor.moveToNext()) {
                    String address = cursor.getString(0);
                    String date = cursor.getString(1);
                    String type = cursor.getString(2);
                    String body = cursor.getString(3);
                    Log.i("HeHe", "地址:" + address);
                    Log.i("HeHe", "时间:" + date);
                    Log.i("HeHe", "类型:" + type);
                    Log.i("HeHe", "内容:" + body);
                    Log.i("HeHe", "======================");
                }
                cursor.close();
                break;
            case R.id.other_btn_insert_sms:
                //插入短信失败 todo
                ContentValues conValues = new ContentValues();
                conValues.put("address", "123456789");
                conValues.put("type", 1);
                conValues.put("date", System.currentTimeMillis());
                conValues.put("body", "no zuo no die why you try!");
                resolver.insert(uri, conValues);
                Log.i("HeHe", "短信插入完毕~");

                break;
        }
    }

    /**
     * 操作联系人
     *
     * @param v
     */
    protected void btnOptContact(View v) {
        ContentResolver resolver = getContentResolver();
        switch (v.getId()) {
            case R.id.other_btn_read_contact:

                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor cursor = resolver.query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    //获取联系人姓名,手机号码
                    String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.i("HeHe", "姓名:" + cName);
                    Log.i("HeHe", "号码:" + cNum);
                    Log.i("HeHe", "======================");
                }
                cursor.close();
                break;
            case R.id.other_btn_insert_contact:
                //使用事务添加联系人
                Uri uri2 = Uri.parse("content://com.android.contacts/raw_contacts");
                Uri dataUri = Uri.parse("content://com.android.contacts/data");

                ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
                ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri2)
                        .withValue("account_name", null)
                        .build();
                operations.add(op1);

                //依次是姓名，号码，邮编
                ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                        .withValueBackReference("raw_contact_id", 0)
                        .withValue("mimetype", "vnd.android.cursor.item/name")
                        .withValue("data2", "Coder-pig")
                        .build();
                operations.add(op2);

                ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                        .withValueBackReference("raw_contact_id", 0)
                        .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                        .withValue("data1", "13798988888")
                        .withValue("data2", "2")
                        .build();
                operations.add(op3);

                ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                        .withValueBackReference("raw_contact_id", 0)
                        .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                        .withValue("data1", "779878443@qq.com")
                        .withValue("data2", "2")
                        .build();
                operations.add(op4);
                //将上述内容添加到手机联系人中~
                try {
                    resolver.applyBatch("com.android.contacts", operations);
                } catch (Exception e) {

                }

                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected void btnOptProvider(View v) {
        final ContentResolver resolver = this.getContentResolver();
        switch (v.getId()) {
            case R.id.other_btn_custom_provider_insert:
                ContentValues values = new ContentValues();
                values.put("name", "测试");
                Uri uri = Uri.parse("content://com.jack.androidbase.providers.myprovider/test");
                resolver.insert(uri, values);
                Toast.makeText(getApplicationContext(), "数据插入成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected void btnOptOkhttpCus(View v) {
        final String TAG = "OKHTTP";
        String url = "http://www.baidu.com";
        switch (v.getId()) {
            case R.id.other_btn_okhttp_get_cus:
                OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "aaa");
                        Log.d(TAG, response);
                        Toast.makeText(getApplicationContext(), "get Success", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.other_btn_okhttp_post_cus:
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("title", "title");
                OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "aaa");
                        Log.d(TAG, response);
                        Toast.makeText(getApplicationContext(), "post Success", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    protected void btnOptSqlite(View v) {
        DatabaseUtil dbUtil = new DatabaseUtil(this);
        dbUtil.open();

        switch (v.getId()) {
            case R.id.other_btn_sqlite_list: //查询
                Cursor cursor = dbUtil.fetchAll();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Log.i("Student", "ID:" + cursor.getInt(0) + ",Student Name: " + cursor.getString(1) +
                                ",Grade: " + cursor.getString(2));
                    }
                }
                break;
            case R.id.other_btn_sqlite_add:
                dbUtil.insert("Prashant Thakkar", "100");
                Log.i("Student", "add over");
                break;
            case R.id.other_btn_sqlite_update:
                dbUtil.update(1, "aa", "bb");
                Log.i("Student", "update over");
                break;
            case R.id.other_btn_sqlite_del:
                dbUtil.delete(2);
                Log.i("Student", "delete over");
                break;
            case R.id.other_btn_sqlite_del_all:
                dbUtil.deleteAll();
                Log.i("Student", "delete all over");
                break;
        }
        dbUtil.close();
    }

    @Override
    public void onBackPressed() {
        //我们需要重写回退按钮的时间,当用户点击回退按钮：
        //1.webView.canGoBack()判断网页是否能后退,可以则goback()
        //2.如果不可以连续点击两次退出App,否则弹出提示Toast
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }

        }
    }
}
