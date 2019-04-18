package com.jack.androidbase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jack.androidbase.R;
import com.jack.androidbase.adapter.CustomRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考： https://blog.csdn.net/new_one_object/article/details/57102997
 */
public class UIRecyclerView2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mData = null;
    private CustomRecyclerViewAdapter myAdapter;
    private CustomRecyclerViewAdapter.OnItemClickListener listener1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_recycler_view2);

        bindView();
    }

    private void bindView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.ui_basic_recycler_custom_1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList();
        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }

        //数据装载
        myAdapter = new CustomRecyclerViewAdapter(mData, R.layout.item_recyler_1) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                holder.setText(R.id.recycler_txt_1, obj.toString());
            }
        };

        //点击监听
        listener1 = new CustomRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), position + " Long click", Toast.LENGTH_SHORT).show();
            }
        };
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(listener1);
    }
}
