package com.jack.androidbase.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.adapter.CustomListViewAdapter;
import com.jack.androidbase.bean.Hero;

import java.util.ArrayList;

public class UISpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spin_one;
    private Spinner spin_two;
    private Context mContext;
    //判断是否为刚进去时触发onItemSelected的标志
    private boolean one_selected = false;
    private boolean two_selected = false;
    private ArrayList<Hero> mData = null;
    private BaseAdapter myAdadpter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_spinner);

        mContext = UISpinnerActivity.this;
        mData = new ArrayList<Hero>();
        bindViews();
    }

    private void bindViews() {
        spin_one = (Spinner) findViewById(R.id.spin_one);
        spin_two = (Spinner) findViewById(R.id.spin_two);

        mData.add(new Hero(R.drawable.avatar_1, "迅捷斥候：提莫（Teemo）"));
        mData.add(new Hero(R.drawable.avatar_2, "诺克萨斯之手：德莱厄斯（Darius）"));
        mData.add(new Hero(R.drawable.avatar_3, "无极剑圣：易（Yi）"));

        myAdadpter = new CustomListViewAdapter<Hero>(mData, R.layout.item_spin_hero) {
            @Override
            public void bindView(ViewHolder holder, Hero obj) {
                holder.setImageResource(R.id.img_icon, obj.gethIcon());
                holder.setText(R.id.txt_name, obj.gethName());
            }
        };
        spin_two.setAdapter(myAdadpter);//spin_one已经用android:entries填充数据，所以这里只在代码中填充spin_two

        spin_one.setOnItemSelectedListener(this);
        spin_two.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_one:
                if (one_selected) {
                    Toast.makeText(mContext, "您的分段是~：" + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                } else one_selected = true;
                break;
            case R.id.spin_two:
                if (two_selected) {
                    TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
                    Toast.makeText(mContext, "您选择的英雄是~：" + txt_name.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                } else two_selected = true;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
