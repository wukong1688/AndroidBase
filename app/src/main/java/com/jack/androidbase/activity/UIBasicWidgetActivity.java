package com.jack.androidbase.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jack.androidbase.R;
import com.jack.androidbase.tools.RoundImageView;

public class UIBasicWidgetActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    TextView ui_base_text_view6, ui_base_text_view7;
    RoundImageView ui_base_img_5;
    CheckBox ui_base_ck_1, ui_base_ck_2, ui_base_ck_3;
    private ToggleButton tbtn_open;
    private Switch swh_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_basic_widget);

        initTextView();
        initImageView();
        initRadioCheckbox();

        tbtn_open = (ToggleButton) findViewById(R.id.tbtn_open);
        swh_status = (Switch) findViewById(R.id.swh_status);
        tbtn_open.setOnCheckedChangeListener(this);
        swh_status.setOnCheckedChangeListener(this);
    }

    private void initTextView() {
        ui_base_text_view6 = (TextView) findViewById(R.id.ui_base_text_view6);
        ui_base_text_view7 = (TextView) findViewById(R.id.ui_base_text_view7);

        //测试html标签的支持
        String s1 = "支持HTML标签：<br>";
        s1 += "<font color='blue'><b>百度一下，你就知道~：</b></font><br>";
        s1 += "<a href = 'http://www.baidu.com'>百度</a>";
//        s1 += "<img src = 'http://www.runoob.com/wp-content/uploads/2015/07/62308635.jpg>"; //img src赞不支持，需其他插件
        ui_base_text_view6.setText(Html.fromHtml(s1));
        ui_base_text_view6.setMovementMethod(LinkMovementMethod.getInstance());

        //测试微信里的好友点赞和点击效果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("好友" + i + "，");
        }

        String likeUsers = sb.substring(0, sb.lastIndexOf("，")).toString();
        ui_base_text_view7.setMovementMethod(LinkMovementMethod.getInstance());
        ui_base_text_view7.setText(addClickPart(likeUsers), TextView.BufferType.SPANNABLE);
    }

    //定义一个点击每个部分文字的处理方法
    private SpannableStringBuilder addClickPart(String str) {
        //赞的图标，这里没有素材，就找个笑脸代替下~
        ImageSpan imgspan = new ImageSpan(UIBasicWidgetActivity.this, R.drawable.fav);
        SpannableString spanStr = new SpannableString("p.");
        spanStr.setSpan(imgspan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //创建一个SpannableStringBuilder对象，连接多个字符串
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(str);
        String[] likeUsers = str.split("，");
        if (likeUsers.length > 0) {
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                final int start = str.indexOf(name) + spanStr.length();
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(UIBasicWidgetActivity.this, name,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        //删除下划线，设置字体颜色为蓝色
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                }, start, start + name.length(), 0);
            }
        }
        return ssb.append("等" + likeUsers.length + "个人觉得很赞");
    }

    private void initImageView() {
        ui_base_img_5 = (RoundImageView) findViewById(R.id.ui_base_img_5);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_test);
        ui_base_img_5.setBitmap(bitmap);
    }

    private void initRadioCheckbox() {
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.ui_base_radioGrp);
        Button btnChange = (Button) findViewById(R.id.ui_base_radioPost);
        Button btnPost = (Button) findViewById(R.id.ui_base_ckPost);

        //第一种获得单选按钮值的方法
        //为radioGroup设置一个监听器:setOnCheckedChanged()
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "按钮组值发生改变,你选了" + radbtn.getText(), Toast.LENGTH_LONG).show();
            }
        });

        //为radioGroup设置一个监听器:setOnCheckedChanged()
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
                    if (rd.isChecked()) {
                        Toast.makeText(getApplicationContext(), "点击提交按钮,获取你选择的是:" + rd.getText(), Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });

        ui_base_ck_1 = (CheckBox) findViewById(R.id.ui_base_ck_1);
        ui_base_ck_2 = (CheckBox) findViewById(R.id.ui_base_ck_2);
        ui_base_ck_3 = (CheckBox) findViewById(R.id.ui_base_ck_3);

        ui_base_ck_1.setOnCheckedChangeListener(this);
        ui_base_ck_2.setOnCheckedChangeListener(this);
        ui_base_ck_3.setOnCheckedChangeListener(this);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choose = "";
                if (ui_base_ck_1.isChecked()) choose += ui_base_ck_1.getText().toString() + "";
                if (ui_base_ck_2.isChecked()) choose += ui_base_ck_2.getText().toString() + "";
                if (ui_base_ck_3.isChecked()) choose += ui_base_ck_3.getText().toString() + "";
                Toast.makeText(getApplicationContext(), choose, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.tbtn_open:
                if(compoundButton.isChecked()) Toast.makeText(this,"打开声音",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"打开声音",Toast.LENGTH_SHORT).show();
                break;
            case R.id.swh_status:
                if(compoundButton.isChecked()) Toast.makeText(this,"开关:ON",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"开关:OFF",Toast.LENGTH_SHORT).show();
                break;
            default:
                if (compoundButton.isChecked())
                    Toast.makeText(this, compoundButton.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }

    }


}


