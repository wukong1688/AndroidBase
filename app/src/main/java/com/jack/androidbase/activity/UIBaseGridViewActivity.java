package com.jack.androidbase.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.adapter.CustomAdapter;
import com.jack.androidbase.bean.Icon;

import java.util.ArrayList;

public class UIBaseGridViewActivity extends AppCompatActivity {
    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_base_grid_view);

        mContext = UIBaseGridViewActivity.this;

        initView();
    }

    private void initView(){
        grid_photo = (GridView) findViewById(R.id.grid_photo);

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.avatar_1, "图标1"));
        mData.add(new Icon(R.drawable.avatar_2, "图标2"));
        mData.add(new Icon(R.drawable.avatar_3, "图标3"));
        mData.add(new Icon(R.drawable.avatar_4, "图标4"));
        mData.add(new Icon(R.drawable.avatar_5, "图标5"));
        mData.add(new Icon(R.drawable.avatar_6, "图标6"));
        mData.add(new Icon(R.drawable.avatar_7, "图标7"));

        mAdapter = new CustomAdapter<Icon>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
