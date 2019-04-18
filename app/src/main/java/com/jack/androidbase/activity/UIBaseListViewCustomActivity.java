package com.jack.androidbase.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.adapter.CustomListViewAdapter;
import com.jack.androidbase.bean.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomListViewAdapter 为封装好的adapter，用于直接new使用
 */
public class UIBaseListViewCustomActivity extends AppCompatActivity {
    private Context mContext;
    private ListView list_book, list_book2;

    private CustomListViewAdapter<Animal> myAdapter1 = null;
    private CustomListViewAdapter<Animal> myAdapter2 = null;
    private List<Animal> mData1 = null;
    private List<Animal> mData2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_base_list_view_custom);

        mContext = UIBaseListViewCustomActivity.this;
        initList1();
        initList2();
    }

    private void initList1() {
        list_book = (ListView) findViewById(R.id.ui_base_list_v2_1);

        //数据初始化
        mData1 = new ArrayList<>();
        mData1.add(new Animal("狗说", "你是狗么?", R.drawable.avatar_1));
        mData1.add(new Animal("牛说", "你是牛么?", R.drawable.avatar_2));
        mData1.add(new Animal("鸭说", "你是鸭么?", R.drawable.avatar_3));

        //Adapter初始化
        myAdapter1 = new CustomListViewAdapter<Animal>((ArrayList) mData1, R.layout.item_list_animal) {
            @Override
            public void bindView(ViewHolder holder, Animal obj) {
                holder.setImageResource(R.id.img_icon, obj.getaIcon());
                holder.setText(R.id.txt_aName, obj.getaName());
            }
        };

        //ListView设置下Adapter：
        list_book.setAdapter(myAdapter1);

        list_book.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initList2() {
        list_book2 = (ListView) findViewById(R.id.ui_base_list_v2_2);

        //数据初始化
        mData2 = new ArrayList<>();
        mData2.add(new Animal("我说", "你是狗么?", R.drawable.avatar_6));
        mData2.add(new Animal("你说", "你是牛么?", R.drawable.avatar_7));
        mData2.add(new Animal("他说", "你是鸭么?", R.drawable.avatar_8));

        //Adapter初始化
        myAdapter2 = new CustomListViewAdapter<Animal>((ArrayList) mData2, R.layout.item_list_animal) {
            @Override
            public void bindView(ViewHolder holder, Animal obj) {
                holder.setImageResource(R.id.img_icon, obj.getaIcon());
                holder.setText(R.id.txt_aName, obj.getaName());
            }
        };

        //ListView设置下Adapter：
        list_book2.setAdapter(myAdapter2);

    }
}
