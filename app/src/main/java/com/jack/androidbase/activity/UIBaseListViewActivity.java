package com.jack.androidbase.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.adapter.AnimalAdapter;
import com.jack.androidbase.bean.Animal;

import java.util.LinkedList;
import java.util.List;

public class UIBaseListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;
    private Button btn_add, btn_del, btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_base_list_view);

        mContext = UIBaseListViewActivity.this;

        initView();
    }

    private void initView() {
        btn_add = (Button) findViewById(R.id.add_row);
        btn_del = (Button) findViewById(R.id.del_row);
        btn_clear = (Button) findViewById(R.id.del_all);

        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        list_animal = (ListView) findViewById(R.id.list_animal);

        mData = new LinkedList<Animal>();
        mData.add(new Animal("狗说", "你是狗么?", R.drawable.avatar_1));
        mData.add(new Animal("牛说", "你是牛么?", R.drawable.avatar_2));
        mData.add(new Animal("鸭说", "你是鸭么?", R.drawable.avatar_3));
//        mData.add(new Animal("鱼说", "你是鱼么?", R.drawable.avatar_4));
//        mData.add(new Animal("马说", "你是马么?", R.drawable.avatar_5));
//        mData.add(new Animal("马说", "你是马么?", R.drawable.avatar_6));
//        mData.add(new Animal("马说", "你是马么?", R.drawable.avatar_7));
//        mData.add(new Animal("牛说", "你是牛么?", R.drawable.avatar_2));
//        mData.add(new Animal("鸭说", "你是鸭么?", R.drawable.avatar_3));
//        mData.add(new Animal("鱼说", "你是鱼么?", R.drawable.avatar_4));
//        mData.add(new Animal("马说", "你是马么?", R.drawable.avatar_5));

        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);

        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_row:
                mAdapter.add(new Animal("马说", "你是马么?", R.drawable.avatar_7));
                break;
            case R.id.del_row:
                mAdapter.remove(0); //删除第一行
                break;
            case R.id.del_all:
                mAdapter.clear(); //删除all
                break;
        }
    }
}
