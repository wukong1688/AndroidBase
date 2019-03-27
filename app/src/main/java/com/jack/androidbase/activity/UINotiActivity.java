package com.jack.androidbase.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jack.androidbase.R;

public class UINotiActivity extends AppCompatActivity {
    private Context mContext;
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_noti);

        mContext = UINotiActivity.this;
        //创建大图标的Bitmap
        LargeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    //"Notification显示"
    protected void showNotification(View v) {
        Intent it = new Intent(mContext, UINotiDetailActivity.class);
        PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);

        //设置图片,通知标题,发送时间,提示方式等属性
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle("今日头条")                        //标题
                .setContentText("马云坚持每天思考6小时,快使用双节棍，哼哼哈兮。马云坚持每天思考6小时...")      //内容
//                .setSubText("")                    //内容下面的一小段文字
                .setTicker("收到通知发送过来的信息~")             //收到信息后状态栏显示的文字信息
                .setWhen(System.currentTimeMillis())           //设置通知时间
                .setSmallIcon(R.mipmap.ic_launcher)            //设置小图标
                .setLargeIcon(LargeBitmap)                     //设置大图标
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
//                .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao))  //设置自定义的提示音
                .setAutoCancel(true)                           //设置点击后取消Notification
                .setContentIntent(pit);                        //设置PendingIntent
        notify1 = mBuilder.build();
        mNManager.notify(NOTIFYID_1, notify1);
    }

    protected void deleteNotification(View v) {
//        mNManager.cancelAll();
        mNManager.cancel(NOTIFYID_1);
    }
}
